package pl.joannaszczesna.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.joannaszczesna.shop.product.PriceGenerator;
import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("start")
class ShopStartService implements Shop {
    private final PriceGenerator generator;
    private final List<Product> basket;

    @Autowired
    ShopStartService(PriceGenerator generator) {
        this.generator = generator;
        this.basket = new ArrayList<>();
    }

    @Override
    public void addToBasket(Product product) {
        this.basket.add(product);
    }

    @Override
    public List<Product> getBasket() {
        return new ArrayList<>(this.basket);
    }

    @Override
    public BigDecimal countSummaryPrice() {
        return this.basket.stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public String getInfo() {

       return  countSummaryPrice().toString();


    }

    BigDecimal generatePrice() {
        return generator.generatePrice();
    }
}

