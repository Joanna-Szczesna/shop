package pl.joannaszczesna.shop.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.joannaszczesna.shop.discount.DiscountPolicy;
import pl.joannaszczesna.shop.product.PriceGenerator;
import pl.joannaszczesna.shop.product.Product;
import pl.joannaszczesna.shop.tax.TaxPolicy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("start")
class ShopStartService implements Shop {
    private List<Product> cart;
    private final TaxPolicy taxPolicy;
    protected Warehouse warehouse;
    protected DiscountPolicy discountPolicy;

    @Autowired
    ShopStartService(TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.cart = new ArrayList<>();
        this.warehouse = new InMemoryWarehouse();
        this.discountPolicy = discountPolicy;
    }

    @Override
    public void addToCart(int productId) {
        Product product = warehouse.findProduct(productId);
        this.cart.add(product);
    }

    @Override
    public List<Product> getCart() {
        return new ArrayList<>(this.cart);
    }

    @Override
    public BigDecimal countSummaryPrice() {
        return this.cart.stream()
                .map(Product::price)
                .map(p -> p.add(taxPolicy.countTax(p)))
                .map(p -> p.subtract(discountPolicy.countDiscount(p)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public String getInfo() {
//        log.atInfo().log("ShopStart");
        return countSummaryPrice().toString();
    }

    @Override
    public void addNewProduct(String productName, int productId, BigDecimal price) {
        warehouse.store(productName, productId, price);
    }

    @Override
    public void clearCart() {
        this.cart = new ArrayList<>();
    }
}

