public class NewMovie extends Movie {
    public NewMovie(String title) {
        super(title);
        _price = PriceCode.NEW_RELEASE.getPrice();
    }

    @Override
    PriceCode getPriceCode() {
        return PriceCode.NEW_RELEASE;
    }

    @Override
    double getDurationCost(int daysRented) {
        return daysRented * 3;
    }

    @Override
    double getFrequentRenterPointsBonus(int daysRented) {
        if (daysRented > 1) {
            return 1;
        }
        return 0;
    }


}