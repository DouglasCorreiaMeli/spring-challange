package com.meli.socialMeli.controller;

import com.meli.socialMeli.domain.dto.request.UserRequest;
import com.meli.socialMeli.domain.entity.Seller;
import com.meli.socialMeli.domain.mapper.SellerMapper;
import com.meli.socialMeli.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellers")
@Api(tags = { " Sellers " }, value = "end-point to create sellers")
public class SellerController {

    private final SellerService service;
    private final SellerMapper mapper;

    public SellerController(SellerService service, SellerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @ApiOperation(value = "create seller")
    @PostMapping
    @Transactional
    public ResponseEntity<Void> createSeller(@RequestBody UserRequest request){
        Seller seller = this.service.saveSeller(mapper.requestToSeller(request));
        return ResponseEntity.ok().build();
    }

}
