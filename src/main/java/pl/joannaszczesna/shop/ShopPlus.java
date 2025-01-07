package pl.joannaszczesna.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Profile("plus")
@ConfigurationProperties(prefix = "shop-attribute")
class ShopPlus extends ShopStart {

    //    @Value("shop-attribute.tax")
    private int tax;

    @Autowired
    ShopPlus(PriceGenerator generator) {
        super(generator);
    }

    ShopPlus setTax(int tax) {
        this.tax = tax;
        return this;
    }

    @Override
    public void addToBasket(String name, BigDecimal price) {
        System.out.println("addToBasket from " + ShopPlus.class);
        BigDecimal countedTax = price.multiply(BigDecimal.valueOf(tax))
                .divide(BigDecimal.valueOf(100),
                        new MathContext(2, RoundingMode.FLOOR));
        BigDecimal priceWithTax = price.add(countedTax);
        super.addToBasket(name, priceWithTax);
    }


}
