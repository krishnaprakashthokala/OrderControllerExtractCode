package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.persistence.populator.OrderPopulator;

@RestController
@RequestMapping("/orderPopulator")
public class OrderPopulatorController {
    @Autowired
    private OrderPopulator orderPopulator;

    @PostMapping(value = "/contextRefreshedEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderPopulatorContextRefreshedEventOutDTO> contextRefreshedEvent(@RequestBody OrderPopulatorContextRefreshedEventInDTO in) {
        OrderPopulatorContextRefreshedEventOutDTO ret = new OrderPopulatorContextRefreshedEventOutDTO();
        orderPopulator.contextRefreshedEvent();
        return ResponseEntity.ok(ret);
    }

}