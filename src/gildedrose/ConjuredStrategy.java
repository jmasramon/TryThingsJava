package gildedrose;

public class ConjuredStrategy implements Strategy {
    public Item updateItem(Item item) {
        return new Item(
                item.name,
                item.sellIn - 1,
                (item.quality - 2 >=0 ? item.quality - 2 : 0));
    }
}
