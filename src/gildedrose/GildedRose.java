package gildedrose;

import java.util.HashMap;
import java.util.List;

public class GildedRose {

    private enum Names {
        CONJURED("Conjured"),
        BRIE("Aged Brie"),
        BACKSTAGE("Backstage passes to a TAFKAL80ETC concert"),
        SULFURAS("Sulfuras, Hand of Ragnaros");

        protected String name;
        Names(String name) {
            this.name = name;
        }
    }

//    private static List<Item> items = null;
    private static List<UpdatebleItem>           items = null;
	private static HashMap<String, Strategy> strategies;
    static {
	    strategies = new HashMap<>();
        strategies.put(Names.CONJURED.name, new ConjuredStrategy());
        strategies.put(Names.BRIE.name, new AgedBrieStrategy());
        strategies.put(Names.BACKSTAGE.name, new BackstageStrategy());
        strategies.put(Names.SULFURAS.name, new SulfurasStrategy());
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

/*
		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateQuality();
*/
	}

	public static void updateQuality() {
	    int index = 0;
        for (UpdatebleItem item: items) {
//        for (Item item: items) {
//			String itemName = item.getName();
//
//			updateQuality(item, itemName);
//
//			updateSellIn(item, itemName);
//
//			if (isSellInOver(item)) {
//				adaptQualityToSellInOver(item, itemName);
//			}

//			if (isConjured(itemName)) {
//                items.set(index, strategies.get(Names.CONJURED.name).updateItem(item));
//            }

//            Strategy strategy = strategies.getOrDefault(item.getName(), new GeneralStrategy());
//            Item newItem = strategy.updateItem(item);
//            items.set(index, newItem);
//            index++;
            item.updateItem();
		}
	}

//	private static void updateQuality(Item item, String itemName) {
//		if (isStandardItem(itemName)) {
//			decreaseQuality(item);
//		} else { // nonstandard items
//			if (isConjured(itemName)) {
//			} else {
//				increaseQuality(item);
//
//				if (isBackstagePass(itemName) ) {
//					extraBackStagePassQualityIncrease(item);
//				}
//			}
//		}
//	}
//
//	private static void updateSellIn(Item item, String itemName) {
//		if (!isSulfuras(itemName) && !isConjured(itemName)) {
//			decreaseSellIn(item);
//		}
//	}
//
//	private static void adaptQualityToSellInOver(Item item, String itemName) {
//		if (isAgedBrie(itemName)) {
//			increaseQuality(item);
//		} else { // not brie
//			if (!isBackstagePass(itemName)) {
//				if (!isSulfuras(itemName)) {
//					decreaseQuality(item);
//				}
//			} else {
//				item.setQuality(0);
//			}
//		}
//	}
//
//	private static void decreaseQuality(Item item) {
//		if (isQualityBiggerThanMin(item)){
//			item.setQuality(item.getQuality() - 1);
//		}
//	}
//
//	private static void increaseQuality(Item item) {
//		if (isQualityLessThanMax(item)) {
//			item.setQuality(item.getQuality() + 1);
//		}
//	}
//
//	private static void decreaseSellIn(Item item) {
//		item.setSellIn(item.getSellIn() - 1);
//	}
//
//	private static void extraBackStagePassQualityIncrease(Item item) {
//		if (item.getSellIn() < 11) {
//			increaseQuality(item);
//		}
//
//		if (item.getSellIn() < 6) {
//			increaseQuality(item);
//		}
//	}
//
//	private static boolean isSellInOver(Item item) {
//		return item.getSellIn() < 0;
//	}
//
//	private static boolean isQualityBiggerThanMin(Item item) {
//		return item.getQuality() > 0;
//	}
//
//	private static boolean isQualityLessThanMax(Item item) {
//		return item.getQuality() < 50;
//	}
//
//	private static boolean isStandardItem(String itemName) {
//		return (!isAgedBrie(itemName)) &&
//				!isBackstagePass(itemName) &&
//				!isSulfuras(itemName) &&
//				!isConjured(itemName);
//	}
//
//    private static boolean isConjured(String itemName) {
//        return Names.CONJURED.name.equals(itemName);
//    }
//
//    private static boolean isBackstagePass(String itemName) {
//		return Names.BACKSTAGE.name.equals(itemName);
//	}
//
//	private static boolean isSulfuras(String itemName) {
//		return Names.SULFURAS.name.equals(itemName);
//	}
//
//	private static boolean isAgedBrie(String itemName) {
//		return Names.BRIE.name.equals(itemName);
//	}

	/*
	 * minimal addition to ensure testability
	 */
//    public static void setItems(List<Item> items) {
//        GildedRose.items = items;
//    }
    public static void setItems(List<UpdatebleItem> items) {
        GildedRose.items = items;
    }
}