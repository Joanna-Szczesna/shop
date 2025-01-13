package pl.joannaszczesna.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.joannaszczesna.shop.product.Product;
import pl.joannaszczesna.shop.product.ProductPriceGenerator;
import pl.joannaszczesna.shop.service.Shop;

import java.math.BigDecimal;
import java.util.stream.IntStream;

@Component
class RunShop {

    private final Shop shop;
    private final ProductPriceGenerator generator;

    @Autowired
    public RunShop(Shop shop) {
        this.shop = shop;
        this.generator = new ProductPriceGenerator();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        IntStream.rangeClosed(1, 5)
                .forEach(n-> shop.addNewProduct(
                         "item"+n, n, generatePrice()));

        IntStream.rangeClosed(1, 5)
                .forEach(shop::addToCart);


        System.out.println(shop.getInfo());
    }
    BigDecimal generatePrice() {
        return generator.generatePrice();
    }
}
