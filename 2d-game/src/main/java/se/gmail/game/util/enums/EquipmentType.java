package se.gmail.game.util.enums;

public enum EquipmentType {
    HELMET,
    CHEST,
    BELT,
    PANTS,
    BOOTS,
    GLOVES,
    MAIN_HAND,
    OFF_HAND,
    NECKLACE,
    GEM,
    CONSUMABLE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
