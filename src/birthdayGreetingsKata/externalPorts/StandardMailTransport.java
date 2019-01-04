package birthdayGreetingsKata.externalPorts;

import birthdayGreetingsKata.domainPorts.facades.MailTransport;

import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.Transport;

public class StandardMailTransport implements MailTransport {
    @Override
    public void send(Message msg) throws MessagingException {
        Transport.send(msg);
    }
}
