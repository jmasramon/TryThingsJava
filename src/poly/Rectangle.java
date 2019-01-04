package poly;

import java.io.PrintStream;

public class Rectangle  extends Poly {

    public Rectangle(PrintStream stream) {
        super(stream);
    }

    @Override
    public void draw() {
        stream.print(Shape.RECTANGLE);
    }
}
