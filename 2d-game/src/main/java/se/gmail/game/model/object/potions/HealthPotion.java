package se.gmail.game.model.object.potions;

import javax.swing.text.html.parser.Entity;

public class HealthPotion extends ConsumableObject {

    private int healthRestore = 45;

    public HealthPotion() {
        this.setObjName("Health Potion");
        this.setObjDesc("Very poggersh health potion that replenishes your health points.");
        this.loadObjectImage("/objects/potions/potion_0.png");
    }

    @Override
    public void onUse(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDrink'");
    }

    @Override
    public void onPickup(Entity e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onPickup'");
    }
    
}
