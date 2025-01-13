package pl.joannaszczesna.shop.service;

import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;

public interface Warehouse {
    void store(String productName, int productId, BigDecimal price);
    Product findProduct(int productId);
}
