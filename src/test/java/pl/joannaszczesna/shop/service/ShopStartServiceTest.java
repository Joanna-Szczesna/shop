package pl.joannaszczesna.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TestConfig.class)
@EnableAutoConfiguration
class ShopStartServiceTest {
    @Autowired
    private Shop shop;
    private static final BigDecimal FIXED_PRICE = BigDecimal.valueOf(100);

    @BeforeEach
    void setUp() {
        this.shop.clearCart();
        this.shop.addNewProduct("productName", 1, FIXED_PRICE);
        this.shop.addNewProduct("productName2", 2, FIXED_PRICE);
    }

    @Test
    void addOneProduct() {
        shop.addToCart(1);

        List<Product> cart = shop.getCart();
        BigDecimal countedPrice = shop.countSummaryPrice();

        assertEquals(1, cart.size());
        assertEquals(FIXED_PRICE, cart.getFirst().price());
        assertEquals(FIXED_PRICE, countedPrice);
    }

    @Test
    void addTwoProduct_printSummaryPrice() {
        shop.addToCart(1);
        shop.addToCart(2);

        BigDecimal actualPriceSum = shop.countSummaryPrice();
        BigDecimal expectedPriceSum = FIXED_PRICE.multiply(BigDecimal.valueOf(2));

        assertEquals(expectedPriceSum, actualPriceSum);
    }
}