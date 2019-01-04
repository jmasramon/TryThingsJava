package poly;//import poly.Shape;

import java.io.PrintStream;

abstract public class Poly {
    protected final PrintStream stream;

    public Poly(PrintStream stream) {
        this.stream = stream;
    }

    abstract public void draw();
}
