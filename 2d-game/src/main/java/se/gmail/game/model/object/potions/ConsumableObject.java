package se.gmail.game.model.object.potions;

import se.gmail.game.model.entities.Player;
import se.gmail.game.model.object.GameObject;

public abstract class ConsumableObject extends GameObject {
    public abstract void onUse(Player p);
}
