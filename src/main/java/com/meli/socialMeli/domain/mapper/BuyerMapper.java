package com.meli.socialMeli.domain.mapper;

import com.meli.socialMeli.domain.dto.request.UserRequest;
import com.meli.socialMeli.domain.dto.response.BuyerResponse;
import com.meli.socialMeli.domain.dto.response.FollowedSellers;
import com.meli.socialMeli.domain.entity.Buyer;
import com.meli.socialMeli.domain.entity.Seller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerMapper {


    public Buyer requestToBuyer(UserRequest request) {
        Buyer b = new Buyer();
        b.setUserName(request.getUserName());
        b.setSellers(new ArrayList<Seller>());
        return b;
    }

    public BuyerResponse buyerToResponse(Buyer buyer) {
        return BuyerResponse
                .builder()
                .userId(buyer.getId())
                .userName(buyer.getUserName())
                .followed(this.convertSellers(buyer.getSellers()))
                .build();
    }

    private List<FollowedSellers> convertSellers(List<Seller> sellers) {
        List<FollowedSellers> followedSellers = new ArrayList <>();
        sellers.forEach(seller -> followedSellers.add(new FollowedSellers(seller.getId(), seller.getUserName())));
        return followedSellers;
    }
}
