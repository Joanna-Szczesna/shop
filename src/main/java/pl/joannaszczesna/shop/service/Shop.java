package pl.joannaszczesna.shop.service;

import pl.joannaszczesna.shop.product.Product;

import java.math.BigDecimal;
import java.util.List;

public interface Shop {
    void addToCart(int productId);
    List<Product> getCart();
    BigDecimal countSummaryPrice();
    String getInfo();
    void addNewProduct(String productName, int productId, BigDecimal price);
    void clearCart();
}
