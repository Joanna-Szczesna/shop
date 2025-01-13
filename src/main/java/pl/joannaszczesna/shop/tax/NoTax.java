package pl.joannaszczesna.shop.tax;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Primary
class NoTax implements TaxPolicy{
    @Override
    public BigDecimal countTax(BigDecimal price) {
        return BigDecimal.ZERO;
    }
}
