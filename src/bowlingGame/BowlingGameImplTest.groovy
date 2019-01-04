package bowlingGame

class BowlingGameImplTest extends groovy.util.GroovyTestCase {
    BowlingGame game;
    void setUp() {
        super.setUp()
        game = new BowlingGameImpl()

    }

    void tearDown() {
    }

    void testAllZeroes() {
        rollSame(20, 0)
        assertEquals(0, game.score())
    }

    void testAllOnes() {
        rollSame(20, 1)
        assertEquals(20, game.score())

    }

    void testIntermediateScores(){
        game.roll(5)
        assertEquals(5, game.score())

        game.roll(4)
        assertEquals(9, game.score())

    }

    void testResetScore() {
        game.roll(5)
        assertEquals(5, game.score())

        game.reset()
        assertEquals(0, game.score())

    }

    void testOneSpare() {
        rollSpare()
        game.roll(3)
        rollSame(17,0)
        assertEquals(16, game.score())
    }

    void testOneStrike() {
        rollStrike()
        game.roll(3)
        game.roll(4)
        rollSame(17,0)
        assertEquals(24, game.score())
    }

    void testPerfectGame() throws Exception {
        rollStrikes(10)
        rollExtraStrike()
        rollExtraStrike()
        assertEquals(300, game.score())
    }

    def rollStrikes(int reps) {
        while (reps-- > 0) {
            rollStrike()
        }
    }

    def rollExtraStrike(){
        game.roll(10)
    }

    private rollStrike() {
        game.roll(10)
        game.roll(0)
    }

    private void rollSpare() {
        Random r = new Random()
        def pins = r.nextInt(9)
        game.roll(pins)
        game.roll(10-pins) // spare
    }

    void rollSame(times, pins) {
        for (i in 0..(times-1)) {
            game.roll(pins)
        }

    }
}
