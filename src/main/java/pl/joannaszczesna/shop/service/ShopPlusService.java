package pl.joannaszczesna.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.joannaszczesna.shop.discount.DiscountPolicy;
import pl.joannaszczesna.shop.tax.TaxPolicy;

@Component
@Profile("plus")
class ShopPlusService extends ShopStartService {

    @Autowired
    ShopPlusService(@Qualifier("taxPlus") TaxPolicy taxPolicy, @Qualifier("noDiscount") DiscountPolicy discountPolicy) {
        super(taxPolicy, discountPolicy);
    }
}
