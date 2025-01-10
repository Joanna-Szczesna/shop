package pl.joannaszczesna.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopStartServiceTest {

    private ShopStartService shop;
    private static final BigDecimal FIXED_PRICE = BigDecimal.valueOf(15.0);

    @BeforeEach
    void setUp() {
        this.shop = new ShopStartService(() -> FIXED_PRICE);
    }

    @Test
    void addOneProduct() {
        BigDecimal generatedPrice = shop.generatePrice();
        String itemName = "item";
        shop.addToBasket(new Product(itemName, generatedPrice));

        List<Product> basket = shop.getBasket();

        assertEquals(basket.size(), 1);
        assertEquals(basket.getFirst().price(), FIXED_PRICE);
    }

    @Test
    void addTwoProduct_printSummaryPrice() {
        BigDecimal generatedPrice = shop.generatePrice();
        String itemName = "item";
        String itemNameSecond = "item2";
        shop.addToBasket(new Product(itemName, generatedPrice));
        shop.addToBasket(new Product(itemNameSecond, generatedPrice));

        BigDecimal actualPriceSum = shop.countSummaryPrice();
        BigDecimal expectedPriceSum = FIXED_PRICE.multiply(BigDecimal.valueOf(2));

        assertEquals(expectedPriceSum, actualPriceSum);
    }
}