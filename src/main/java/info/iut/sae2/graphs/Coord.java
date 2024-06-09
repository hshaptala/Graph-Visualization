package main.java.info.iut.sae2.graphs;

public class Coord {

    private double x;
    private double y;

    public Coord() {
        x = y = 0.;
    }

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this coordinate and another coordinate.
     *
     * @param c The coordinate to calculate the distance to.
     * @return The distance between this coordinate and the given coordinate.
     */
    public double dist(Coord c) {
        double distx = this.x - c.x;
        double disty = this.y - c.y;
        return Math.sqrt(distx * distx + disty * disty);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) { // TAG
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coord coord = (Coord) o;

        return this.x == coord.x && this.y == coord.y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Coord(x=%.2f, y=%.2f)", x, y);
    }

}
