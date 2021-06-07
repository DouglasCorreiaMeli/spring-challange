package com.meli.socialMeli.controller;

import com.meli.socialMeli.domain.dto.request.UserRequest;
import com.meli.socialMeli.domain.dto.response.BuyerResponse;
import com.meli.socialMeli.domain.entity.Buyer;
import com.meli.socialMeli.domain.mapper.BuyerMapper;
import com.meli.socialMeli.service.BuyerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyers")
@Api(tags = { " Buyers " }, value = "end-point to create buyers")
public class BuyerController {

    private final BuyerService service;
    private final BuyerMapper mapper;

    public BuyerController(BuyerService service, BuyerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }



    @ApiOperation(value = "list buyers", hidden = true)
    @GetMapping("/")
    public ResponseEntity<List<BuyerResponse>> getAllBuyers() {
        return ResponseEntity.ok(service.listAllBuyers().stream().map(mapper::buyerToResponse).collect(Collectors.toList()));
    }

    @ApiOperation(value = "create buyer")
    @PostMapping
    @Transactional
    public ResponseEntity<BuyerResponse> createBuyer(@RequestBody UserRequest request, UriComponentsBuilder uriComponentsBuilder){
        Buyer buyer = this.service.saveBuyer(mapper.requestToBuyer(request));
        URI uri = uriComponentsBuilder.path("/buyers/{id}").buildAndExpand(buyer.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.buyerToResponse(buyer));
    }
}
