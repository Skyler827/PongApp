var scene, camera, directionalLight, renderer, bg_light, point_light;
var left_paddle_geo, white_material, left_paddle;
var right_paddle_geo, right_paddle;
var background_geo, wall_mesh, background_wall;
var ball_geo, ball_mesh, ball, ball_vx, ball_vy;
var frame_geo, frame_mesh, middle_line;
var t, previous_t, dt;
var mouse;
var left_up, left_down, right_up, right_down;
var gridHelper;
var left_paddle_mouse_grabber;
var arr_mouse_grabber;
var paused = false;

var paddle_velY = 0,
    paddle_velX = 0,
    speed = .5,
    accel = .02,
    friction = .95,
    keys = [];

function init() {
    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, .1, 1000 );
    // camera = new THREE.OrthographicCamera( width / - 2, width / 2, height / 2, height / - 2, 1, 1000 );
    directionalLight = new THREE.DirectionalLight( 0xffffff, 10 );
    scene.add( directionalLight );

    renderer = new THREE.WebGLRenderer();
    renderer.setSize( window.innerWidth, window.innerHeight );
    document.querySelector("#canvas_container").appendChild( renderer.domElement );

    camera.position.set( 0, 0, 12);
    camera.lookAt( 0, 0, 0);
    bg_light = new THREE.AmbientLight( 0x404040, 2 ); // soft white light
    scene.add( bg_light);
    point_light = new THREE.PointLight(0x666666, 3, 0);
    point_light.translateZ(-10);
    scene.add(point_light);

    left_paddle_geo = new THREE.BoxGeometry( 0.5, 3, 1 );

    // Texture wrapping for left paddle
    var paddleTexture = new THREE.TextureLoader().load("/img/wood_texture2.jpg");
    paddleTexture.minFilter = THREE.LinearFilter;
    white_material = new THREE.MeshStandardMaterial( { color: 0xffffff } );
    left_paddle = new THREE.Mesh(left_paddle_geo, new THREE.MeshPhongMaterial({color:0xffffff, map:paddleTexture}));
    left_paddle.translateX(-10);
    scene.add(left_paddle);

    left_paddle_mouse_grabber_geo = new THREE.BoxGeometry(4,12,0.8);
    left_paddle_mouse_grabber_material = new THREE.MeshBasicMaterial({
        color: 0x248f24, alphaTest: 0, visible: false});
    left_paddle_mouse_grabber = new THREE.Mesh(
        left_paddle_mouse_grabber_geo,left_paddle_mouse_grabber_material);
    left_paddle_mouse_grabber.translateX(-10);
    // left_paddle_mouse_grabber.translateY(+2);
    scene.add(left_paddle_mouse_grabber);

    right_paddle_geo= new THREE.BoxGeometry( 1, 3, 1 );
    right_paddle = new THREE.Mesh(right_paddle_geo, white_material);
    right_paddle.translateX(10);
    right_paddle.translateY(-3);
    scene.add(right_paddle);

    var texture, material;

    texture = new THREE.TextureLoader().load("/img/universe1.jpg")
    texture.minFilter = THREE.LinearFilter;
    material = new THREE.MeshBasicMaterial({ map : texture });
    background_geo = new THREE.BoxGeometry(60, 30, 1);
    background_wall = new THREE.Mesh(background_geo, material);
    background_wall.translateZ(-5);
    scene.add(background_wall);

    ball_geo = new THREE.SphereGeometry(0.4);
    ball_mesh = new THREE.MeshStandardMaterial({color:0xff0000});
    ball = new THREE.Mesh(ball_geo, ball_mesh);
    scene.add(ball);
    ball_vx = 4;
    ball_vy = 1;

    // Center line
    midline_geo= new THREE.BoxGeometry(.2, 30, -1);
    middle_line = new THREE.Mesh(midline_geo, white_material);
    middle_line.translateZ(-1);
    scene.add(middle_line);

    t = 0;
    left_up = false; left_down = false; right_up = false; right_down = false;
    mouse = new THREE.Vector2();
    raycaster = new THREE.Raycaster();
}

