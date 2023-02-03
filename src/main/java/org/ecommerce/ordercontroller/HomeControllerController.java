package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.web.frontend.controllers.HomeController;

@RestController
@RequestMapping("/homeController")
public class HomeControllerController {
    @Autowired
    private HomeController homeController;

    @PostMapping(value = "/index", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeControllerIndexOutDTO> index(@RequestBody HomeControllerIndexInDTO in) {
        HomeControllerIndexOutDTO ret = new HomeControllerIndexOutDTO();
        ret.setRetVal(homeController.index(in.getModel()));
        return ResponseEntity.ok(ret);
    }

}