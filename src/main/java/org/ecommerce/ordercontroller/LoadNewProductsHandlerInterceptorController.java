package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.web.interceptors.LoadNewProductsHandlerInterceptor;

@RestController
@RequestMapping("/loadNewProductsHandlerInterceptor")
public class LoadNewProductsHandlerInterceptorController {
    @Autowired
    private LoadNewProductsHandlerInterceptor loadNewProductsHandlerInterceptor;

    @PostMapping(value = "/postHandle", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoadNewProductsHandlerInterceptorPostHandleOutDTO> postHandle(@RequestBody LoadNewProductsHandlerInterceptorPostHandleInDTO in) {
        LoadNewProductsHandlerInterceptorPostHandleOutDTO ret = new LoadNewProductsHandlerInterceptorPostHandleOutDTO();
        loadNewProductsHandlerInterceptor.postHandle(in.getRequest(), in.getResponse(), in.getHandler(), in.getModelAndView());
        return ResponseEntity.ok(ret);
    }

}