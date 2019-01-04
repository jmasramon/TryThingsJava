public enum Direction {
    NORTH,
    WEST,
    SOUTH,
    EAST;

    private static Direction[] dirs = values();

    public Direction next() {
        return go(1);
    }

    public Direction prev() {
        return go(-1);
    }

    private Direction go(int step){
        return dirs[Math.floorMod((this.ordinal() + step), dirs.length)];
    }
}
