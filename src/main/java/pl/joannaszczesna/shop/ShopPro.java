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
@Profile("pro")
@ConfigurationProperties(prefix = "shop-attribute-pro")
class ShopPro extends ShopStart {

//  @Value("shop-attribute-pro.tax")
    private int tax;
//  @Value("shop-attribute-pro.discount")
    private int discount;

    @Autowired
    ShopPro(PriceGenerator generator) {
        super(generator);
    }

    ShopPro setTax(int tax) {
        this.tax = tax;
        return this;
    }

    ShopPro setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public void addToBasket(String name, BigDecimal price) {
        System.out.println("addToBasket from " + ShopPro.class);

        BigDecimal countedTax = countFraction(price, tax);
        BigDecimal priceWithTax = price.add(countedTax);
        BigDecimal countedDiscount = countFraction(priceWithTax, discount);
        BigDecimal discountPrice = priceWithTax.subtract(countedDiscount);

        super.addToBasket(name, discountPrice);
    }

    private BigDecimal countFraction(BigDecimal price, int percentage) {
        return price.multiply(BigDecimal.valueOf(percentage))
                .divide(BigDecimal.valueOf(100),
                        new MathContext(2, RoundingMode.FLOOR));
    }
}