function onDocumentMouseMove(event) {
    mouse.x = ( (event.clientX) / (window.innerWidth) ) * 2 - 1;
    mouse.y = - ( event.clientY / window.innerHeight ) * 2 + 1;

    raycaster.setFromCamera(mouse, camera);
    var intersects = raycaster.intersectObject(left_paddle_mouse_grabber);
    if (intersects.length > 0) {
        left_paddle.position.y = intersects[0].point.y;
    }
}
function collide() {
	//Checking both paddles, could be limited with direction of ball velocity
	//Could also be checked if the ball is close to the a paddle
    paddleCollision(left_paddle, ball);
    paddleCollision(right_paddle, ball);

    
    var next_y = ball.position.y + ball_vy*0.05;
    if (5 < next_y || next_y < -5) {
        ball_vy *= -1;
    }
}

//Checks if the paddle passed in is in collision with the ball
function paddleCollision(paddle, ball){
	let p = new THREE.Box3().setFromObject(paddle);
	let b = new THREE.Box3().setFromObject(ball);
	let col = p.intersectsBox(b);
	if(col){
        ball_vx *= -1;
        playSoundOnce('/img/ping.mp3');
	}
}

function returnBall() {
    ball.position.x = 0;
    ball.position.y = 0;
    playSoundOnce('/img/out.mp3');
}
function pauseGame() {
    paused = true;
    document.querySelector("#pause-anchor").style.display = "none";
    document.querySelector("#resume-anchor").style.display = "inline";
}
function resumeGame() {
    paused = false;
    document.querySelector("#pause-anchor").style.display = "inline";
    document.querySelector("#resume-anchor").style.display = "none";
    animate();
}
function computeNextCollision() {

}
function computerMove() {

}
function update() {
    t = performance.now()/1000;
    collide();
    if (keys[38]) {
        if (paddle_velY > -speed) {
            paddle_velY += accel;
        }
    }
    
    if (keys[40]) {
        if (paddle_velY < speed) {
            paddle_velY -= accel;
        }
    }
    if (keys[39]) {
        if (paddle_velX < speed) {
            paddle_velX += accel;
        }
    }
    if (keys[37]) {
        if (paddle_velX > -speed) {
            paddle_velX -= accel;
        }
    }

    // Paddle position constraint
    paddle_velY *= friction;
    if(paddle_velY > 0){
        if(left_paddle.position.y <= 5){
            left_paddle.translateY(paddle_velY);
        }
    }else{
        if(left_paddle.position.y >= -5){
            left_paddle.translateY(paddle_velY);
        }
    }
    paddle_velX *= friction;
    if(paddle_velX > 0){
        if(left_paddle.position.x <= -3){
            left_paddle.translateX(paddle_velX);
        }
    }else{
        if(left_paddle.position.x >= -10){
            left_paddle.translateX(paddle_velX);
        }
    }


    ball.translateX(0.05*ball_vx);
    ball.translateY(0.05*ball_vy);
    if (Math.abs(ball.position.x)>20) {
        returnBall();
        pauseGame();
    }
    right_paddle.position.y = ball.position.y;
}


document.body.addEventListener("keydown", function (e) {
    keys[e.keyCode] = true;
    console.log(e.keyCode);
    if (e.keyCode === 32 && paused === true) {
        resumeGame();
        }
});
document.body.addEventListener("keyup", function (e) {
    keys[e.keyCode] = false;
});

function animate() {
    update();
    if (paused) {return;}
    requestAnimationFrame( animate );
    renderer.render( scene, camera );
}

function sound() {
    myAudio = new Audio('/img/audio.mp3'); 
myAudio.addEventListener('ended', function() {
    this.currentTime = 0;
    this.play();
}, false);
myAudio.play();
}

function playSoundOnce(path) {
    bgSound = new Audio(path);
    bgSound.play(); 
}


init();
animate();
