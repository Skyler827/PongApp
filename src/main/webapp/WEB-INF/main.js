var scene, camera, directionalLight, renderer, bg_light, point_light;
var left_paddle_geo, red_material, left_paddle;
var right_paddle_geo, right_paddle;
var background_geo, wall_mesh, background_wall;
var ball_geo, ball_mesh, ball, ball_vx, ball_vy;
var frame_geo, frame_mesh, frame;
var t, previous_t, dt;
var mouse;
var left_up, left_down, right_up, right_down;
var gridHelper;
var left_paddle_mouse_grabber;
var arr_mouse_grabber;
var paused = false;

function init() {
    scene = new THREE.Scene();
    camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 0.1, 1000 );
    directionalLight = new THREE.DirectionalLight( 0xffffff, 0.5 );
    scene.add( directionalLight );

    renderer = new THREE.WebGLRenderer();
    renderer.setSize( window.innerWidth, window.innerHeight );
    document.querySelector("#canvas_container").appendChild( renderer.domElement );

    camera.position.set( 0, 1, 12 );
    camera.lookAt( 0, 0, 0 );
    bg_light = new THREE.AmbientLight( 0x404040, 2 ); // soft white light
    scene.add( bg_light);
    point_light = new THREE.PointLight(0x666666,4,1000);
    point_light.translateZ(10);
    point_light.translateY(3);
    scene.add(point_light);

    left_paddle_geo = new THREE.BoxGeometry( 1, 3, 1 );
    red_material = new THREE.MeshStandardMaterial( { color: 0xff0000 } );
    left_paddle = new THREE.Mesh( left_paddle_geo, red_material);
    left_paddle.translateX(-10);
    scene.add(left_paddle);

    left_paddle_mouse_grabber_geo = new THREE.BoxGeometry(4,12,0.8);
    left_paddle_mouse_grabber_material = new THREE.MeshBasicMaterial({
        color: 0x248f24, alphaTest: 0, visible: false})
    left_paddle_mouse_grabber = new THREE.Mesh(
        left_paddle_mouse_grabber_geo,left_paddle_mouse_grabber_material);
    left_paddle_mouse_grabber.translateX(-10);
    // left_paddle_mouse_grabber.translateY(+2);
    scene.add(left_paddle_mouse_grabber);

    right_paddle_geo= new THREE.BoxGeometry( 1, 3, 1 );
    right_paddle = new THREE.Mesh( right_paddle_geo, red_material);
    right_paddle.translateX(10);
    right_paddle.translateY(-3);
    scene.add(right_paddle);

    background_geo = new THREE.BoxGeometry(20, 10, 1);
    wall_mesh = new THREE.MeshStandardMaterial({color: 0x333333});
    background_wall = new THREE.Mesh(background_geo, wall_mesh);
    background_wall.translateZ(-1);
    scene.add(background_wall);

    ball_geo = new THREE.SphereGeometry(0.4);
    ball_mesh = new THREE.MeshStandardMaterial({color:0x11BB22});
    ball = new THREE.Mesh(ball_geo, ball_mesh);
    scene.add(ball);
    ball_vx = 4;
    ball_vy = 1;
    frame_geo = new THREE.RingGeometry(10,11,4,1,3.14159*0.25,6.285);
    frame_mesh = new THREE.MeshStandardMaterial({color:0x22DD33});
    frame = new THREE.Mesh(frame_geo, frame_mesh);
    frame.translateZ(-0.45);
    frame.scale.x  = 1.3;
    frame.scale.y = 0.75;
    scene.add(frame);
    t = 0;
    document.addEventListener( 'mousemove', onDocumentMouseMove, false );
    left_up = false; left_down = false; right_up = false; right_down = false;
    mouse = new THREE.Vector2();
    raycaster = new THREE.Raycaster();
    document.addEventListener("keydown", onDocumentKeyDown, false);document.addEventListener("keyup", onkeyup, false);
}
function onDocumentKeyDown(event) {
    var keyCode = event.which;
    if (keyCode == 87) {
        left_up = true;
    } else if (keyCode == 83) {
        left_down = true;
    } else if (keyCode == 38) {
        right_up = true;
    } else if (keyCode == 40) {
        right_down = true;
    }
    console.log(keyCode);
}
function onkeyup(event){
    var keyCode = event.which;
    if (keyCode == 87) {
        left_up = false;
    } else if (keyCode == 83) {
        left_down = false;
    } else if (keyCode == 38) {
        right_up = false;
    } else if (keyCode == 40) {
        right_down = false;
    }
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
	}
}

function keyboardMovement(){
    if(left_up){
        left_paddle.translateY(0.2);
    }
    if(left_down){
        left_paddle.translateY(-0.2);
    }
    if(right_up){
        right_paddle.translateY(0.2);
    }
    if(right_down){
        right_paddle.translateY(-0.2);
    }
}
function returnBall() {
    ball.position.x = 0;
    ball.position.y = 0;
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
function update() {
    t = performance.now()/1000;
    collide();
    keyboardMovement();
    // left_paddle.translateY(0.09*Math.cos(t));
    // right_paddle.translateY(0.1*Math.cos(t+5));
    ball.translateX(0.05*ball_vx);
    ball.translateY(0.05*ball_vy);
    if (Math.abs(ball.position.x)>20) {
        returnBall();
        pauseGame();
    }
}
function animate() {
    update();
    if (paused) {return;}
    requestAnimationFrame( animate );
    renderer.render( scene, camera );
}

init();
animate();