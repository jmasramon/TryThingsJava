public class ChildrensMovie extends Movie{
    public ChildrensMovie(String title) {
        super(title);
        _price = PriceCode.CHILDRENS.getPrice();
    }

    @Override
    PriceCode getPriceCode() {
        return PriceCode.CHILDRENS;
    }

    @Override
    double getDurationCost(int daysRented) {
        if (daysRented > 3)
            return (daysRented - 3) * 1.5;
        return 0;
    }

    @Override
    double getFrequentRenterPointsBonus(int daysRented) {
        return 0;
    }
}
