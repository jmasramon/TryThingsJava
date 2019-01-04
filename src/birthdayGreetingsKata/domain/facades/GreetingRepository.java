package birthdayGreetingsKata.domain.facades;

import birthdayGreetingsKata.domain.model.GreetingMessage;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface GreetingRepository {
//    void serializeGreetingsForAllEmployees(ArrayList<Employee> employees, XDate xDate) throws AddressException, MessagingException;
    void sendMessage(GreetingMessage message) throws AddressException, MessagingException;
}
