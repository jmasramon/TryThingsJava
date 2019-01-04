package bowlingGame;

public class BowlingGameImpl implements BowlingGame {
    private int[] pins      = new int[22];
    private int   cur_frame = 0;

    public BowlingGameImpl() {}

    @Override
    public void roll(int pins) {
        this.pins[cur_frame++] = pins;
    }

    private boolean isLastStrike(int cur_frame) {
        return isStrike(cur_frame) && cur_frame == 18;
    }

    @Override
    public int score() {
        int res = 0;

        for (int i = 0; i< 20; i++){
            res += pins[i];
            if (isStrike(i)) {
                if (isNormalStrike(i)) {
                    if (isStrike(i + 2)) {
                        res += pins[i + 2] + pins[i + 4];
                    } else {
                        res += pins[i + 2] + pins[i + 3];
                    }
                } else if(isLastStrike(i)) {
                    res += pins[i + 2] + pins[i + 3];
                }
            } else if (isSpare(i)) {
                res += pins[i+1];
            }
        }
        return res;
    }

    private boolean isNormalStrike(int cur_frame) {
        return cur_frame < 18;
    }

    private boolean isSpare(int i) {
        return (isOdd(i) && !isStrike(i-1) && addToTen(i));
    }

    private boolean addToTen(int i) {
        return (pins[i] + pins[i-1]) == 10;
    }

    private boolean isStrike(int i) {
        return isEven(i) && pins[i] == 10;
    }

    private boolean isEven(int i) {
        return !isOdd(i);
    }

    private boolean isOdd(int i) {
        return i%2 != 0;
    }

    public void reset() {
        cur_frame = 0;
        pins = new int[21];
    }
}
