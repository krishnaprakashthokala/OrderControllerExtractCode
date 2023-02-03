package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.web.services.impl.OrderServiceImpl;

@RestController
@RequestMapping("/orderServiceImpl")
public class OrderServiceImplController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @PostMapping(value = "/getNewOrders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderServiceImplGetNewOrdersOutDTO> getNewOrders(@RequestBody OrderServiceImplGetNewOrdersInDTO in) {
        OrderServiceImplGetNewOrdersOutDTO ret = new OrderServiceImplGetNewOrdersOutDTO();
        ret.setRetVal(orderServiceImpl.getNewOrders());
        return ResponseEntity.ok(ret);
    }

    @PostMapping(value = "/getTotalProfit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderServiceImplGetTotalProfitOutDTO> getTotalProfit(@RequestBody OrderServiceImplGetTotalProfitInDTO in) {
        OrderServiceImplGetTotalProfitOutDTO ret = new OrderServiceImplGetTotalProfitOutDTO();
        ret.setRetVal(orderServiceImpl.getTotalProfit());
        return ResponseEntity.ok(ret);
    }

}