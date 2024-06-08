package se.gmail.game.util.enums;

public enum WeaponType {
    AXE,
    BOW,
    DAGGER,
    SPEAR,
    SWORD,
    WAND,
    HANDGUN,
    RIFLE,
    SHOTGUN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
