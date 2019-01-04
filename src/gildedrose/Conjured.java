package gildedrose;

public class Conjured extends UpdatebleItem {
    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        sellIn -= 1;
        quality = quality - 2 >=0 ? quality - 2 : 0;
    }
}
