package gildedrose;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GildedRoseTest {

	String          message;
	String          itemName;
	int             sellIn;
	int             quality;
	int             expectedSellIn;
	int             expectedQuality;
//	Item item;
//    List<Item> items;
    List<UpdatebleItem> items;

	public GildedRoseTest(String message,
                          String itemName,
                          int sellIn,
                          int quality,
                          int expectedSellIn,
                          int expectedQuality) {
		this.message = message;
		this.itemName = itemName;
		this.sellIn = sellIn;
		this.quality = quality;
		this.expectedSellIn = expectedSellIn;
		this.expectedQuality = expectedQuality;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.addAll(Arrays
				.asList(new Object[][] { {
						"At the end of each day our system lowers both quality and sell-in for every item",
						"Item with arbitrary name",
						5,
                        49,
                        reducedByOne(5),
                        reducedByOne(49) }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Aged Brie",
                        5,
                        49,
                        reducedByOne(5),
                        increasedByOne(49) }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Aged Brie",
                        4,
                        50,
                        reducedByOne(4),
                        unmodified(50) }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Sulfuras, Hand of Ragnaros",
                        5,
                        80,
                        unmodified(5),
                        unmodified(80) }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Backstage passes to a TAFKAL80ETC concert",
                        10,
                        30,
                        9,
                        32 }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Backstage passes to a TAFKAL80ETC concert",
                        5,
                        20,
                        4,
                        23 }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Backstage passes to a TAFKAL80ETC concert",
                        0,
                        10,
                        -1,
                        0 }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Backstage passes to a TAFKAL80ETC concert",
                        3,
                        48,
                        2,
                        50 }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Backstage passes to a TAFKAL80ETC concert",
                        11,
                        50,
                        10,
                        50 }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Conjured",
                        11,
                        30,
                        10,
                        28 }, {
                        "At the end of each day our system lowers both quality and sell-in for every item",
                        "Conjured",
                        10,
                        0,
                        9,
                        0 }}));
		return data;
	}

    private static int unmodified(int value) {
	    return value;
    }

    @Before
	public void setUp() {
//        items = new ArrayList<Item>();
        items = new ArrayList<UpdatebleItem>();
//        items.add(new Item(itemName, sellIn, quality));
        items.add(ItemFactory.getInstance(itemName, sellIn, quality));

		GildedRose.setItems(items);
	}

	@Test
	public void testQualityUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " Quality ", expectedQuality, (items.get(0)).getQuality());
}

	@Test
	public void testSellInUpdate() {
		GildedRose.updateQuality();
		assertEquals(message + " SellIn", expectedSellIn, (items.get(0)).getSellIn());
	}

	private static int reducedByOne(int value) {
	    return value - 1;
    }

    private static int increasedByOne(int value) {
        return value + 1;
    }

}
