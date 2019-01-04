package birthdayGreetingsKata.domain.model;

public class GreetingMessage {
    public String recipient ;
    public String body;
    public String subject;

    public GreetingMessage(Employee employee) {
        recipient = employee.getEmail();
        body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
        subject = "Happy Birthday!";
    }
}
