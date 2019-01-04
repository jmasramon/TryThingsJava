package SolidChess;

import javax.swing.text.Position;

abstract public class Piece implements Movable {
    private final Color color;
    private Coordinates position;

    public Piece(Color color, Coordinates position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public boolean equals(Object other) {
        return (color == ((Piece)other).color && this.getClass() == other.getClass());
    }

    public Coordinates getPosition() {
        return position;
    }
}
