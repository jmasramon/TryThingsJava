package birthdayGreetingsKata.domainPorts;

import birthdayGreetingsKata.domain.model.Employee;
import birthdayGreetingsKata.domain.exceptions.EmployeeAccessException;
import birthdayGreetingsKata.domain.facades.EmployeeRepository;
import birthdayGreetingsKata.domainPorts.facades.LineReader;

import java.util.ArrayList;


public class TextFileEmployeeRepository implements EmployeeRepository {
    LineReader in;

    public TextFileEmployeeRepository(LineReader in) {
        this.in  = in;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() throws EmployeeAccessException {
        String              line;
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            line = in.readLine(); // skip header
            while ((line = in.readLine()) != null) {
                String[] employeeData = line.split(", ");
                Employee employee     = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                employees.add(employee);
            }
        } catch (Exception e) {
            throw new EmployeeAccessException();
        }
        return employees;
    }
}
