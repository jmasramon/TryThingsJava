package movieRental;

import movieRental.Rental;

public class HTMLFormatter implements Formatter {
    public String frequentPoints(int frequentRenterPoints) {
        return "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
    }

    public String totalAmount(double totalAmount) {
        return "Amount owed is " + String.valueOf(totalAmount);
    }

    public String header(String name) {
        return "<H1>movieRental.Rental Record for " + name + "</H1>";
    }

    public String detail(Rental rental) {
        return "<br>" + rental.getMovie().getTitle() + "<br>" +
                String.valueOf(rental.getPrice());

    }

}
