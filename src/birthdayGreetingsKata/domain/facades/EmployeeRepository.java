package birthdayGreetingsKata.domain.facades;

import birthdayGreetingsKata.domain.model.Employee;
import birthdayGreetingsKata.domain.exceptions.EmployeeAccessException;

import java.util.ArrayList;

public interface EmployeeRepository {
    ArrayList<Employee> getAllEmployees()  throws EmployeeAccessException;
}
