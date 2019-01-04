import java.io.PrintStream;

public class Square extends Poly {

    public Square(PrintStream stream) {
        super(stream);
    }

    @Override
    public void draw() {
        stream.print(Shape.SQUARE);
    }
}
