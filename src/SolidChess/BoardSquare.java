package SolidChess;

public class BoardSquare {
    private final Color color;
    private final Coordinates coordinates;
    private Piece piece;

    public BoardSquare(Color color, Coordinates coordinates, Piece piece) {
        this.color = color;
        this.coordinates = coordinates;
        this.piece = piece;
    }
}
