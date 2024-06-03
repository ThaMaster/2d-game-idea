package se.gmail.game.model.object.equipment.weapons;


import javax.swing.text.html.parser.Entity;

import se.gmail.game.model.object.equipment.Equipment;
import se.gmail.game.util.enums.EquipmentType;
import se.gmail.game.util.enums.WeaponType;

public abstract class Weapon extends Equipment {
    private WeaponType wType;
    private boolean isRanged = false;

    public Weapon(EquipmentType eType, WeaponType wType, Boolean isRanged) {
        super(eType);
        this.wType = wType;
        this.isRanged = isRanged;
    }

    public abstract void attack(Entity e);

    public WeaponType getWeaponType() {
        return this.wType;
    }

    public boolean isRanged() {
        return this.isRanged;
    }
}
