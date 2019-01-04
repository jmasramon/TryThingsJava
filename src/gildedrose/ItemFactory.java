package gildedrose;

public class ItemFactory {
    public static UpdatebleItem getInstance(String name, int sellIn, int quality) {
        switch (name) {
            case "Conjured":
                return new Conjured(name, sellIn, quality);
            case "Aged Brie":
                return new AgedBrie(name, sellIn, quality);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new Backstage(name, sellIn, quality);
            case "Sulfuras, Hand of Ragnaros":
                return new Sulfuras(name, sellIn, quality);
        }
        return new Standard(name, sellIn, quality);
    }
}
