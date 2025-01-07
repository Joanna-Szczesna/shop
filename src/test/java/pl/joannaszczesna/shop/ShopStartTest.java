package pl.joannaszczesna.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopStartTest {

    private ShopStart shop;
    private static final BigDecimal FIXED_PRICE = BigDecimal.valueOf(15.0);

    @BeforeEach
    void setUp() {
        this.shop = new ShopStart(() -> FIXED_PRICE);
    }

    @Test
    void addOneProduct() {
        BigDecimal generatedPrice = shop.generatePrice();
        String itemName = "item";
        shop.addToBasket(itemName, generatedPrice);

        Map<String, BigDecimal> basket = shop.getBasket();

        assertEquals(basket.size(), 1);
        assertEquals(basket.get(itemName), FIXED_PRICE);
    }

    @Test
    void addTwoProduct_printSummaryPrice() {
        BigDecimal generatedPrice = shop.generatePrice();
        String itemName = "item";
        String itemNameSecond = "item2";
        shop.addToBasket(itemName, generatedPrice);
        shop.addToBasket(itemNameSecond, generatedPrice);

        String actualPriceSum = shop.printPriceSummary();
        String expectedPriceSum = String.valueOf( FIXED_PRICE.multiply(BigDecimal.valueOf(2)));

        assertEquals(actualPriceSum, expectedPriceSum);
    }
}