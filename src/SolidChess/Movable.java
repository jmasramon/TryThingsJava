package SolidChess;

import java.util.ArrayList;

public interface Movable {
    void move(Coordinates destination);

    public ArrayList<Coordinates> potentialMoves();
}
