package movieRental;

public class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public double getPrice() {
        double amount = getMovie().getPrice();

        amount = getDurationCost(amount);
        return amount;
    }

    private double getDurationCost(double amount) {
        return amount + getMovie().getDurationCost(getDaysRented());
    }

    public double getFrequentRenterPoints() {
        double frequentRenterPoints = 0;

        // every time you rent you get a point
        frequentRenterPoints++;

        // add frequent renter points bonus for a two day new release rental
        frequentRenterPoints += getMovie().getFrequentRenterPointsBonus(getDaysRented());

        return frequentRenterPoints;

    }

}