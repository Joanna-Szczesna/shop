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
@ActiveProfiles("pro")
@EnableAutoConfiguration
class ShopProServiceTest {
    private static final BigDecimal FIXED_PRICE = BigDecimal.valueOf(100.0);

    @Autowired
    Shop shopPro;

    @BeforeEach
    void setUp() {
        this.shopPro.clearCart();
        this.shopPro.addNewProduct("productName", 1, FIXED_PRICE);
    }
    @Test
    void addOneProduct_summaryPriceWithTaxAndDiscount() {
        shopPro.addToCart(1);
        BigDecimal actualPrice = shopPro.countSummaryPrice();

        assertEquals(117.0, actualPrice.doubleValue());
    }
}