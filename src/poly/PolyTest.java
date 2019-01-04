package poly;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import junit.framework.TestCase;

//import poly.Poly;


public class PolyTest extends TestCase {
    private PrintStream stream;

    public void test_drawPoly_square() throws Exception {
        PrintStreamFake stream_fake = new PrintStreamFake(Shape.SQUARE);
        stream = stream_fake;
        Poly poly = new Square(stream);
        poly.draw();
    }

    public void test_drawPoly_triangle() throws Exception {
        PrintStreamFake stream_fake = new PrintStreamFake(Shape.SHAPE);
        stream = stream_fake;
        Poly poly = new AShape(stream);
        poly.draw();
    }

    public void test_drawPoly_rectangle() throws Exception {
        PrintStreamFake stream_fake = new PrintStreamFake(Shape.RECTANGLE);
        stream = stream_fake;
        Poly poly = new Rectangle(stream);
        poly.draw();
    }

    private static final class PrintStreamFake extends PrintStream {

        private Shape the_shape;

        public PrintStreamFake(Shape theShape) throws IOException {
            super(File.createTempFile("temp", "file"));
            this.the_shape = theShape;
        }

        @Override
        public void print(Object obj) {
            TestCase.assertEquals(obj, the_shape);
        }

    }
}
