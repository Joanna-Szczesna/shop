package pl.joannaszczesna.shop;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Primary
class ShopPriceGenerator implements PriceGenerator {
private final double min = 50.0;
private final double max = 300.0;

    @Override
    public BigDecimal generatePrice() {
        double number = (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;

        return BigDecimal.valueOf(number).setScale(2, RoundingMode.FLOOR);
    }
}
