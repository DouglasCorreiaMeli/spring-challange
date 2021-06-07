package com.meli.socialMeli.domain.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private Integer productId;

    private String productName;

    private String type;

    private String brand;

    private String color;

    private String notes;

}
