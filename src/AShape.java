import java.io.PrintStream;

public class AShape  extends Poly {

    public AShape(PrintStream stream) {
        super(stream);
    }

    @Override
    public void draw() {
        stream.print(Shape.SHAPE);
    }
}