package com.meli.socialMeli.service;

import com.meli.socialMeli.domain.entity.Buyer;
import com.meli.socialMeli.domain.entity.Seller;
import com.meli.socialMeli.domain.error.ResourceNotFoundException;
import com.meli.socialMeli.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List <Seller> listAllSellers(){
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Integer id){
        return sellerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not found for id: " + id));
    }

    public Seller saveSeller(Seller entity){
        if (entity.getCreatedAt() == null){
            entity.setCreatedAt(LocalDate.now());
        }
        entity.setUpdatedAt(LocalDate.now());
        return sellerRepository.save(entity);
    }

    public void addFollower(Buyer follower , Seller followed){
        followed.setUpdatedAt(LocalDate.now());
        followed.getBuyers().add(follower);
        sellerRepository.save(followed);
    }

    public Integer countSellerFollowers(Integer userId) {
        return sellerRepository.countByBuyers(userId).intValue();
    }
}
