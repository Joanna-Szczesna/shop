package pl.joannaszczesna.shop.service;

import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.util.List;

public interface Shop {
    void addToBasket(Product product);
    List<Product> getBasket();
    BigDecimal countSummaryPrice();
    String getInfo();
}
