package SolidChess;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(Color color, Coordinates position) {
        super(color, position);
    }

    @Override
    public void move(Coordinates destination) {
        // TODO
    }

    @Override
    public ArrayList<Coordinates> potentialMoves() {
        ArrayList<Coordinates> potentialMoves = new ArrayList<>();

        potentialMoves.addAll(rightDiagonalMoves(getPosition()));

        potentialMoves.addAll(leftDiagonalMoves(getPosition()));

        return  potentialMoves;
    }

    private ArrayList<Coordinates> leftDiagonalMoves(Coordinates initialPosition) {
        ArrayList<Coordinates> potentialMoves = new ArrayList<>();
        Coordinates potentialMove = initialPosition;

        while(true) {
            potentialMove = new Coordinates(potentialMove.getRow().prev(), potentialMove.getColumn().next());
            if (Board.outOfBoard(potentialMove) || potentialMove == getPosition()) {
                break;
            } else {
                potentialMoves.add(potentialMove);
            }
        }

        return potentialMoves;
    }

    private ArrayList<Coordinates> rightDiagonalMoves(Coordinates initialPosition) {
        ArrayList<Coordinates> potentialMoves = new ArrayList<>();
        Coordinates potentialMove = initialPosition;

        while(true) {
            potentialMove = new Coordinates(potentialMove.getRow().next(), potentialMove.getColumn().next());
            if (Board.outOfBoard(potentialMove) || potentialMove.equals(getPosition())) {
                break;
            } else {
                potentialMoves.add(potentialMove);
            }
        }
        return potentialMoves;
    }


}
