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
You need Java 21 installed on your computer to run this game. <br>
You can download it [here](https://www.oracle.com/de/java/technologies/downloads/#java21). <br>

You can download the latest release [here](https://github.com/MCmoderSD/Snake/releases/latest). <br>
After downloading the JAR file, you can run it by double-clicking it. <br>

You can either download the main JAR or the JAR with asset-streaming. <br>
The main JAR contains all the assets, but it is a lot bigger. <br>
The JAR with asset-streaming downloads the assets from the internet, so it is a lot smaller. <br>
The downside is that you need a good and constant internet connection to run it.

Alternatively, you can run it from the command line with the following command:
```bash
java -jar Snake.jar
```

You can change the language of the game by adding the following argument:
```bash
java -jar Snake.jar en
```

Currently, the following languages are supported:
- English (en)
- German (de)
- Italian (it)
- Spanish (es)
- French (fr)

I'm planning to add more languages in the future. <br>
If you want to help me translate the game into your language, please contact me.


## Troubleshooting

### Sound Issues on Linux
If you encounter sound problems or see an error like this:
```
Error initializing audio: line with format PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian not supported.
```
This issue occurs because the sound system used by my [JavaAudioLibrary](https://github.com/MCmoderSD/JavaAudioLibrary) relies on the **ALSA (Advanced Linux Sound Architecture)** system and also requires **PulseAudio** and other related libraries.

To resolve this on Ubuntu/Debian-based distributions, you can install the necessary packages by running the following commands:
```bash
sudo apt update
sudo apt install alsa-utils libasound2-plugins libpulse-java pulseaudio pulseaudio-utils
```
After installation, restart your system or restart the PulseAudio service to ensure the changes take effect.


## Controls
The snake is controlled by the arrow keys or WASD. <br>
You can pause and resume the game by pressing escape. <br>
To start the game, press space or enter. <br>
You can exit the game by pressing Ctrl + C. <br>


## Debugging
You can enable an FPS counter by pressing F3 + F. <br>
You can enable an TPS counter by pressing F3 + T. <br>
You can enable grid lines by pressing F3 + G. <br>
You can enable hitboxes by pressing F3 + B. <br>


## Custom Assets
You can add your own assets to the game. <br>
You can use both the main JAR and the JAR with asset-streaming. <br>

1. Create a `.json` file with the following structure:
```json
{
   "settings": {
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
      "solidWalls": false
   },
   "assets": {
      "images": {
         "icon": "/assets/images/Icon.png",
         "backgroundTile": "/assets/images/BackgroundTile.png",
         "backgroundCover": "/assets/images/BackgroundCover.png",
         "head": "/assets/images/Head.png",
         "upperBody": "/assets/images/UpperBody.png",
         "lowerBody": "/assets/images/LowerBody.png",
         "legTile": "/assets/images/LegTile.png",
         "legTransition": "/assets/images/LegTransition.png",
         "feet": "/assets/images/Feet.png",
         "food": "/assets/images/Food.png",
         "goldFood": "/assets/images/GoldFood.png"
      },
      "animations": {
         "head": "/assets/animations/Head.gif",
         "upperBody": "/assets/animations/UpperBody.gif",
         "lowerBody": "/assets/animations/LowerBody.gif",
         "legTile": "/assets/animations/LegTile.gif",
         "legTransition": "/assets/animations/LegTransition.gif",
         "feet": "/assets/animations/Feet.gif",
         "opFood": "/assets/animations/OpFood.gif"
      },
      "sounds": {
         "food": "/assets/sounds/Eating.wav",
         "ult": "/assets/sounds/Rainbow.wav",
         "die": "/assets/sounds/OOF.wav"
      }
   },
   "colors": {
      "gridLayout": "#000000",
      "snakeHitbox": "#FF0000",
      "foodHitbox": "#FFFF00",
      "fps": "#FFFF00",
      "tps": "#FFFF00",
      "score": "#FFFF00",
      "text": "#000000",
      "background": "#1E2428",
      "snake": "#5662F6",
      "food": "#E27662",
      "goldFood": "#1CCB5B",
      "opFood": "#FF00FF"
   }
}
```

2. Replace the paths with the absolute paths to your assets. <br>
   If it doesn't work, try using forward slashes instead of backslashes or double slashes. <br>
   For audio, there is currently only support for .wav files encoded with 16-bit PCM. <br>
   For images, you can use .jpg, .jpeg, but .png works best. <br>
   For animations, you can use .gif files. <br> <br>

3. Run the game with the custom config file. <br>
   For example:
```bash
java -jar Snake.jar <language> <PathToTheConfigFile>
```


## Custom Language
You can use your language pack in the game. <br>
You can use both the main JAR and the JAR with asset-streaming. <br>

1. Create a .json file with the following structure:
```json
{
  "title": "Snake",
  "restart": "Restart",
  "restartToolTip": "Restart the game",
  "gameOver": "Game Over",
  "scorePrefix": "Score: ",
  "fpsPrefix": "FPS: ", 
  "tpsPrefix": "TPS: "
}
```

2. Replace the values with your translations. <br>
   If you want to help me translate the game into your language, please contact me. <br> <br>
3. Run the game with the custom language file. <br>
   For example:
```bash
java -jar Snake.jar <PathToTheLanguageFile>
```         