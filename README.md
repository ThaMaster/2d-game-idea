# 2D Game Idea

## About this game

### Game Concept

### Key Features

## Help

### Controls

This section lists all the differnet keybinds used for the game. These keybinds represent the default keyboard layout
but can be 

<div align=center>

| Key        | Function                                      |
|:----------:|:---------------------------------------------:|
| W          | **Moves** the character to the **up**         |
| S          | **Moves** the character to the **down**       |
| A          | **Moves** the character to the **left**       |
| D          | **Moves** the character to the **right**      |

</div>

## Todo logs

This section details what is to be done in the next programming session. This section is an attempt at keeping a good focus on what to work on the next time a programming session comences.

### 2024-05-23
---
- **Stock Market:** Make the stocks have a chance to switch stock mode when performing a tick.
- **Stock Market:** Implement functionality to buy and sell stocks.
- **Stock Market:** Implement such that the hide button on the stock panel minimizes the stock panel.
- **Stock Market:** Make it possible to add multiple graph lines in a single image.
- **Stock Market:** Make it possible to toggle each stocks graph on and off.
- **Stock Market:** Implement different view scaling on the graph. These could be a stocks value history from current to 10, 50, 100, 1000 ticks back.
- **Stock Market:** Make the simulation occurr in real time.
- **Stock Market:** Decide how the stocks for a market is to be selected and what information should be stored in the resource file.
---
- **General Game:** Make the environemnt tiles have multiple layers such as a base terrain layer, detail layer, and more.
- **General Game:** Fix the scaling of the tilemap.
- **General Game:** Make the game run smooth, oh god...
- **General Game:** Make the camera follow the player and not be fixed.
- **General Game:** Implement collisionboxes/hitboxes.

---
- **Code:** Finish the *loadStockInfo* function in the *JsonLoader* class.
- **Code:** Decide how the overall architecture of the game should look like such that it is easy to implement new features while being good from a programming stand point.
- **Code** Start to comment the code so I know what is going on.

---

## Change log

This section details the changes that occurs with each session of working on this project. After each programming session a new list with all the things that have been done and other information will be posted. This is to have a clear view of what has been done and what is needed to be fixed/added to the project.

### 2024-05-23

#### New system: The Stock Market
- First iteration of the **Stock Market** system that the player will be able to utilize in the game. This system is to be used in different locations in the game and simulate different demands which the player can exploit to gain massive amounts of resources and money.
- Each stock is 
- Gui for the **Stock Market** system has been added. However, this is in its own frame and the user cannot at the time of writing this buy and sell stocks.

#### Bug Fixes
- Fixed an issue with not being able to load content from the resource folder inside the project.
- 

#### Other
- Updated this readme to be of a higher standard.
- Created the *StyleManager* which will be a static class and can be accessed throughout the system. This class contains the stylings that should be used for a unified design for the fonts, colors, and such.
- Some overall refactoring of the classes has been made to make it easier to implement all this.
- Created a *HashMap* in the image loader to prevent the class from loading already loaded images.
- Created a resource file for the data of the stocks to make it easier to add new information. This may be changed if alternative methods that is better is found.

##### Images

![firstStockGraph](/images/firstStockGraph.png)
*The first prototype of the **Stock Market** system. This is in its own window at the time of writing but it will be better integrated to the main game when it is deemed to be ready.*

[//]: # "Use this: ![imageText](/path/to/image) to insert images in markdown."