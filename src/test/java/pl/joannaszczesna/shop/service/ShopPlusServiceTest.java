package pl.joannaszczesna.shop.service;

import org.junit.jupiter.api.Test;
import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class ShopPlusServiceTest {
    private static final BigDecimal FIXED_PRICE = BigDecimal.valueOf(15.0);
    private static final int tax = 22;

    @Test
    void addOneProduct_summaryPriceWithTax() {
        ShopPlusService shopPlus = new ShopPlusService(() -> FIXED_PRICE);
        BigDecimal generatedPrice = shopPlus.generatePrice();
        shopPlus.addToBasket(new Product("item", generatedPrice));

        BigDecimal countedTax = FIXED_PRICE.multiply(BigDecimal.valueOf(tax))
                .divide(BigDecimal.valueOf(100),
                        new MathContext(2, RoundingMode.FLOOR));
        BigDecimal expectedPrice = FIXED_PRICE.add(countedTax);

        BigDecimal actualPrice = shopPlus.countSummaryPrice();

        assertEquals(expectedPrice, actualPrice);
    }
}