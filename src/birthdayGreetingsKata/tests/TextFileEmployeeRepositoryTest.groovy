package birthdayGreetingsKata.tests

import birthdayGreetingsKata.domain.model.Employee
import birthdayGreetingsKata.domainPorts.facades.LineReader
import birthdayGreetingsKata.domainPorts.TextFileEmployeeRepository

class TextFileEmployeeRepositoryTest extends GroovyTestCase {
    class FakeLineReader implements LineReader {
        String[] lines = [
                "last_name, first_name, date_of_birth, email",
                "Doe, John, 1982/10/08, john.doe@foobar.com",
                "Ann, Mary, 1975/03/11, mary.ann@foobar.com"]
        int curLine = 0

        @Override
        String readLine() throws IOException {
            if (curLine<lines.length) {
                return lines[curLine++]
            }
            return null;
        }
    }

    void setUp() {
        super.setUp()
    }

    void testGetAllEmployees() {
        def allEmployees = new TextFileEmployeeRepository(new FakeLineReader()).allEmployees

        assertEquals(2, allEmployees.size())
        assertEquals(new Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com"), allEmployees.get(0))
    }
}
