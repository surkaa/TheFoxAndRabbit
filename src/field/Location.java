package field;

public record Location(int x, int y) {

    public boolean equals(Location location) {
        return this.x == location.x() && this.y == location.y();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
