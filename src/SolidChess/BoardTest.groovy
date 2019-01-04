package SolidChess

class BoardTest extends GroovyTestCase {
    Board board;

    void setUp() {
        super.setUp()
        board = new Board()
    }

    void testInitialisation() {
        assertEquals(1, board.getPieces().size())
        Coordinates coordinates = new Coordinates(Coordinates.Row.A, Coordinates.Column.ONE);
        assertEquals(new Bishop(Color.WHITE, coordinates), board.getPieces().get(0))
        assertEquals(coordinates, board.getPieces().get(0).getPosition())
    }

    void testPotentialMoves() {
        assertEquals(5, board.getPieces().get(0).potentialMoves().size())
    }
}
