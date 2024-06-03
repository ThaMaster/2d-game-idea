package se.gmail.game.model.object.equipment.weapons.ranged.bows;

import javax.swing.text.html.parser.Entity;

import se.gmail.game.model.entities.Player;
import se.gmail.game.model.object.equipment.weapons.Weapon;
import se.gmail.game.util.enums.EquipmentType;
import se.gmail.game.util.enums.WeaponType;

public class WoddenBow extends Weapon{

    public WoddenBow() {
        super(EquipmentType.TWO_HANDED_WEAPON, WeaponType.BOW, true);
        this.setObjName("Wodden Bow");
        this.setObjDesc("A very standard bow used by hunters across the realm.");
        this.loadObjImage("/objects/weapons/bows/sprites/bow_0.png");
        this.setItemSize(2, 2);
    }

    @Override
    public void attack(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attack'");
    }

    @Override
    public void applyStats(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyStats'");
    }

    @Override
    public void removeStats(Player p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeStats'");
    }

    @Override
    public void onPickup(Player p) {
        p.addToInventory(this);
    }
    
}
