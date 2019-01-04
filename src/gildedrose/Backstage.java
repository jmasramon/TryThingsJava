package gildedrose;

public class Backstage extends UpdatebleItem {
    public Backstage(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        QualityIncrease();
        sellIn -= 1;
    }

    private void QualityIncrease() {
        increaseQuality();

        if (getSellIn() < 11) {
            increaseQuality();
        }

        if (getSellIn() < 6) {
            increaseQuality();
        }

        if (getSellIn() - 1 == -1) {
            setQuality(0);
        }

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
