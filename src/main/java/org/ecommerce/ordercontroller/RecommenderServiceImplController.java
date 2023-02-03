package org.ecommerce.ordercontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.ecommerce.ordercontroller.dto.*;
import org.ecommerce.web.services.impl.RecommenderServiceImpl;

@RestController
@RequestMapping("/recommenderServiceImpl")
public class RecommenderServiceImplController {
    @Autowired
    private RecommenderServiceImpl recommenderServiceImpl;

    @PostMapping(value = "/addProductViewedToUserHistory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecommenderServiceImplAddProductViewedToUserHistoryOutDTO> addProductViewedToUserHistory(@RequestBody RecommenderServiceImplAddProductViewedToUserHistoryInDTO in) {
        RecommenderServiceImplAddProductViewedToUserHistoryOutDTO ret = new RecommenderServiceImplAddProductViewedToUserHistoryOutDTO();
        recommenderServiceImpl.addProductViewedToUserHistory(in.getUserId(), in.getProductId());
        return ResponseEntity.ok(ret);
    }

    @PostMapping(value = "/addProductViewedToAnonymousUserHistory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecommenderServiceImplAddProductViewedToAnonymousUserHistoryOutDTO> addProductViewedToAnonymousUserHistory(@RequestBody RecommenderServiceImplAddProductViewedToAnonymousUserHistoryInDTO in) {
        RecommenderServiceImplAddProductViewedToAnonymousUserHistoryOutDTO ret = new RecommenderServiceImplAddProductViewedToAnonymousUserHistoryOutDTO();
        recommenderServiceImpl.addProductViewedToAnonymousUserHistory(in.getProductId());
        return ResponseEntity.ok(ret);
    }

}