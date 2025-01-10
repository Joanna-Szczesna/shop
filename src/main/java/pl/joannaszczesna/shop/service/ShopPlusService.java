package pl.joannaszczesna.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.joannaszczesna.shop.product.PriceGenerator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Profile("plus")
@ConfigurationProperties(prefix = "shop-attribute")
class ShopPlusService extends ShopStartService {

    private int tax;

    @Autowired
    ShopPlusService(PriceGenerator generator) {
        super(generator);
    }

    ShopPlusService setTax(int tax) {
        this.tax = tax;
        return this;
    }

    @Override
    public BigDecimal countSummaryPrice() {
        BigDecimal priceWithoutTax = super.countSummaryPrice();
        BigDecimal countedTax = priceWithoutTax.multiply(BigDecimal.valueOf(tax))
                .divide(BigDecimal.valueOf(100),
                        new MathContext(2, RoundingMode.FLOOR));

        return priceWithoutTax.add(countedTax);
    }
}
