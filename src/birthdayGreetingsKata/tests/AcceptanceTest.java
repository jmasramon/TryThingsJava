package birthdayGreetingsKata.tests;

import static org.junit.Assert.*;

import birthdayGreetingsKata.domain.BirthdayService;
import birthdayGreetingsKata.domain.model.XDate;
import birthdayGreetingsKata.domainPorts.EmailGreetingRepository;
import birthdayGreetingsKata.domainPorts.TextFileEmployeeRepository;
import birthdayGreetingsKata.externalPorts.StandardLineReader;
import birthdayGreetingsKata.externalPorts.StandardMailTransport;
import org.junit.*;

import com.dumbster.smtp.*;

public class AcceptanceTest {

	private static final int              NONSTANDARD_PORT = 9999;
	private              BirthdayService  birthdayService;
	private              SimpleSmtpServer mailServer;

	@Before
	public void setUp() throws Exception {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		birthdayService = new BirthdayService(new TextFileEmployeeRepository(new StandardLineReader("employee_data.txt")), new EmailGreetingRepository("localhost", NONSTANDARD_PORT, new StandardMailTransport()));
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		birthdayService.sendGreetings(new XDate("2008/10/08"));

		assertEquals("message not sent?", 1, mailServer.getReceivedEmailSize());
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/01/01"));

		assertEquals("what? messages?", 0, mailServer.getReceivedEmailSize());
	}
}
