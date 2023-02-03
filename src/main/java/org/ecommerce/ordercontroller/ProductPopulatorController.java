package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.persistence.populator.ProductPopulator;

@RestController
@RequestMapping("/productPopulator")
public class ProductPopulatorController {
    @Autowired
    private ProductPopulator productPopulator;

    @PostMapping(value = "/contextRefreshedEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPopulatorContextRefreshedEventOutDTO> contextRefreshedEvent(@RequestBody ProductPopulatorContextRefreshedEventInDTO in) {
        ProductPopulatorContextRefreshedEventOutDTO ret = new ProductPopulatorContextRefreshedEventOutDTO();
        productPopulator.contextRefreshedEvent();
        return ResponseEntity.ok(ret);
    }

}