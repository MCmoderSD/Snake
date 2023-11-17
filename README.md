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
If you want to help me translate the game into your language, please contact me and [read more](#custom-language). <Br>

## Controls

The snake is controlled by the arrow keys or WASD. <Br>
You can pause and resume the game by pressing escape. <Br>
To start the game, press space or enter. <Br>
You can exit the game by pressing Ctrl + C. <Br>

## Debugging

You can enable an FPS counter by pressing F3 + F . <Br>
You can enable grid lines by pressing F3 + G. <Br>
You can enable hitboxes by pressing F3 + B. <Br>

## Custom Assets

You can add your own assets to the game. <Br>
You can use both the main jar and the jar with asset-streaming. <Br>

1. Create a `.json` file with the following structure: <Br>

```json
{
  "icon": "/Path/To/Icon.png",
  "fieldWidth": 32,
  "fieldHeight": 16,
  "scale": 32,
  "fps": 60,
  "tps": 10,
  "specialFoodChance": 0.05,
  "resizable": false,
  "solidWalls": false,
  "backgroundTile": "/Path/To/BackgroundTile.png",
  "head": "/Path/To/Head.png",
  "upperBody": "/Path/To/UpperBody.png",
  "lowerBody": "/Path/To/LowerBody.png",
  "legTile": "/Path/To/LegTile.png",
  "legTransition": "/Path/To/LegTransition.png",
  "feet": "/Path/To/Feet.png",
  "food": "/Path/To/Food.png",
  "goldFood": "/Path/To/GoldFood.png",
  "foodSound": "/Path/To/Food.wav",
  "ultSound": "/Path/To/Ult.wav",
  "dieSound": "/Path/To/Die.wav",
  "headAnimation": "/Path/To/Head.gif",
  "upperBodyAnimation": "/Path/To/UpperBody.gif",
  "lowerBodyAnimation": "/Path/To/LowerBody.gif",
  "legTileAnimation": "/Path/To/LegTile.gif",
  "legTransitionAnimation": "/Path/To/LegTransition.gif",
  "feetAnimation": "/Path/To/Feet.gif",
  "gridLayoutColor": "#000000",
  "snakeHitboxColor": "#FF0000",
  "foodHitboxColor": "#FFFF00",
  "fpsColor": "#FFFF00",
  "scoreColor": "#FFFF00",
  "textColor": "#000000",
  "backgroundColor": "#1e2428",
  "snakeColor": "#5662f6",
  "foodColor": "#e27662",
  "goldFoodColor": "#1ccb5b"
}
```

2. Replace the paths with the absolute paths to your assets. <Br>
   If it doesn't work, try using forward slashes instead of backslashes or double slashes. <Br>
   For audio there is currently only support for `.wav` files encoded with 16 bit PCM. <Br>
   For images, you can use `.jpg`, `.jpeg`, but `.png` works best. <Br>
   For animations, you can use `.gif` files. <Br> <Br>

3. Run the game with the custom config file. <br>
   For example: `java -jar Snake.jar <language> <PathToTheConfigFile>` <br> <br>

## Custom Language

You can use your own language pack in the game. <Br>
You can use both the main jar and the jar with asset-streaming. <Br>

1. Create a `.json` file with the following structure: <Br>

```json
{
  "title": "Snake",
  "restart": "Restart",
  "gameOver": "Game Over",
  "score": "Score"
}
```

2. Replace the values with your own translations. <Br>
   If you want to help me translate the game into your language, please contact me. <Br>
3. Run the game with the custom language file. <br>
   For example: `java -jar Snake.jar <PathToTheLanguageFile>` <br> <br>

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
- [x] Pause
- [x] Resume
- [x] FPS Counter
- [x] Grid Lines
- [x] Hitboxes
- [x] Language Support
- [x] Custom Assets
- [ ] Win
- [ ] Game Over
- [ ] Menu