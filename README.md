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

### Change log 2024-06-06

#### Changes

- **Inventory:** Have now integrated the inventory within the game window instead of having its own window.
- **Inventory:** The players inventory is now changed to 8x8 instead of 10x10.
- **Inventory:** Added inventory ui walls.

#### Bug Fixes

- **Inventory:** Fixed a bug where all the enemies were updated super fast when interacting with the inventory.
- **Inventory:** Fixed a bug where the game crashed upon picking up an item.

#### Images

### TODO

- **Stock Market:** Take a look at the stock modes, they maybe should have percentages such that they switch to the *right* mode. For example, *Fast Climb* have a *3%* chance to switch to *Fast Fall*.
- **Stock Market:** The East stock market panel have small white border that should not be present.
- **Stock Market:** Decide how the stocks for a market is to be selected and what information should be stored in the resource file.
- **Stock Market:** Take out the logic of creating the stock market graph to seperate function so that the *paintComponent* function does less logic.
- **Stock Market:** Integrate the stock market to the game instead of having it be a seperate window.

---

- **Inventory:** Make it possible to drop items from inventory.
- **Inventory:** Make items in the inventory highlight when hovering over them with the mouse.
- **Inventory:** Create the hotbar using either the inventory or its own section in the equipment window.
- **Inventory:** Refactor the inventory drawing method for occupied inventory slots. DO IT AS SOON AS POSSIBLE!
- ~~**Inventory:** Integrate the inventory interface into the main game window instead of having a seperate window for it.~~

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
