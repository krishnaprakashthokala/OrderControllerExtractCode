package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.web.services.impl.ProductServiceImpl;

@RestController
@RequestMapping("/productServiceImpl")
public class ProductServiceImplController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @PostMapping(value = "/getNewFeedbacks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductServiceImplGetNewFeedbacksOutDTO> getNewFeedbacks(@RequestBody ProductServiceImplGetNewFeedbacksInDTO in) {
        ProductServiceImplGetNewFeedbacksOutDTO ret = new ProductServiceImplGetNewFeedbacksOutDTO();
        ret.setRetVal(productServiceImpl.getNewFeedbacks());
        return ResponseEntity.ok(ret);
    }

}