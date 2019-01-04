package starbuzz

class BeverageTest extends GroovyTestCase {
    Beverage houseBlend, darkRoastWithMilk
    void setUp() {
        super.setUp()
        houseBlend = new HouseBlend()
        darkRoastWithMilk = new DarkRoastWithMilk()
    }

    void testGetDescription() {
        assertEquals("House Blend", houseBlend.getDescription())
        assertEquals("Dark Roast wiht milk", darkRoastWithMilk.getDescription())

        GroovyTestCase.assertEquals("Dark Roast, plus Milk", new WithMilk(new DarkRoast()).getDescription())
        GroovyTestCase.assertEquals("Dark Roast, plus Mocka", new WithMocka(new DarkRoast()).getDescription())
        GroovyTestCase.assertEquals("Dark Roast, plus Milk, plus Mocka", new WithMocka(new WithMilk(new DarkRoast())).getDescription())
        GroovyTestCase.assertEquals("Dark Roast, plus Milk, plus Mocka, plus Mocka", new WithMocka(new WithMocka(new WithMilk(new DarkRoast()))).getDescription())
        GroovyTestCase.assertEquals("starbuzz.Mid Dark Roast, plus Milk, plus Mocka, plus Mocka", new Mid(new WithMocka(new WithMocka(new WithMilk(new DarkRoast())))).getDescription())}

    void testCost() {
        assertEquals(0.85f, houseBlend.cost())
        assertEquals(1.25f, darkRoastWithMilk.cost())

        assertEquals(1.25f, new WithMilk(new DarkRoast()).cost())
        assertEquals(2.25f, new WithMocka(new DarkRoast()).cost())
        assertEquals(2.75f, new WithMocka(new WithMilk(new DarkRoast())).cost())
        assertEquals(4.25f, new WithMocka(new WithMocka(new WithMilk(new DarkRoast()))).cost())

        assertEquals(5.3125, new Mid(new WithMocka(new WithMocka(new WithMilk(new DarkRoast())))).cost())
    }
}
