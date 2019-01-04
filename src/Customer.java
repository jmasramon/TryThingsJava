import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String         _name;
    private Vector<Rental> _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    }


    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public Vector<Rental> getRentals() {
        return _rentals;
    }

    public String textStatement() {
        return statement(new TextFormatter());
    }

    public String htmlStatement() {
        return statement(new HTMLFormatter());
    }

    private String statement(Formatter formatter) {
        double      totalAmount          = 0;
        int         frequentRenterPoints = 0;
        Enumeration rentals              = _rentals.elements(); // feature envy
        String      result               = formatter.header(getName());

        while (rentals.hasMoreElements()) {
            Rental rental       = (Rental) rentals.nextElement();

            result += formatter.detail(rental);

            frequentRenterPoints += rental.getFrequentRenterPoints();
            totalAmount += rental.getPrice();;
        }

        //add footer lines
        result += formatter.totalAmount(totalAmount);
        result += formatter.frequentPoints(frequentRenterPoints);

        return result;
    }
}
