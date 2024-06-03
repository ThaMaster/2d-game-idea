package se.gmail.game.model.object.equipment.consumables.potions;

import se.gmail.game.model.entities.Player;
import se.gmail.game.model.object.equipment.consumables.ConsumableObject;
import se.gmail.game.util.enums.EquipmentType;

public class HealthPotion extends ConsumableObject {

    private int healthRestore = 45;

    public HealthPotion() {
        super(EquipmentType.CONSUMABLE);
        this.setObjName("Health Potion");
        this.setObjDesc("Very poggersh health potion that replenishes your health points.");
        this.loadObjImage("/objects/potions/sprites/potion_0.png");
        this.loadUsedImage("/objects/potions/sprites/potion_6.png");
        this.setItemSize(1, 1);
    }

    @Override
    public void onUse(Player p) {
        if(!alreadyUsed()) {
            double newHealth = p.getCurrentHealth() + healthRestore;
            if(newHealth > p.getMaxHealth()) {
                p.setCurrentHealth(p.getMaxHealth());
            } else {
                p.setCurrentHealth(newHealth);
            }
            this.setObjImage(getUsedImage());
            setUsed(true);
        }
    }

    @Override
    public void onPickup(Player p) {
        p.addToInventory(this);
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
}
