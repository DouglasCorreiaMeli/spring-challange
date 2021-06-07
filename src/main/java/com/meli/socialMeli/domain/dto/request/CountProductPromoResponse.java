package com.meli.socialMeli.domain.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountProductPromoResponse {

    private Integer userId;
    private String userName;
    private Integer promoproductsCount;
}
