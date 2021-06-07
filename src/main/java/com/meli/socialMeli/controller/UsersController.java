package com.meli.socialMeli.controller;

import com.meli.socialMeli.domain.dto.response.BuyerResponse;
import com.meli.socialMeli.domain.dto.response.SellerFollowersListResponse;
import com.meli.socialMeli.domain.dto.response.SellerFollowersResponse;
import com.meli.socialMeli.domain.entity.Seller;
import com.meli.socialMeli.domain.mapper.BuyerMapper;
import com.meli.socialMeli.domain.mapper.SellerMapper;
import com.meli.socialMeli.service.BuyerService;
import com.meli.socialMeli.service.SellerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(tags = { " Users " }, value = "end-point to manage buyers and Sellers")
public class UsersController {

    private final BuyerService buyerService;
    private final BuyerMapper buyerMapper;
    private final SellerService sellerService;
    private final SellerMapper sellerMapper;

    public UsersController(BuyerService buyerService, BuyerMapper buyerMapper, SellerService sellerService, SellerMapper sellerMapper) {
        this.buyerService = buyerService;
        this.buyerMapper = buyerMapper;
        this.sellerService = sellerService;
        this.sellerMapper = sellerMapper;
    }

    @ApiOperation(value = "US001 - make the buyer follow a seller")
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    @Transactional
    public ResponseEntity<Void> buyerFollowSeller(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        this.buyerService.followSeller(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "US007 - make the buyer unfollow a seller")
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    @Transactional
    public ResponseEntity<Void> buyerUnfollowSeller(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow){
        this.buyerService.unfollowSeller(userId, userIdToUnfollow);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "US002 - return the number of buyers that follow a seller")
    @GetMapping("/{userId}/followers")
    public ResponseEntity<SellerFollowersResponse> countSellerFollowers(@PathVariable Integer userId){
        Seller seller = this.sellerService.getSellerById(userId);
        Integer numbersOfFollowers = this.sellerService.countSellerFollowers(userId);
        return ResponseEntity.ok(sellerMapper.sellerToSellerFollowersResponse(seller,numbersOfFollowers));
    }

    @ApiOperation(value = "US003 - list of buyers who follow a seller / US008 - list of buyers who follow a seller using order by name")
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<SellerFollowersListResponse> SellerFollowersList(@PathVariable Integer userId, @RequestParam(value = "name = order", required = false) String order){
        Seller seller = this.sellerService.getSellerById(userId);
        if (order == null) return ResponseEntity.ok(sellerMapper.sellerToSellerFollowersListResponse(seller));
        return ResponseEntity.ok(sellerMapper.sellerToSellerFollowersListResponseOrder(seller, order));
    }

    @ApiOperation(value = "US004 - list sellers that a buyer follows")
    @GetMapping("/{id}/followed/list")
    public ResponseEntity<BuyerResponse> getBuyerById(@PathVariable Integer id){
        return ResponseEntity.ok(buyerMapper.buyerToResponse(buyerService.getBuyerById(id)));
    }
}
