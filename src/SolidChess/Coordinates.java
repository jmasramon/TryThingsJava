package SolidChess;

public class Coordinates {
    public enum Row {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H;
        private static Row[] vals = values();

        public static int length() {
            return vals.length;
        }

        public Row next()
        {
            return vals[(ordinal()+1) % vals.length];
        }

        public Row prev() {
            int nextPotentialPos = ((ordinal() - 1) % vals.length);
            return vals[nextPotentialPos == -1 ? vals.length - 1 : nextPotentialPos];
        }
    }

    public enum Column {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT;
        private static Column[] vals = values();
        public Column next()
        {
            return vals[(this.ordinal()+1) % vals.length];
        }
        public static int length() {
            return vals.length;
        }

        public Column prev() {
            int nextPotentialPos = ((ordinal() - 1) % vals.length);
            return vals[nextPotentialPos == -1 ? vals.length - 1 : nextPotentialPos];
        }
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    final private Row    row;
    final private Column column;

    @Override
    public boolean equals(Object other) {
        return (row == ((Coordinates)other).row && column == ((Coordinates)other).column);
    }

    public Coordinates(Row row, Column column) {
        this.row = row;
        this.column = column;
    }
}
