package birthdayGreetingsKata.domain;

import birthdayGreetingsKata.domain.exceptions.EmployeeAccessException;
import birthdayGreetingsKata.domain.exceptions.GreetingCouldNotBeSendException;
import birthdayGreetingsKata.domain.facades.EmployeeRepository;
import birthdayGreetingsKata.domain.facades.GreetingRepository;
import birthdayGreetingsKata.domain.model.Employee;
import birthdayGreetingsKata.domain.model.GreetingMessage;
import birthdayGreetingsKata.domain.model.XDate;

import java.util.ArrayList;

public class BirthdayService {

    private final EmployeeRepository employeeRepository;
    private final GreetingRepository greetingRepository;

    public BirthdayService(EmployeeRepository employeeRepository, GreetingRepository greetingRepository) {
		this.employeeRepository = employeeRepository;
		this.greetingRepository = greetingRepository;
	}

	public void sendGreetings(XDate xDate) throws EmployeeAccessException, GreetingCouldNotBeSendException {

        ArrayList<Employee> employees = employeeRepository.getAllEmployees();
        serializeGreetingsForAllEmployees(employees, xDate);

	}

    private void serializeGreetingsForAllEmployees(ArrayList<Employee> employees, XDate xDate ) throws GreetingCouldNotBeSendException {
        for (Employee employee: employees) {
            if (employee.isBirthday(xDate)) {

                GreetingMessage message = new GreetingMessage(employee);
                try {
                    greetingRepository.sendMessage(message);
                } catch (Exception e) {
                    throw new GreetingCouldNotBeSendException();
                }
            }

        }

    }


}
