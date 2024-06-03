package se.gmail.game.model.object.equipment.consumables;


import java.awt.image.BufferedImage;

import se.gmail.game.model.entities.Player;
import se.gmail.game.model.object.equipment.Equipment;
import se.gmail.game.util.ImageLoader;
import se.gmail.game.util.enums.EquipmentType;

public abstract class ConsumableObject extends Equipment {
    public ConsumableObject(EquipmentType type) {
        super(type);
    }

    private boolean used = false;
    private BufferedImage usedImage;
    public abstract void onUse(Player p);

    public void loadUsedImage(String imagePath) {
        this.usedImage = ImageLoader.loadImage(imagePath);
    }

    public BufferedImage getUsedImage() {
        return this.usedImage;
    }

    public boolean alreadyUsed() {
        return used;
    }

    public void setUsed(boolean b) {
        this.used = b;
    }
}
