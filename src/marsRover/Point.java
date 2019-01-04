package marsRover;

public class Point {
    private int location,
        maxLocation;

    public Point(int x, int y) {
        this.location = x;
        this.maxLocation = y;
    }

    public int getLocation() {
        return location;
    }

    public int getMaxLocation() {
        return maxLocation;
    }


    public void setlocation(int location) {
        this.location = location;
    }

    public void setNewPos(int value) {
        int roundedValue = roundTheWorld(value, this.maxLocation);
        setlocation(roundedValue);
    }

    private int roundTheWorld(int value, int limit) {
        return Math.floorMod(value, limit + 1); // seems that starting positon is (1,1) instead of (0,0)
    }

}
