package pl.joannaszczesna.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.joannaszczesna.shop.product.PriceGenerator;
import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
@Profile("pro")
@ConfigurationProperties(prefix = "shop-attribute-pro")
class ShopProService extends ShopStartService {

//  @Value("shop-attribute-pro.tax")
    private int tax;
//  @Value("shop-attribute-pro.discount")
    private int discount;

    @Autowired
    ShopProService(PriceGenerator generator) {
        super(generator);
    }

    ShopProService setTax(int tax) {
        this.tax = tax;
        return this;
    }

    ShopProService setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public void addToBasket(Product product) {
        System.out.println("addToBasket from " + ShopProService.class);

        BigDecimal countedTax = countFraction(product.price(), tax);
        BigDecimal priceWithTax = product.price().add(countedTax);
        BigDecimal countedDiscount = countFraction(priceWithTax, discount);
        BigDecimal discountPrice = priceWithTax.subtract(countedDiscount);

        super.addToBasket(product);
    }

    private BigDecimal countFraction(BigDecimal price, int percentage) {
        return price.multiply(BigDecimal.valueOf(percentage))
                .divide(BigDecimal.valueOf(100),
                        new MathContext(2, RoundingMode.FLOOR));
    }
}
