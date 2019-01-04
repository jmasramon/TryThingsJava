package movieRental;

abstract public class Movie {
    private   String _title;
    protected double _price;

    public Movie(String title) {
        _title = title;
    }

    public String getTitle() {
        return _title;
    }

    public Double getPrice() {
        return _price;
    };

    abstract PriceCode getPriceCode();
    abstract double getDurationCost(int daysRented);
    abstract double getFrequentRenterPointsBonus(int daysRented);
}
