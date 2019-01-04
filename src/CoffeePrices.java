public enum CoffeePrices {
    DARK_ROAST(0.75f),
    HOUSE_BLEND(0.85f),
    MILK(0.5f),
    MOKA(1.5f),
    MID(1.25F);

    public float price;

    CoffeePrices(float price) {
        this.price = price;
    }
}
