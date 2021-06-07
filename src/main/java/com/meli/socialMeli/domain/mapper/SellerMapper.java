package com.meli.socialMeli.domain.mapper;

import com.meli.socialMeli.domain.dto.request.UserRequest;
import com.meli.socialMeli.domain.dto.response.FollowedSellers;
import com.meli.socialMeli.domain.dto.response.SellerFollowersListResponse;
import com.meli.socialMeli.domain.dto.response.SellerFollowersResponse;
import com.meli.socialMeli.domain.entity.Buyer;
import com.meli.socialMeli.domain.entity.Seller;
import com.meli.socialMeli.service.SellerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SellerMapper {

    private SellerService service;

    public Seller requestToSeller(UserRequest request) {
        Seller seller = new Seller();
        seller.setUserName(request.getUserName());
        seller.setBuyers(new ArrayList<>());
        seller.setPublications(new ArrayList <>());
        return seller;
    }

    public SellerFollowersResponse sellerToSellerFollowersResponse(Seller seller, Integer numbersOfFollowers){
        return SellerFollowersResponse
                .builder()
                .userId(seller.getId())
                .userName(seller.getUserName())
                .followers_count(numbersOfFollowers)
                .build();
    }

    public SellerFollowersListResponse sellerToSellerFollowersListResponse(Seller seller) {
        return SellerFollowersListResponse
                .builder()
                .userId(seller.getId())
                .userName(seller.getUserName())
                .followers(this.listFollowers(seller))
                .build();
    }

    public SellerFollowersListResponse sellerToSellerFollowersListResponseOrder(Seller seller, String order) {
        if (order.equals("name_asc")){
            return SellerFollowersListResponse
                    .builder()
                    .userId(seller.getId())
                    .userName(seller.getUserName())
                    .followers(this.listFollowersOrderAsc(seller))
                    .build();
        }else if (order.equals("name_desc")){
            return SellerFollowersListResponse
                    .builder()
                    .userId(seller.getId())
                    .userName(seller.getUserName())
                    .followers(this.listFollowersOrderDesc(seller))
                    .build();
        }
        return this.sellerToSellerFollowersListResponse(seller);
    }

    private List<FollowedSellers> listFollowers(Seller sellers) {
        List<FollowedSellers> followedSellers = new ArrayList <>();
        sellers.getBuyers().forEach(buyer -> followedSellers.add(new FollowedSellers(buyer.getId(), buyer.getUserName())));
        return followedSellers;
    }

    private List<FollowedSellers> listFollowersOrderAsc(Seller sellers) {
        var listToSort = this.listFollowers(sellers);
        listToSort.sort(Comparator.comparing(FollowedSellers::getUserName));
        return listToSort;
    }

    private List<FollowedSellers> listFollowersOrderDesc(Seller sellers) {
        var listToSort = this.listFollowers(sellers);
        listToSort.sort(Comparator.comparing(FollowedSellers::getUserName).reversed());
        return listToSort;
    }
}
