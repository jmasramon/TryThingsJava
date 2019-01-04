package movieRental;


public class TextFormatter implements Formatter {
    public String frequentPoints(int frequentRenterPoints) {
        return "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
    }

    public String totalAmount(double totalAmount) {
        return "Amount owed is " + String.valueOf(totalAmount) + "\n";
    }

    public String header(String name) {
        return "movieRental.Rental Record for " + name + "\n";
    }

    public String detail(Rental rental) {
        return "\t" + rental.getMovie().getTitle() + "\t" +
                String.valueOf(rental.getPrice()) + "\n";

    }

}
