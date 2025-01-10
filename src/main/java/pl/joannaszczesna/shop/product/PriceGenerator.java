package pl.joannaszczesna.shop.product;

import java.math.BigDecimal;

@FunctionalInterface
public interface PriceGenerator {
    BigDecimal generatePrice();
}
