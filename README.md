# Snake

## Description

This is a simple snake game written in Java using the Swing library with Graphics2D. <br>
Currently, it is in a very early stage of development, so it is not complete yet. <br>

## How to play

The goal of the game is to eat as many apples as possible. <br>
In this adaptation, you can go through the walls and appear on the other side. <br>
It's game over when the snake hits itself. <br>
The snake grows by one block when it eats an apple. <br>
The golden apple activates your ult for 7 seconds. <br>
Your ult makes you twice as fast, makes you invincible, and you can go through the walls. <br>
The enchanted golden apple activates your op ult for 7 seconds. <br>
Your op ult is like the normal ult, but you are three times as fast and grow every second. <br>

## How to run

You need Java 8 installed on your computer to run this game. <br>
You can download it [here](https://www.java.com/de/download/manual.jsp). <br>

You can download the latest release [here](https://github.com/MCmoderSD/Snake/releases/latest). <br>
After downloading the JAR file, you can run it by double-clicking it. <br>

You can either download the main JAR or the JAR with asset-streaming. <br>
The main JAR contains all the assets, but it is a lot bigger. <br>
The JAR with asset-streaming downloads the assets from the internet, so it is a lot smaller. <br>
The downside is that you need a good and constant internet connection to run it. <br>

Alternatively, you can run it from the command line with the following command: <br>
`java -jar Snake.jar` <br>

You can change the language of the game by adding the following argument: <br>
`java -jar Snake.jar en` <br>

Currently, the following languages are supported: <br>

- English (en)
- German (de)
- Italian (it)
- Spanish (es)

and many other but not approved by native speakers. <br>

I'm planning to add more languages in the future. <br>
If you want to help me translate the game into your language , please contact me and [read more](#custom-language). <br>
Or else you can approve the translations [here](#unapproved). <br>

## Controls

The snake is controlled by the arrow keys or WASD. <br>
You can pause and resume the game by pressing escape. <br>
To start the game, press space or enter. <br>
You can exit the game by pressing Ctrl + C. <br>

## Debugging

You can enable an FPS counter by pressing F3 + F. <br>
You can enable grid lines by pressing F3 + G. <br>
You can enable hitboxes by pressing F3 + B. <br>

## Custom Assets

You can add your own assets to the game. <br>
You can use both the main JAR and the JAR with asset-streaming. <br>

1. Create a `.json` file with the following structure: <br>

```json
{
  "icon": "/Images/Icon.png",
  "fieldWidth": 32,
  "fieldHeight": 16,
  "scale": 32,
  "fps": 60,
  "tps": 10,
  "specialFoodChance": 0.05,
  "specialFoodDuration": 7,
  "opUltGrowInterval": 1000,
  "ultSpeed": 2,
  "opUltSpeed": 3,
  "resizable": false,
  "solidWalls": false,
  "backgroundCover": "/Images/BackgroundCover.png",
  "backgroundTile": "/Images/BackgroundTile.png",
  "head": "/Images/Head.png",
  "upperBody": "/Images/UpperBody.png",
  "lowerBody": "/Images/LowerBody.png",
  "legTile": "/Images/LegTile.png",
  "legTransition": "/Images/LegTransition.png",
  "feet": "/Images/Feet.png",
  "food": "/Images/Food.png",
  "goldFood": "/Images/GoldFood.png",
  "foodSound": "/Audio/Eating.wav",
  "ultSound": "/Audio/Rainbow.wav",
  "dieSound": "/Audio/OOF.wav",
  "headAnimation": "/Animations/Head.gif",
  "upperBodyAnimation": "/Animations/UpperBody.gif",
  "lowerBodyAnimation": "/Animations/LowerBody.gif",
  "legTileAnimation": "/Animations/LegTile.gif",
  "legTransitionAnimation": "/Animations/LegTransition.gif",
  "feetAnimation": "/Animations/Feet.gif",
  "opFoodAnimation": "/Animations/OpFood.gif",
  "gridLayoutColor": "#000000",
  "snakeHitboxColor": "#FF0000",
  "foodHitboxColor": "#FFFF00",
  "fpsColor": "#FFFF00",
  "scoreColor": "#FFFF00",
  "textColor": "#000000",
  "backgroundColor": "#1E2428",
  "snakeColor": "#5662F6",
  "foodColor": "#E27662",
  "goldFoodColor": "#1CCB5B",
  "opFoodColor": "#FF00FF"
}
```

2. Replace the paths with the absolute paths to your assets. <br>
   If it doesn't work, try using forward slashes instead of backslashes or double slashes. <br>
   For audio, there is currently only support for .wav files encoded with 16-bit PCM. <br>
   For images, you can use .jpg, .jpeg, but .png works best. <br>
   For animations, you can use .gif files. <br> <br>

3. Run the game with the custom config file. <br>
   For example: java -jar Snake.jar <language> <PathToTheConfigFile> <br> <br>

## Custom Language

You can use your language pack in the game. <br>
You can use both the main JAR and the JAR with asset-streaming. <br>

1. Create a .json file with the following structure: <br>

```json
{
  "title": "Snake",
  "restart": "Restart",
  "restartToolTip": "Restart the game",
  "gameOver": "Game Over",
  "scorePrefix": "Score: ",
  "fpsPrefix": "FPS: "
}
```

2. Replace the values with your translations. <br>
   If you want to help me translate the game into your language, please contact me. <br> <br>
3. Run the game with the custom language file. <br>
   For example: java -jar Snake.jar <PathToTheLanguageFile> <br> <br>

## Languages

### Approved

- English (en)
- German (de)
- Italian (it)
- Spanish (es)

### Unapproved

- French (fr)
- Portuguese (pt)
- Russian (ru)
- Chinese (zh)
- Japanese (ja)
- Korean (ko)
- Turkish (tr)
- Polish (pl)
- Dutch (nl)
- Swedish (sv)
- Czech (cs)
- Hungarian (hu)
- Danish (da)
- Finnish (fi)
- Norwegian (no)
- Romanian (ro)
- Slovak (sk)
- Slovenian (sl)
- Ukrainian (uk)
- Vietnamese (vi)
- Greek (el)
- Bulgarian (bg)
- Croatian (hr)
- Lithuanian (lt)
- Serbian (sr)
- Arabic (ar)

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
- [x] Ult Sound
- [x] Ult Timer
- [x] Op Ult
- [x] Rotation
- [x] Restart
- [x] Pause
- [x] Resume
- [x] FPS Counter
- [x] Grid Lines
- [x] Hitboxes
- [x] Language Support
- [x] Custom Assets
- [x] Op Ult Animation
- [ ] Win
- [ ] Game Over
- [ ] Menu