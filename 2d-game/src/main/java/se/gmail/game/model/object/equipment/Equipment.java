package se.gmail.game.model.object.equipment;

import se.gmail.game.model.entities.Player;
import se.gmail.game.model.object.GameObject;
import se.gmail.game.util.enums.EquipmentType;

public abstract class Equipment extends GameObject {

    private EquipmentType type;

    public Equipment(EquipmentType type) {
        this.type = type;
    }

    public abstract void applyStats(Player p);
    public abstract void removeStats(Player p);

    public EquipmentType getType() {
        return this.type;
    }
}
