package tennis;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GameTest {

    Player victor;
    Player sarah;
    Game game;

    @Before
    public void beforeGameTest() {
        victor = new Player("Victor");
        sarah = new Player("Sarah");
        game = new Game(victor, sarah);
    }

    @Test
    public void loveShouldBeDescriptionForScore0() {
        Game game = new Game(victor, sarah);
        assertThat(game, hasProperty("score"));
        assertThat(game, hasProperty("score", is("love, love")));
    }

    @Test
    public void fifteenShouldBeDescriptionForScore1() {
        sarah.winBall();
        assertThat(game, hasProperty("score", is("love, fifteen")));
    }

    @Test
    public void thirtyShouldBeDescriptionForScore2() {
        victor.winBall();
        victor.winBall();
        sarah.winBall();
        assertThat(game, hasProperty("score", is("thirty, fifteen")));
    }

    @Test
    public void fortyShouldBeDescriptionForScore3() {
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            victor.winBall();
        });
        assertThat(game, hasProperty("score", is("forty, love")));
    }

    @Test
    public void advantageShouldBeDescriptionWhenLeastThreePointsHaveNeenScoredByEachSideAndPlayerHasOnePointMoreThanHisOpponent() {
        IntStream.rangeClosed(1, 3).forEach((Integer) -> {
            victor.winBall();
        });
        IntStream.rangeClosed(1, 4).forEach((Integer) -> {
            sarah.winBall();
        });
        assertThat(game, hasProperty("score", is("advantage Sarah")));
    }

    @Test
    public void deuceShouldBeDescriptionWhenAtLeastThreePointsHaveBeenScoredByEachPlayerAndTheScoresAreEqual() {
        for(int index = 1; index <= 3; index++) {
            victor.winBall();
        }
        for(int index = 1; index <= 3; index++) {
            sarah.winBall();
        }
        assertThat(game, hasProperty("score", is("deuce")));
        victor.winBall();
        assertThat(game, hasProperty("score", is(not("deuce"))));
        sarah.winBall();
        assertThat(game, hasProperty("score", is("deuce")));
    }

    @Test
    public void gameShouldBeWonByTheFirstPlayerToHaveWonAtLeastFourPointsInTotalAndWithAtLeastTwoPointsMoreThanTheOpponent() {
        for(int index = 1; index <= 4; index++) {
            victor.winBall();
        }
        for(int index = 1; index <= 3; index++) {
            sarah.winBall();
        }
        assertThat(game, hasProperty("score", is(not("Victor won"))));
        assertThat(game, hasProperty("score", is(not("Sarah won"))));
        victor.winBall();
        assertThat(game, hasProperty("score", is("Victor won")));
    }

}