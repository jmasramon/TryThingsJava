public interface Formatter {
    String frequentPoints(int frequentRenterPoints);

    String totalAmount(double totalAmount);

    String header(String name);

    String detail(Rental rental);

    static Formatter getFormatter() {
        return null;
    };
}
