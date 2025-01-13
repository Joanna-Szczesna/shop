package pl.joannaszczesna.shop.tax;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Profile("plus")
@ConfigurationProperties(prefix = "shop-attribute")
class TaxPlus implements TaxPolicy{

    private int tax;

    TaxPlus setTax(int tax) {
        this.tax = tax;
        return this;
    }

    @Override
    public BigDecimal countTax(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(tax))
                .divide(BigDecimal.valueOf(100),
                        new MathContext(2, RoundingMode.FLOOR));
    }
}
