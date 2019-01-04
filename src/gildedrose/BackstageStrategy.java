package gildedrose;

public class BackstageStrategy implements Strategy {
    public Item updateItem(Item item) {
        return new Item(
                item.name,
                item.sellIn - 1,
                QualityIncrease(item));
    }

    private static int QualityIncrease(Item item) {
        increaseQuality(item);

        if (item.getSellIn() < 11) {
            increaseQuality(item);
        }

        if (item.getSellIn() < 6) {
            increaseQuality(item);
        }

        if (item.getSellIn() - 1 == -1) {
            item.setQuality(0);
        }

        return item.quality;

    }

    private static void increaseQuality(Item item) {
        if (isQualityLessThanMax(item)) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private static boolean isQualityLessThanMax(Item item) {
        return item.getQuality() < 50;
    }

}
