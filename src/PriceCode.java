public enum PriceCode {
   REGULAR(2.0),
   NEW_RELEASE(0.0),
   CHILDRENS(1.5);

   private final double price;

    PriceCode(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
