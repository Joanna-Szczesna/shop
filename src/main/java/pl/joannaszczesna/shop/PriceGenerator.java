package pl.joannaszczesna.shop;

import java.math.BigDecimal;

@FunctionalInterface
public interface PriceGenerator {
    BigDecimal generatePrice();
}
