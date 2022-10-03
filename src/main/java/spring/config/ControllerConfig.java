package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"controllers", "controllers.goods", "controllers.orders"})
public class ControllerConfig {

}
