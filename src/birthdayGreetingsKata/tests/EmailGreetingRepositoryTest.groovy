package birthdayGreetingsKata.tests

import birthdayGreetingsKata.domain.model.Employee
import birthdayGreetingsKata.domain.model.GreetingMessage
import birthdayGreetingsKata.domain.facades.GreetingRepository
import birthdayGreetingsKata.domainPorts.EmailGreetingRepository
import birthdayGreetingsKata.domainPorts.facades.MailTransport

import javax.mail.Message
import javax.mail.MessagingException

class EmailGreetingRepositoryTest extends GroovyTestCase {
    ArrayList<Message> sentMsgs = new ArrayList<>()

    class FakeMailTransport implements MailTransport {

        @Override
        void send(Message msg) throws MessagingException {
            sentMsgs.add(msg)
        }
    }

    void setUp() {
        super.setUp()
    }

    void testSerializeGreetingsForAllEmployees() {
        GreetingRepository greeter = new EmailGreetingRepository("localhost", 25, new FakeMailTransport())

        greeter.sendMessage(new GreetingMessage(new Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com")))

        assertEquals(1, sentMsgs.size())

//        assertEquals("", sentMsgs[0].getFrom()[0])
//        assertEquals("", sentMsgs[0].getRecipients()[0])
        assertEquals("Happy Birthday!", sentMsgs[0].getSubject())
        assertEquals("Happy Birthday, dear John!", sentMsgs[0].getContent())
    }
}
