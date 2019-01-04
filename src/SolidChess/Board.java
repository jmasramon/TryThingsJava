package SolidChess;

import java.util.HashMap;
import java.util.ArrayList;

public class Board {
    private final HashMap<Coordinates, BoardSquare> squares = new HashMap();
    private final ArrayList<Piece> pieces = new ArrayList();

    public Board() {
        Coordinates coordinates = new Coordinates(Coordinates.Row.A, Coordinates.Column.ONE);
        Piece piece = new Bishop(Color.WHITE, coordinates);

        pieces.add(piece);
        squares.put(coordinates, new BoardSquare(Color.WHITE, coordinates, piece)); // is this coordinate redundancy needed?
    }

    public static boolean outOfBoard(Coordinates potentialMove) {
        boolean res = (potentialMove.getColumn().ordinal() == Coordinates.Column.length() ||
                potentialMove.getRow().ordinal() == Coordinates.Row.length()) ||
                (potentialMove.getColumn().ordinal() < 0 ||
                        potentialMove.getRow().ordinal() < 0);
        return res;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
