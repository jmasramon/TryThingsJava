package gildedrose;

public class Standard extends UpdatebleItem {
    public Standard(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        sellIn -= 1;
        decreaseQuality();
    }

    private int decreaseQuality() {
        if (isQualityBiggerThanMin()){
            setQuality(getQuality() - 1);
        }
        return getQuality();
    }

    private boolean isQualityBiggerThanMin() {
        return quality > 0;
    }

}
