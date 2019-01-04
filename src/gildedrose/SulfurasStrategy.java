package gildedrose;

public class SulfurasStrategy implements Strategy {
    @Override
    public Item updateItem(Item item) {
        return new Item(
                item.name,
                item.sellIn,
                80);
    }
}
