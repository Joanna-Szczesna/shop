package pl.joannaszczesna.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
@Profile("start")
class ShopStart implements Shop {
    private PriceGenerator generator;
    private Map<String, BigDecimal> basket;

    @Autowired
    ShopStart(PriceGenerator generator) {
        this.generator = generator;
        this.basket = new HashMap<>();
    }

    @Override
    public void addToBasket(String name, BigDecimal price) {
        this.basket.put(name, price);
    }

    @Override
    public Map<String, BigDecimal> getBasket() {
        return new HashMap<>(this.basket);
    }

    @Override
    public String printPriceSummary() {
        BigDecimal sum = this.basket.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return String.valueOf(sum);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void app() {
        List<String> names = IntStream.rangeClosed(1, 5).mapToObj(i -> "item" + i).toList();
        names.forEach(n-> addToBasket(n, generatePrice()));

        System.out.println("Price summary: " + printPriceSummary());
    }

    BigDecimal generatePrice() {
        return generator.generatePrice();
    }
}

