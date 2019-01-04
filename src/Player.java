public class Player {
    private Score score;
    private String myScore;
    String name;

    public Player(String name) {
        this.name = name;
        score = Score.LOVE;
    }

    public String getMyScore() {
        return score.getName();
    }

    public void setMyScore(Score score) {
        this.score = score;
    }

    public void winBall() {
        score = increaseScore();
    }



    private Score increaseScore() {
        switch (score) {
            case FORTY: {
                return score;
            }
            case ADVANTAGE: {
                return score;
            }
            default: {
                return score.next();
            }
        }
    }
}
