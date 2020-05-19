package Model;

import Exceptions.NoSuchDirectionException;

public enum Direction {
        NORTH, NORTHEAST, EAST ,SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST ,UP, DOWN;

    public static Direction getDirectionFromString(String s)
            throws NoSuchDirectionException {

        if (s == null)
            throw new IllegalArgumentException("direction must be non-null");

        Direction dir = null;
        for (Direction direction : Direction.values()) {
            if(direction.toString().equals(s)) {
                dir = direction;
                break;
            }
        }

        if (dir == null)
            throw new NoSuchDirectionException("wrong " + s);
        return dir;
    }
}
