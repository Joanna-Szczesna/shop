package pl.joannaszczesna.shop.tax;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Primary
@Profile("start")
class NoTax implements TaxPolicy{
    @Override
    public BigDecimal countTax(BigDecimal price) {
        return BigDecimal.ZERO;
    }
}
