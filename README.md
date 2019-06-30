# PongApp
A Pong web app by Allen, Kevin, Skyler, Richard, and Jim. (In no particular order)
This app is a WORK IN PROGRESS, gameplay is currently janky and very feature-incomplete
I plan to add substantially more features in the future.

## Initialization
1. Clone this repository with `git clone git@github.com:Skyler827/PongApp.git`
2. Log into Mysql/Mariadb and run the following commands:
 
        CREATE DATABASE pongapp;
        CREATE USER 'groupproject'@'localhost' IDENTIFIED BY 'bRm.c7Udn}&h<D';
        GRANT ALL ON pongapp.* to 'groupproject'@'localhost';
3. To run this on your development machine, run the following command:

        ./mvnw spring-boot:run
4. To run this on a production server, compile and run it with these two commands:

        ./mvnw package
        java -jar target/pongapp-0.0.1-SNAPSHOT.jar

## Deployment

1. Change the password of the pongapp database service account, or set it up with a different password
2. Set up a systemd service for running this app using the jar command above
3. Write an nginx settings file under `sites_available` to redirect web requests from the correct domain to the correct port (8080 by default) as follows:

        //TODO: write a nginx settings file
        //remember to proxy websockets!
4. Set up certbot to enable https

## Usage

A user may register on the site by filling out the registration form

A user my sign in with the given username and password they entered during registration

To play pong, one player must click the `Play Now` button that appears after they login, and another player must log in and click the `Join Game` button.

Once two players are in a game, play begins with the ball moving in a random direction.

Each player may control the pong paddle with his/her arrow keys.

Each player starts with ten points, and loses a point whenever they fail to catch the ball with their paddle.

The game is over when one player reaches zero points, and the other player is the winner.

## Feature Roadmap

[X] Display a simple pong animation client-side (done)
[X] Set up client side controls for a simple pong game
[X] Set up simple Java server for serving pong app
[X] Set up basic Java pong simulation code
[X] Rework client side code to synchropnize state with Java pong simulation
[X] Make user accounts and basic login and registration logic
[X] Make simple entities for games, lobbies, and match histories
[ ] Enable PongApp to handle when a player disconnects from a game
[ ] Allow a player to quit an ongoing game
[ ] Enable saving completed games in the match history table correctly when a match concludes or ends early
[ ] Enable players to browse their own match histories, and those of other players
[ ] Create a simple bot player for players to play against
