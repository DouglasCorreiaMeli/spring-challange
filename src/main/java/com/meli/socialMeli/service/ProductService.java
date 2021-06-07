package com.meli.socialMeli.service;

import com.meli.socialMeli.domain.dto.response.PublicationResponse;
import com.meli.socialMeli.domain.dto.response.TimeLineResponse;
import com.meli.socialMeli.domain.entity.Buyer;
import com.meli.socialMeli.domain.entity.Publication;
import com.meli.socialMeli.domain.entity.Seller;
import com.meli.socialMeli.domain.mapper.ProductMapper;
import com.meli.socialMeli.repository.PublicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final PublicationRepository publicationRepository;
    private final BuyerService buyerService;
    private final ProductMapper productMapper;
    List<PublicationResponse> publicationsList;

    public ProductService(PublicationRepository publicationRepository, BuyerService buyerService, ProductMapper productMapper ) {
        this.publicationRepository = publicationRepository;
        this.buyerService = buyerService;
        this.productMapper = productMapper;
    }

    public Publication createPublication(Publication publication, Integer userId) {
        publication.setUpdatedAt(LocalDate.now());
        publication.getProduct().setCreatedAt(LocalDate.now());
        publication.getProduct().setUpdatedAt(LocalDate.now());
        this.publicationRepository.save(publication);
        this.publicationRepository.insertSellerPub(userId, publication.getId());
        return publication;
    }

    public TimeLineResponse getTimeLineProductsForBuyer(Integer userId, String order) {
        Buyer buyer = buyerService.getBuyerById(userId);
        return  TimeLineResponse
                .builder()
                .userId(buyer.getId())
                .posts(this.getSellersPublications(buyer.getSellers(), order))
                .build();

    }

    //TODO filtar a lista por data e ver o bug de dado duplicado na lista
    private List<PublicationResponse> getSellersPublications(List<Seller> sellers, String order) {
        this.publicationsList = new ArrayList <>();

        sellers.forEach(seller -> seller.getPublications()
                .forEach(publication -> checkDateRange(seller, publication)));
        if(order == null ) return publicationsList;
        if (order.equals("date_asc")){
             publicationsList.sort(Comparator.comparing(this::stringToDate));
        }else if (order.equals("date_desc")){
            publicationsList.sort(Comparator.comparing(this::stringToDate).reversed());
        }
        return publicationsList;
    }

    private LocalDate stringToDate(PublicationResponse publicationResponse) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(publicationResponse.getDate(), dtf);
    }

    private void checkDateRange(Seller seller, Publication publication) {
        if(publication.getCreatedAt().isBefore(LocalDate.now().minusWeeks(2))) return;
        this.publicationsList.add(productMapper.publicationToResponse(publication, seller.getId()));
    }


    public Integer countPromoProductsBySeller(Integer userId) {
        return this.publicationRepository.countPromoProducts(userId).intValue();

    }

    public List<Publication> listPromoProductsBySeller(Integer userId) {
        return this.publicationRepository.listPromoProducts(userId);
    }
}
