package com.meli.socialMeli.domain.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    String productName;
    String type;
    String brand;
    String color;
    String notes;
}
