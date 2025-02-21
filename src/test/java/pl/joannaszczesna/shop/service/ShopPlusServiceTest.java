package pl.joannaszczesna.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("plus")
@EnableAutoConfiguration
class ShopPlusServiceTest {
    private static final BigDecimal FIXED_PRICE = BigDecimal.valueOf(100.0);

    @Autowired
    Shop shopPlus;

    @BeforeEach
    void setUp() {
        this.shopPlus.clearCart();
        this.shopPlus.addNewProduct("productName", 1, FIXED_PRICE);
    }

    @Test
    void addOneProduct_summaryPriceWithTax() {
        shopPlus.addToCart(1);
        BigDecimal actualPrice = shopPlus.countSummaryPrice();

        assertEquals(122.0, actualPrice.doubleValue());
    }
}