package gildedrose;

public class AgedBrieStrategy implements Strategy {
    @Override
    public Item updateItem(Item item) {
        return new Item(
                item.name,
                item.sellIn - 1,
                increaseQuality(item));

    }

    private static int increaseQuality(Item item) {
        if (isQualityLessThanMax(item)) {
            item.setQuality(item.getQuality() + 1);
        }
        return item.quality;
    }

    private static boolean isQualityLessThanMax(Item item) {
        return item.getQuality() < 50;
    }
}
