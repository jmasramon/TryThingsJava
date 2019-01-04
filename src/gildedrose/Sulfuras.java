package gildedrose;

public class Sulfuras extends UpdatebleItem {
    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItem() {
        quality = 80;
    }
}
