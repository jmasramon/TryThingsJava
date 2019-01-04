import java.util.List;

public class Coordinates {
    Point pX;
    Point pY;
    Direction direction;
    List<Obstacle> obstacles;


    public Coordinates(Point pX, Point pY, Direction direction, List<Obstacle> obstacles) {
        this.pX = pX;
        this.pY = pY;
        this.direction = direction;
        this.obstacles = obstacles;
    }

    public Point getPx() { return pX; }
    public Point getPy() {
        return pY;
    }

    public int getX() { return pX.getLocation(); }
    public int getY() {
        return pY.getLocation();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void lookLeft() {
        direction = leftDirection();
    }

    public void lookRight() {
        direction = rightDirection();
    }

    private Direction leftDirection() {
        return direction.next();
    }

    private Direction rightDirection() {
        return direction.prev();
    }

    public String getPosition() {
        return "" +
                getX() +
                " X " +
                getY() +
                " " +
                getDirection().toString().substring(0,1);
    }

    public void goForward() throws ObstacleFoundException {
        switch (getDirection()){
            case NORTH:
                goUp();
                break;
            case EAST:
                goRight();
                break;
            case SOUTH:
                goDown();
                break;
            case WEST:
                goLeft();
                break;
        }
    }

    public void goBackwards() throws ObstacleFoundException {
        switch (getDirection()){
            case NORTH:
                goDown();
                break;

            // todo: pending to add the rest of cases
        }
    }

    private class TentativePosition {
        int x, y;

        public TentativePosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    private void goLeft() throws ObstacleFoundException {
        go('x', -1);
    }

    private void go(char axis, int step) throws ObstacleFoundException {
        int x = getX(),
            y = getY();
        switch (axis) {
            case 'x':
                x += step;
                break;
            case 'y':
                y += step;
                break;

        }
        TentativePosition tentativePosition = new TentativePosition(x, y) ;
        setNewPos(tentativePosition);
    }

    private void setNewPos(TentativePosition tentativePosition) throws ObstacleFoundException {
        if (freePassage(tentativePosition)) {
            pX.setNewPos(tentativePosition.x); // seems that starting positon is (1,1) instead of (0,0)
            pY.setlocation(tentativePosition.y); // seems that starting positon is (1,1) instead of (0,0)
        } else {
            throw new ObstacleFoundException("Cannot move any more. Obstacle at " + tentativePosition);
        }

    }

    private boolean freePassage(TentativePosition tentativePosition) {
        for (Obstacle obstacle: obstacles) {
            if (isThisObstacleWhereIWantToGo(tentativePosition, obstacle)) return false;
        }
        return true;
    }

    private boolean isThisObstacleWhereIWantToGo(TentativePosition tentativePosition, Obstacle obstacle) {
        return (obstacle.x == tentativePosition.x) && (obstacle.y == tentativePosition.y);
    }

    private void goRight() throws ObstacleFoundException {
        go('x', 1);;
    }

    private void goUp() throws ObstacleFoundException {
        go('y', 1);;
    }

    private void goDown() throws ObstacleFoundException {
        go('y', -1);;
    }

}
