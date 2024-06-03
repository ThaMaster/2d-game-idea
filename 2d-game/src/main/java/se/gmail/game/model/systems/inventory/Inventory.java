package se.gmail.game.model.systems.inventory;

import java.util.HashMap;

import se.gmail.game.model.object.GameObject;
import se.gmail.game.model.object.equipment.consumables.potions.HealthPotion;

public class Inventory {

    private HashMap<GameObject, Integer[]> itemPos = new HashMap<>();
    private boolean[][] grid;
    private GameObject[][] itemSpace;
    private int gridWidth;
    private int gridHeight;

    public Inventory(int width, int height) {
        this.gridWidth = width;
        this.gridHeight = height;
        this.grid = new boolean[width][height];
        this.itemSpace = new GameObject[width][height];
        for(int x = 0; x < gridWidth; x++) {
            for(int y = 0; y < gridHeight; y++) {
                grid[x][y] = true;
            }
        }

        insertAtFirstAvailableSlot(new HealthPotion());
    }

    public boolean insertAtFirstAvailableSlot(GameObject obj) {
        for(int x = 0; x < gridWidth; x++) {
            for(int y = 0; y < gridHeight; y++) {
                if(grid[x][y]) {
                    if(insertItem(obj, x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean insertItem(GameObject obj, int x, int y) {
        if(isSpaceAvailable(x, y, obj.getItemSizeW(), obj.getItemSizeH())) {
            Integer pos[] = {x, y};
            itemPos.put(obj, pos);
            setGridSpace(x, y, obj.getItemSizeW(), obj.getItemSizeH(), false);
            setItemSpace(x, y, obj.getItemSizeW(), obj.getItemSizeH(), obj);
            return true;
        } 

        return false;
    }

    public boolean removeItem(int x, int y) {
        if(grid[x][y]) {
            return false;
        }
        GameObject selectedObj = itemSpace[x][y];
        Integer pos[] = itemPos.get(selectedObj);
        setGridSpace(pos[0], pos[1], selectedObj.getItemSizeW(), selectedObj.getItemSizeH(), true);
        setItemSpace(pos[0], pos[1], selectedObj.getItemSizeW(), selectedObj.getItemSizeH(), null);
        return true;
    }

    public boolean isSpaceAvailable(int xPos, int yPos, int width, int height) {
        if(xPos < 0 || yPos < 0) {
            return false;
        }

        if(xPos+width > gridWidth || yPos+height > gridHeight) {
            return false;
        }

        for(int x = xPos; x < xPos + width; x++) {
            for(int y = yPos; y < yPos+height; y++) {
                if(!grid[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setGridSpace(int xPos, int yPos, int width, int height, boolean state) {
        for(int x = xPos; x < xPos + width; x++) {
            for(int y = yPos; y < yPos+height; y++) {
                grid[x][y] = state;
            }
        }
    }

    private void setItemSpace(int xPos, int yPos, int width, int height, GameObject obj) {
        for(int x = xPos; x < xPos + width; x++) {
            for(int y = yPos; y < yPos+height; y++) {
                itemSpace[x][y] = obj;
            }
        }
    }

    @Override
    public String toString() {
        String strOutput = "";
        for(int y = 0; y < gridHeight; y++) {
            for(int x = 0; x < gridWidth; x++) {
                if(grid[x][y]) {
                    strOutput += "[ ]";
                } else {
                    strOutput += "[" + itemSpace[x][y].getObjName() + "]";
                }
            }
            strOutput += "\n";
        }

        return strOutput;
    }

    public boolean[][] getInventoryGrid() {
        return this.grid;
    }

    public GameObject getItemAt(int x, int y) {
        return this.itemSpace[x][y];
    }

    public int getGridHeight() {
        return this.gridHeight;
    }

    public int getGridWidth() {
        return this.gridWidth;
    }
}
