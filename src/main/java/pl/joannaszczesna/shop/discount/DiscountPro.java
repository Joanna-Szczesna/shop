package pl.joannaszczesna.shop.discount;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Profile("pro")
@ConfigurationProperties(prefix = "shop-attribute-pro")
class DiscountPro implements DiscountPolicy {
    private int discount;

    DiscountPro setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public BigDecimal countDiscount(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(discount))
                .divide(BigDecimal.valueOf(100),
                        new MathContext(2, RoundingMode.FLOOR));
    }

}
