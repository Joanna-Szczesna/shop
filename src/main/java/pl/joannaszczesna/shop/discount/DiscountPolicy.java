package pl.joannaszczesna.shop.discount;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@FunctionalInterface
public interface DiscountPolicy {
    BigDecimal countDiscount(BigDecimal price);
}
