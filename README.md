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

This section contains the most recent updates and changes that is implemented in the game. It will contain a somewhat detailed description of new systems, bug fixes, gameplay tweaks, and and more to give a good overview on how the project is going. In addition to this, the section will end with a TODO log that details what should be the focus of the next programming session. Only the most recent updates will be detailed here so if you would like to see previous logs, these are located [here](/logs).d

### Change log 2024-05-25

#### Changes

- **Stock Market:** Stocks can now change stockmode if its corresponding stockmode duration runs out. However, there is also a 10% chance to spontaneously change in order to make it harder to detect which mode the stock is in.
- **Stock Market:** Changed so that the starting value of a stock is randomly selected around the resting value.
- **Stock Market:** Implemented the hide button functionality. It shrinks the panel and only displays the percentage of which the stock has changed.
- **Stock Market:** Slightly changed the styling of the stock panel, adding borders and added a scroll pane if the market contains very many stock types.

#### Bug Fixes

- **Stock Market:** Fixed issue with the stock increase/decrease percentage displaying wrong values.

##### Images

### TODO

- **Stock Market:** Take a look at the stock modes, they maybe should have percentages such that they switch to the *right* mode. For example, *Fast Climb* have a *3%* chance to switch to *Fast Fall*.

---
