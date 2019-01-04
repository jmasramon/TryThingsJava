package gildedrose;

public class GeneralStrategy implements Strategy {
    @Override
    public Item updateItem(Item item) {
        return new Item(
                item.name,
                item.sellIn - 1,
                decreaseQuality(item));
    }

    private static int decreaseQuality(Item item) {
        if (isQualityBiggerThanMin(item)){
            item.setQuality(item.getQuality() - 1);
        }
        return item.getQuality();
    }

    private static boolean isQualityBiggerThanMin(Item item) {
        return item.getQuality() > 0;
    }

}
