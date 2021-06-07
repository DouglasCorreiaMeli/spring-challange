package com.meli.socialMeli.domain.dto.response;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicationResponse {

    private Integer idPost;
    private Integer userId;
    private String date;
    private ProductResponse detail;
    private int category;
    private Double price;
    private Boolean hasPromo;
    private Double discount;


}
