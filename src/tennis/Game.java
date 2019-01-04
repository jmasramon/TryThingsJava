package tennis;

public class Game {
    Player first, second;
    private String score;


    public Game(Player first, Player second) {
        score = "love, love";
        this.first = first;
        this.second = second;
    }

    public String getScore() {
        String proposedScore = first.getMyScore() + ", " + second.getMyScore();
        if (proposedScore.equals("forty, forty")) return Score.DEUCE.getName();
        return proposedScore;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
