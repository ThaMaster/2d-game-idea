package se.gmail.game.util.enums;

public enum Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST;
    
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
