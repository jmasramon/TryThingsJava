package birthdayGreetingsKata.clients;

import birthdayGreetingsKata.domain.exceptions.EmployeeAccessException;
import birthdayGreetingsKata.domain.exceptions.GreetingCouldNotBeSendException;
import birthdayGreetingsKata.externalPorts.StandardLineReader;
import birthdayGreetingsKata.externalPorts.StandardMailTransport;
import birthdayGreetingsKata.domain.BirthdayService;
import birthdayGreetingsKata.domain.model.XDate;
import birthdayGreetingsKata.domainPorts.EmailGreetingRepository;
import birthdayGreetingsKata.domainPorts.TextFileEmployeeRepository;

import java.io.*;

public class Main {

	public static void main(String[] args) throws EmployeeAccessException, GreetingCouldNotBeSendException, IOException {


		BirthdayService service = new BirthdayService(
		        new TextFileEmployeeRepository(new StandardLineReader("employee_data.txt")),
                new EmailGreetingRepository("localhost", 25, new StandardMailTransport()));

		service.sendGreetings(new XDate());
	}

}
