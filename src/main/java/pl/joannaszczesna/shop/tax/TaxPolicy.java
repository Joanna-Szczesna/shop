package pl.joannaszczesna.shop.tax;

import java.math.BigDecimal;

@FunctionalInterface
public interface TaxPolicy {
    BigDecimal countTax(BigDecimal price);
}
