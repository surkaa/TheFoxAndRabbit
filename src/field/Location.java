package field;

public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Location location) {
        return this.x == location.getX() && this.y == location.getY();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
