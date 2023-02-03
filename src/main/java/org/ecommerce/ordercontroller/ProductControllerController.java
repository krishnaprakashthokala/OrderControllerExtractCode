package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.web.frontend.controllers.ProductController;

@RestController
@RequestMapping("/productController")
public class ProductControllerController {
    @Autowired
    private ProductController productController;

    @PostMapping(value = "/detail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductControllerDetailOutDTO> detail(@RequestBody ProductControllerDetailInDTO in) {
        ProductControllerDetailOutDTO ret = new ProductControllerDetailOutDTO();
        ret.setRetVal(productController.detail(in.getLine(), in.getModel()));
        return ResponseEntity.ok(ret);
    }

    @PostMapping(value = "/result", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductControllerResultOutDTO> result(@RequestBody ProductControllerResultInDTO in) {
        ProductControllerResultOutDTO ret = new ProductControllerResultOutDTO();
        ret.setRetVal(productController.result(in.getSearchProduct(), in.getCategory(), in.getPage(), in.getModel()));
        return ResponseEntity.ok(ret);
    }

}