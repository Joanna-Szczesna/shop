package pl.joannaszczesna.shop.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"pl.joannaszczesna.shop.product",
        "pl.joannaszczesna.shop.service",
        "pl.joannaszczesna.shop.tax",
        "pl.joannaszczesna.shop.discount"})
class TestConfig {

}
