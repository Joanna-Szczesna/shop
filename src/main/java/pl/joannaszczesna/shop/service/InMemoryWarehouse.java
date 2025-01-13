package pl.joannaszczesna.shop.service;

import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryWarehouse implements Warehouse {
    private final Map<Integer, Product> products = new HashMap<>();

    @Override
    public void store(String productName, int productId, BigDecimal price) {
        products.put(productId, new Product(productName, price));
    }

    @Override
    public Product findProduct(int productId) {
        return Optional.ofNullable(products.get(productId)).orElseThrow();
    }
}
