package gildedrose;

public abstract class UpdatebleItem extends Item implements Updatable{
    public UpdatebleItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }
}
