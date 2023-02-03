package org.ecommerce.ordercontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({})
public class OrderControllerApp {
    public static void main(String[] args)
    {
      SpringApplication.run(OrderControllerApp.class, args);
    }
}