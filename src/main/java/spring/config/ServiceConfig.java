package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"models.goods.service", "models.orders.service"})
public class ServiceConfig {

}
