package pl.joannaszczesna.shop;

import java.math.BigDecimal;
import java.util.Map;

public interface Shop {
    void addToBasket(String name, BigDecimal price);
    Map<String, BigDecimal> getBasket();
    String printPriceSummary();
    void app();
}
