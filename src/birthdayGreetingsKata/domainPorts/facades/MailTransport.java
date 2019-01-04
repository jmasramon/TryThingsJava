package birthdayGreetingsKata.domainPorts.facades;

import javax.mail.MessagingException;
import javax.mail.Message;

public interface MailTransport {
    void send(Message msg) throws MessagingException;
}
