package se.gmail.game.model.object.potions;

import javax.swing.text.html.parser.Entity;

import se.gmail.game.model.entities.Player;

public class HealthPotion extends ConsumableObject {

    private int healthRestore = 45;

    public HealthPotion() {
        this.setObjName("Health Potion");
        this.setObjDesc("Very poggersh health potion that replenishes your health points.");
        this.loadObjectImage("/objects/potions/sprites/potion_0.png");
    }

    @Override
    public void onUse(Player p) {
        double newHealth = p.getCurrentHealth() + healthRestore;
        if(newHealth > p.getMaxHealth()) {
            p.setCurrentHealth(p.getMaxHealth());
        } else {
            p.setCurrentHealth(newHealth);
        }
    }

    @Override
    public void onPickup(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onPickup'");
    }    
}
