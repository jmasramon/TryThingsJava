package movieRental;

import org.junit.Assert;
import org.junit.Test;


public class CustomerTest {

    @Test
    public void getNameShouldReturnCustomersName() {
        Customer customer = new Customer("Jordi");

        Assert.assertEquals("Jordi", customer.getName());
    }

    @Test
    public void addRentalShouldAddARental() {
        Customer customer = new Customer("Jordi");

        Assert.assertEquals(true, customer.getRentals().isEmpty());

        customer.addRental(new Rental(new RegularMovie("Godfather"), 10));
        Assert.assertEquals(false, customer.getRentals().isEmpty());
        Assert.assertEquals(1, customer.getRentals().size());

        customer.addRental(new Rental(new NewMovie("Godfather"), 10));
        Assert.assertEquals(false, customer.getRentals().isEmpty());
        Assert.assertEquals(2, customer.getRentals().size());
    }

    @Test
    public void statementShouldProduceAStatement() {
        Customer customer = new Customer("Jordi");

        customer.addRental(new Rental(new RegularMovie("Godfather"), 10));
        customer.addRental(new Rental(new NewMovie("El padrino"), 3));
        customer.addRental(new Rental(new ChildrensMovie("The piano"), 5));
        Assert.assertEquals("movieRental.Rental Record for Jordi\n" +
                        "\tGodfather\t14.0\n" +
                        "\tEl padrino\t9.0\n" +
                        "\tThe piano\t4.5\n" +
                        "Amount owed is 27.5\n" +
                        "You earned 4 frequent renter points",
                customer.textStatement());

    }

    @Test
    public void htmlStatementShouldProduceAnHtmlStatement() {
        Customer customer = new Customer("Jordi");

        customer.addRental(new Rental(new RegularMovie("Godfather"), 10));
        customer.addRental(new Rental(new NewMovie("El padrino"), 3));
        customer.addRental(new Rental(new ChildrensMovie("The piano"), 5));
        Assert.assertEquals("<H1>movieRental.Rental Record for Jordi</H1>" +
                        "<br>Godfather<br>14.0" +
                        "<br>El padrino<br>9.0" +
                        "<br>The piano<br>4.5" +
                        "Amount owed is 27.5" +
                        "You earned 4 frequent renter points",
                customer.htmlStatement());

    }
}
