public class RegularMovie extends Movie{
    public RegularMovie(String title) {
        super(title);
        _price = PriceCode.REGULAR.getPrice();

    }

    @Override
    PriceCode getPriceCode() {
        return PriceCode.REGULAR;
    }

    @Override
    double getDurationCost(int daysRented) {
        if (daysRented > 2)
            return (daysRented - 2) * 1.5;
        return 0;
    }

    @Override
    double getFrequentRenterPointsBonus(int daysRented) {
        return 0;
    }
}
