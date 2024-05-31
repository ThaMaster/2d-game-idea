package se.gmail.game.model.object.potions;

import javax.swing.text.html.parser.Entity;

import se.gmail.game.model.object.GameObject;

public abstract class ConsumableObject extends GameObject {
    public abstract void onUse(Entity e);
}
