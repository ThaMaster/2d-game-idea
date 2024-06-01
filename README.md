# 2D Game Idea

## About this game

### Game Concept

### Key Features

## Help

### Controls

This section lists all the differnet keybinds used for the game. These keybinds represent the default keyboard layout but it will be possible to remap them in a settings window at a later date.

<div align=center>

| Key        | Function                                      |
|:----------:|:---------------------------------------------:|
| W          | **Moves** the character to the **up**         |
| S          | **Moves** the character to the **down**       |
| A          | **Moves** the character to the **left**       |
| D          | **Moves** the character to the **right**      |
| Q          | Opens the **Stock Market** window             |

</div>

## Recent changes and TODO log

This section contains the most recent updates and changes that is implemented in the game. It will contain a somewhat detailed description of new systems, bug fixes, gameplay tweaks, and and more to give a good overview on how the project is going. In addition to this, the section will end with a TODO log that details what should be the focus of the next programming session. Only the most recent updates will be detailed here so if you would like to see previous logs, these are located [here](/readme_handling/logs).

### Change log 2024-05-27

#### Changes

---

- **Code:** The entities in the game now have two types of positions, *world position* and *screen position*.
- **Code:** The game camera now always displays the character in the middle of the screen and moves the world making larger areas possible, see section *Images* for example.
- **Code:** Refactored the draw method in the *TileManager* class to not draw the content outside the screen, see section *Images for demonstration*.

---

- **Objects:** Made it possible to place different kind of objects.
- **Objects:** Objects can be picked up and runs the *onPickup* function.
- **Objects:** Objects is extended by corresponding super class. For example, potions extends the *ConsumablObject* class.
- **Objects:** The player stores objects in an *ArrayList* but will later be done in a inventory class to enable inventory management.
- **Objects:** Objects inside the player inventory is displayed on the screen.
- **Objects:** Consumables now have an additional image. This image can replace the regular object image upon use.
- **Objects:** The objects in the players inventory can now be used or dropped.
- **Objects:** Created the first potion, *Health Potion* it restores *45hp* and can be used once.

---

- **Collisions:** Added a *insideScreen* function to check if coordinates are inside the screen or not.
- **Collisions:** Added the *CollisionBox* class that will handle the collision detection in the game.
- **Collisions:** Made it possible to draw the collision boxes for debugging purposes.
- **Collisions:** Implemented the first attempt at collisions, but only works for players as of writing this.
- **Collisions:** Added additional collision check that function that uses the intersection method.

---

- **UI:** Added super simple hotbar when picking up items. Will be replaced with a better looking hotbar later.
- **UI:** The hotbar now highlights the selected hotbar item.

---

- **Art:** Added a bunch of different weapons and potions to be used for the different equipment and objects.

#### Bug Fixes

- **Collisions:** Fixed an issue with the player being able to go through a collision tile and then getting stuck.
- **Collisions:** Fixed an issue where the collision is displayed in the wrong place at the screen.

#### Images

![updatedCamera](/readme_handling/images/2024-05-31/updatedCamera.gif)

*Updated camera behaviour, the camera now follows the player instead of being static around the whole map.*

![restrictedDrawing](/readme_handling/images/2024-05-31/restrictedDrawing.gif)

*The game only renders the content inside the window. The image above showcases this but with decreased boundaries.*

![objectManagement](/readme_handling/images/2024-05-31/objectManagement.gif)

*The player is able to pick up items such as potions, as seen above, and consume them. The selected item in the hotbar is highlighted as white and can be either used or drop the selected item.*

### TODO

- **Stock Market:** Take a look at the stock modes, they maybe should have percentages such that they switch to the *right* mode. For example, *Fast Climb* have a *3%* chance to switch to *Fast Fall*.
- **Stock Market:** The East stock market panel have small white border that should not be present.
- **Stock Market:** Decide how the stocks for a market is to be selected and what information should be stored in the resource file.
- **Stock Market:** Take out the logic of creating the stock market graph to seperate function so that the *paintComponent* function does less logic.

---

- **Game Settings:** Implement different display modes such as **Fullscreen**, **Windowed fullscreen**, and **Windowed**.
- **Game Settings:** Implement a **Brightness** slider.
- **Game Settings:** Option to toggle **FPS** on and off.
- **Game Settings:** Make it possible to change keybindings.
- **Game Settings:** Implement specific game options.
- **Game Settings:** Implement audio sliders for different types of audio.

---

- **General Game:** Create a character creation screen.
- **General Game:** Create a starting screen.
- **General Game:** Create the player stats screen which displays all kinds of stats.
- **General Game:** Create the equipment screen where different types of equipable gear is placed, does not need to change appearence of player yet.
- **General Game:** Create the inventory screen where different items can be stored, toggled with *Tab*.
- **General Game:** Make the environemnt tiles have multiple layers such as a base terrain layer, detail layer, and more.
- **General Game:** Fix the scaling of the tilemap.
- **General Game:** Make it possible to save the character and all the gear that is in inventory/equipped.
- **General Game:** Figure out how the players different currencies will be stored, updated and accessed.
- **General Game:** Implement a 'pickup' key so that items do not immediately get picked up on collision.

---

- **Code:** Decide how the overall architecture of the game should look like such that it is easy to implement new features while being good from a programming stand point.
- **Code:** Start to comment the code so I know what is going on.
- **Code:** Refactor the way the buttons becomes enabled/disabled.
- **Code:** Create some sort of a *GameMaster* class that handles the spawning of enemies and such.
