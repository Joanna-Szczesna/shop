package pl.joannaszczesna.shop.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Primary
class NoDiscount implements DiscountPolicy {
    @Override
    public BigDecimal countDiscount(BigDecimal price) {
        return BigDecimal.ZERO;
    }
}
