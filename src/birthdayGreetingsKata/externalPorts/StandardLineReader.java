package birthdayGreetingsKata.externalPorts;

import birthdayGreetingsKata.domainPorts.facades.LineReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StandardLineReader implements LineReader {
    String          fileName;
    BufferedReader  in;

    public StandardLineReader(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        in = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public String readLine() throws IOException {
        return in.readLine();
    }
}
