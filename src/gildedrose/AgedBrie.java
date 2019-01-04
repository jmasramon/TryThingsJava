package gildedrose;

public class AgedBrie extends UpdatebleItem {
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        sellIn -= 1;
        increaseQuality();

    }

    private void increaseQuality() {
        if (isQualityLessThanMax()) {
            setQuality(getQuality() + 1);
        }
    }

    private boolean isQualityLessThanMax() {
        return getQuality() < 50;
    }
}
