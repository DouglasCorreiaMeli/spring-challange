package com.meli.socialMeli.controller;

import com.meli.socialMeli.domain.dto.request.CountProductPromoResponse;
import com.meli.socialMeli.domain.dto.request.NewPostRequest;
import com.meli.socialMeli.domain.dto.request.NewPromoPostRequest;
import com.meli.socialMeli.domain.dto.response.ListPromoProductsResponse;
import com.meli.socialMeli.domain.dto.response.PublicationResponse;
import com.meli.socialMeli.domain.dto.response.TimeLineResponse;
import com.meli.socialMeli.domain.entity.Publication;
import com.meli.socialMeli.domain.mapper.ProductMapper;
import com.meli.socialMeli.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(tags = { " Products " }, value = "end-point to manage products")
public class ProductsController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductsController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @ApiOperation(value = "US005 - create a new post")
    @PostMapping("/newpost")
    @Transactional
    public ResponseEntity<PublicationResponse> createProduct(@RequestBody NewPostRequest request){
        Publication publication = this.productService.createPublication(productMapper.requestToPublication(request), request.getUserId());
        return ResponseEntity.ok().body(productMapper.publicationToResponse(publication, request.getUserId()));
    }

    @ApiOperation(value = "US010 - create a new promo post")
    @PostMapping("/newpromopost")
    @Transactional
    public ResponseEntity<PublicationResponse> createPromoProduct(@RequestBody NewPromoPostRequest request){
        Publication publication = this.productService.createPublication(productMapper.promoRequestToPublication(request),request.getUserId());
        return ResponseEntity.ok().body(productMapper.publicationToResponse(publication, request.getUserId()));
    }

    @ApiOperation(value = "US006 - Get a list of publications made by sellers that a user follows\n" +
            "in the last two weeks / US009 - order by date")
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<TimeLineResponse> getTimeLineByBuyer(@PathVariable Integer userId, @RequestParam(value = "date = order", required = false) String order){
        return ResponseEntity.ok().body(productService.getTimeLineProductsForBuyer(userId, order));
    }

    @ApiOperation(value = "US011 - count promotional products from a specific seller")
    @GetMapping("/followed/{userId}/countPromo")
    public ResponseEntity<CountProductPromoResponse> countPromoProductsBySeller(@PathVariable Integer userId){
        Integer count = productService.countPromoProductsBySeller(userId);
        return ResponseEntity.ok().body(productMapper.createCountPromoResponse(userId, count));
    }

    @ApiOperation(value = "US012 - list promotional products from a specific seller")
    @GetMapping("/{userId}/list")
    public ResponseEntity<ListPromoProductsResponse> getListPromoProducts(@PathVariable Integer userId){
        List<Publication> publications = this.productService.listPromoProductsBySeller(userId);
        return ResponseEntity.ok().body(productMapper.createPromoResponse(userId, publications));
    }




}
