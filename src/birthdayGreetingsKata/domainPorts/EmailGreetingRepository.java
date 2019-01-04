package birthdayGreetingsKata.domainPorts;

import birthdayGreetingsKata.domain.model.GreetingMessage;
import birthdayGreetingsKata.domain.facades.GreetingRepository;
import birthdayGreetingsKata.domainPorts.facades.MailTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGreetingRepository implements GreetingRepository {
    String        smtpHost;
    int           smtpPort;
    MailTransport transport;

    public EmailGreetingRepository(String smtpHost, int smtpPort, MailTransport transport) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.transport = transport;
    }

//    @Override
//    public void serializeGreetingsForAllEmployees(ArrayList<Employee> employees, XDate xDate ) throws AddressException, MessagingException {
//        for (Employee employee: employees) {
//            if (employee.isBirthday(xDate)) {
//
//                GreetingMessage message = new GreetingMessage(employee);
//                sendMessage(message);
//            }
//
//        }
//
//    }

    @Override
    public void sendMessage(GreetingMessage message) throws AddressException, MessagingException {
        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        Session session = Session.getInstance(props, null);

        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("sender@here.com"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(message.recipient));
        msg.setSubject(message.subject);
        msg.setText(message.body);

        // Send the message
        transport.send(msg);
    }

}
