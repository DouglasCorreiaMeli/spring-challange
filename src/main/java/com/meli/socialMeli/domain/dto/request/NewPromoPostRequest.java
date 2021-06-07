package com.meli.socialMeli.domain.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPromoPostRequest {

    Integer userId;
    String date;
    ProductRequest detail;
    Integer category;
    Double price;
    Boolean hasPromo;
    Double discount;


}
