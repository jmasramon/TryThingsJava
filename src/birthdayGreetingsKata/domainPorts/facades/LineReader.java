package birthdayGreetingsKata.domainPorts.facades;

import java.io.IOException;

public interface LineReader {
    public String readLine() throws IOException;
}
