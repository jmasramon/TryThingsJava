package tennis;

public enum Score {
    LOVE("love"),
    FIFTEEN("fifteen"),
    THIRTY("thirty"),
    FORTY("forty"),
    DEUCE("deuce"),
    ADVANTAGE("advantage"),
    WON("won");

    private String name;
    private static Score[] scores = values();

    Score(String name) {
        this.name = name;
    }

    public Score next() {
        return scores[Math.floorMod((this.ordinal() + 1), scores.length)];
    }

    public String getName() {
        return name;
    }}