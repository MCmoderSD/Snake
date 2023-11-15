# Snake

## Description
This is a simple snake game written in Java using the Swing library with Graphics2D. <Br>
Currently, it is in a very early stage of development, so it is not complete yet. <Br>

## How to play
The goal of the game is to eat as many apples as possible. <Br>
In this adaption you can go through the walls and appear on the other side. <Br>
It's game over when the snake hits itself. <Br>
The snake grows by one block when it eats an apple. <Br>
The golden apple activates your ult for 7 seconds. <Br>
Your ult makes you twice as fast, makes you invincible, and you can go through the walls. <Br>

## How to run
You need Java 8 installed on your computer to run this game. <Br>
You can download it [here](https://www.java.com/de/download/manual.jsp). <Br>

You can download the latest release [here](https://github.com/MCmoderSD/Snake/releases/latest). <Br>
After downloading the jar file, you can run it by double-clicking it. <Br>

You can either download the main jar or the jar with asset-streaming. <Br>
The main jar contains all the assets, but it is a lot bigger. <Br>
The jar with asset-streaming downloads the assets from the internet, so it is a lot smaller. <Br>
The downside is that you need a good and constant internet connection to run it. <Br>

Alternatively, you can run it from the command line with the following command: <Br>
`java -jar Snake.jar` <Br>

You can change the language of the game by adding the following argument: <Br>
`java -jar Snake.jar en` <Br>

Currently, the following languages are supported: <Br>
- English (en)
- German (de)

I'm planning to add more languages in the future. <Br>
If you want to help me translate the game into your language, please contact me. <Br>

## Controls
The snake is controlled by the arrow keys or WASD. <Br>
You can pause and resume the game by pressing escape. <Br>
To start the game, press space or enter. <Br>
You can exit the game by pressing Ctrl + C. <Br>

## Debugging
You can enable an FPS counter by pressing F3 + F . <Br>
You can enable grid lines by pressing F3 + G. <Br>
You can enable hitboxes by pressing F3 + B. <Br>

## Features
- [x] Snake movement
- [x] Apple spawning
- [x] Snake growing
- [x] Snake hitting itself
- [x] Snake going through walls
- [x] Snake hitting walls (optional)
- [x] Score
- [x] Golden apple
- [x] Asset-streaming
- [x] Sound effects
- [x] Ult Working
- [x] Ult Animation
- [x] Rotation
- [x] Restart
- [ ] Win
- [ ] Game Over
- [ ] Menu