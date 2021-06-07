package com.meli.socialMeli.domain.mapper;

import com.meli.socialMeli.domain.dto.request.CountProductPromoResponse;
import com.meli.socialMeli.domain.dto.request.NewPostRequest;
import com.meli.socialMeli.domain.dto.request.NewPromoPostRequest;
import com.meli.socialMeli.domain.dto.request.ProductRequest;
import com.meli.socialMeli.domain.dto.response.ListPromoProductsResponse;
import com.meli.socialMeli.domain.dto.response.ProductResponse;
import com.meli.socialMeli.domain.dto.response.PublicationResponse;
import com.meli.socialMeli.domain.entity.Product;
import com.meli.socialMeli.domain.entity.Publication;
import com.meli.socialMeli.domain.entity.Seller;
import com.meli.socialMeli.service.SellerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    private final SellerService sellerService;

    public ProductMapper(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public Publication requestToPublication(NewPostRequest request) {
        Publication pub =  Publication
                .builder()
                .category(request.getCategory())
                .hasPromo(false)
                .discount(0.0)
                .price(request.getPrice())
                .product(this.buildProduct(request.getDetail()))
                .build();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate date = LocalDate.parse(request.getDate(), dtf);
        pub.setCreatedAt(date);
        return pub;
    }

    public Publication promoRequestToPublication(NewPromoPostRequest request) {
        Publication pub =  Publication
                .builder()
                .category(request.getCategory())
                .hasPromo(request.getHasPromo())
                .discount(request.getDiscount())
                .price(request.getPrice())
                .product(this.buildProduct(request.getDetail()))
                .build();
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate date = LocalDate.parse(request.getDate(), dtf);
        pub.setCreatedAt(date);
        return pub;
    }

    private Product buildProduct(ProductRequest detail) {
        return Product
                .builder()
                .productName(detail.getProductName())
                .brand(detail.getBrand())
                .color(detail.getColor())
                .notes(detail.getNotes())
                .type(detail.getType())
                .build();
    }

    public PublicationResponse publicationToResponse(Publication publication, Integer userId) {
        return PublicationResponse
                .builder()
                .category(publication.getCategory())
                .hasPromo(publication.getHasPromo())
                .discount(publication.getDiscount())
                .price(publication.getPrice())
                .date(publication.getCreatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .idPost(publication.getId())
                .userId(userId)
                .detail(this.buildDetail(publication.getProduct()))
                .build();


    }

    private ProductResponse buildDetail(Product product) {
        return ProductResponse
                .builder()
                .productName(product.getProductName())
                .brand(product.getBrand())
                .color(product.getColor())
                .notes(product.getNotes())
                .productId(product.getId())
                .type(product.getType())
                .build();
    }

    public CountProductPromoResponse createCountPromoResponse(Integer userId, Integer countPromoProductsBySeller) {
        Seller seller = sellerService.getSellerById(userId);
        return CountProductPromoResponse
                .builder()
                .promoproductsCount(countPromoProductsBySeller)
                .userId(seller.getId())
                .userName(seller.getUserName())
                .build();

    }

    public ListPromoProductsResponse createPromoResponse(Integer userId, List<Publication> publications) {
        Seller seller = this.sellerService.getSellerById(userId);
        return ListPromoProductsResponse
                .builder()
                .userId(seller.getId())
                .userName(seller.getUserName())
                .posts(getForEach(publications, userId))
                .build();


    }

    private List<PublicationResponse> getForEach(List <Publication> publications, Integer userId) {
        List<PublicationResponse> list = new ArrayList<>();
        for ( var publication: publications) list.add(this.publicationToResponse(publication, userId));
        return list;
    }
}
