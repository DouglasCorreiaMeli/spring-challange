package com.meli.socialMeli.domain.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListPromoProductsResponse {

    Integer userId;
    String userName;
    List <PublicationResponse> posts;
}
