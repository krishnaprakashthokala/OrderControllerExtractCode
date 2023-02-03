package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.web.frontend.controllers.ReviewController;

@RestController
@RequestMapping("/reviewController")
public class ReviewControllerController {
    @Autowired
    private ReviewController reviewController;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewControllerCreateOutDTO> create(@RequestBody ReviewControllerCreateInDTO in) {
        ReviewControllerCreateOutDTO ret = new ReviewControllerCreateOutDTO();
        ret.setRetVal(reviewController.create(in.getProductId(), in.getReview(), in.getBindingResult(), in.getModel(), in.getSessionStatus(), in.getCurrentUser()));
        return ResponseEntity.ok(ret);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewControllerCreateOutDTO> create(@RequestBody ReviewControllerCreateInDTO in) {
        ReviewControllerCreateOutDTO ret = new ReviewControllerCreateOutDTO();
        ret.setRetVal(reviewController.create(in.getProductId(), in.getModel()));
        return ResponseEntity.ok(ret);
    }

}