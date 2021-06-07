package com.meli.socialMeli.domain.dto.request;

import com.meli.socialMeli.domain.entity.Product;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPostRequest {

    Integer userId;
    String date;
    ProductRequest detail;
    Integer category;
    Double price;

}
