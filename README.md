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

</div>

## Recent changes and TODO log

This section contains the most recent updates and changes that is implemented in the game. It will contain a somewhat detailed description of new systems, bug fixes, gameplay tweaks, and and more to give a good overview on how the project is going. In addition to this, the section will end with a TODO log that details what should be the focus of the next programming session. Only the most recent updates will be detailed here so if you would like to see previous logs, these are located [here](/readme_handling/logs).

### Change log 2024-05-25

#### Changes

- **Stock Market:** Stocks can now change stockmode if its corresponding stockmode duration runs out. However, there is also a 10% chance to spontaneously change in order to make it harder to detect which mode the stock is in.
- **Stock Market:** Changed so that the starting value of a stock is randomly selected around the resting value.
- **Stock Market:** The value history graph can now contain multiple stocks at a time.
- **Stock Market:** Implemented the hide button functionality. It shrinks the panel and only displays the percentage of which the stock has changed. It also hides the value history in the main graph when the stock panel is hidden which enables one to focus on specific stocks.
- **Stock Market:** Implemented the graph scaling feature, which currently can display the 10, 50 and 100 earlier values of the stocks.
- **Stock Market:** Slightly changed the styling of the stock panel, adding borders and added a scroll pane if the market contains very many stock types.
- **Stock Market:** The stock icon now is displayed at the end of the graph instead of the beginning.
- **Stock Market:** Added two additional stocks that can appear in the stock markets. The stocks that are currently available are: **Iron**, **Gold**, and **Iron**. However, many more are planned to be added to the game.
- **Stock Market:** Changed the sprite of the **Gold** stock, it is now a gold ingot and the gold coin can be used when displaying the players currency.

---

- **Version Control:** Restructured the readme so that it is constructed by multiple files rather than a single one in order to make it easier to log.
- **Version Control:** Added a git hook so that these change logs are sorted where the newest one is displayed in the main readme.md file through a bash script.

#### Bug Fixes

- **Stock Market:** Fixed issue with the stock increase/decrease percentage displaying wrong values.
- **Stock Market:** Fixed an issue where the actual value displayed on the graph was wrong.
- **Stock Market:** Fixed an index out of bound exception when not having performed enought ticks corresponding to the selected graph scale.
- **Stock Market:** Fixed the vertical graph lines so that it correctly displays previous stock *tick* numbers.
- **Stock Market:** Fixed an issue where one or more graph lines disappears when chaning graph scale.
- **Stock Market:** Fixed weird behaviour when the stock value instantly plummeted below $1.

---

- **Code:** Adjusted the pom.xml file so that the java compiler compliance specifies tha correct JRE version.

#### Images

![secondStockGraph](/readme_handling/images/2024-05-25/secondStockGraph.png)
*The second iteration of the stock market window. Many improvments have been made but it is still long ways to go until it can be used in the game.*

![ironIngot](/readme_handling/images/2024-05-25/iron.png)
![goldIngot](/readme_handling/images/2024-05-25/gold.png)
![diamond](/readme_handling/images/2024-05-25/diamond.png)
![goldCoin](/readme_handling/images/2024-05-25/gold_coin.png)

*Additional icons that has been added to the game.*

### TODO

- **Stock Market:** Implement functionality to buy and sell stocks.
- **Stock Market:** Take a look at the stock modes, they maybe should have percentages such that they switch to the *right* mode. For example, *Fast Climb* have a *3%* chance to switch to *Fast Fall*.
- **Stock Market:** Change all stock values such that they round to two decimals to limit floating point errors.
- **Stock Market:** The East stock market panel have small white border that should not be present.
- **Stock Market:** Make the simulation occurr in real time.
- **Stock Market:** Decide how the stocks for a market is to be selected and what information should be stored in the resource file.

---

- **General Game:** Make the environemnt tiles have multiple layers such as a base terrain layer, detail layer, and more.
- **General Game:** Fix the scaling of the tilemap.
- **General Game:** Make the game run smoothly and become more reliable, oh god...
- **General Game:** Make the camera follow the player and not be fixed.
- **General Game:** Implement collisionboxes/hitboxes.
- **General Game:** Figure out how the players different currencies will be stored, updated and accessed.

---

- **Code:** Take out the logic of creating the stock market graph to seperate function so that the *paintComponent* function does less logic.
- **Code:** Finish the *loadStockInfo* function in the *JsonLoader* class.
- **Code:** Decide how the overall architecture of the game should look like such that it is easy to implement new features while being good from a programming stand point.
- **Code** Start to comment the code so I know what is going on.
