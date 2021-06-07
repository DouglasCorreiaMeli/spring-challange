package com.meli.socialMeli.service;

import com.meli.socialMeli.domain.entity.Buyer;
import com.meli.socialMeli.domain.entity.Seller;
import com.meli.socialMeli.domain.error.ResourceNotFoundException;
import com.meli.socialMeli.repository.BuyerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final SellerService sellerService;

    public BuyerService(BuyerRepository buyerRepository, SellerService sellerService) {
        this.buyerRepository = buyerRepository;
        this.sellerService = sellerService;
    }

    public List <Buyer> listAllBuyers(){
        return buyerRepository.findAll();
    }

    public Buyer getBuyerById(Integer id){
        return buyerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Buyer not found for id: " + id));
    }

    public Buyer saveBuyer(Buyer entity){
        if (entity.getCreatedAt() == null){
            entity.setCreatedAt(LocalDate.now());
        }
        entity.setUpdatedAt(LocalDate.now());
        return buyerRepository.save(entity);
    }

    public void followSeller(Integer buyerId, Integer sellerId){
        Buyer buyer = this.getBuyerById(buyerId);
        Seller seller = this.sellerService.getSellerById(sellerId);
        buyer.getSellers().add(seller);
        this.buyerRepository.save(buyer);
        this.sellerService.addFollower(buyer, seller);
    }

    public void unfollowSeller(Integer buyerId, Integer sellerId) {
        Buyer buyer = this.getBuyerById(buyerId);
        Seller seller = this.sellerService.getSellerById(sellerId);
        seller.getBuyers().remove(buyer);
        buyer.getSellers().remove(seller);
        this.sellerService.saveSeller(seller);
        this.saveBuyer(buyer);

    }
}
