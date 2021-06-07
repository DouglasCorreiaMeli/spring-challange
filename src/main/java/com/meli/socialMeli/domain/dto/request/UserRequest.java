package com.meli.socialMeli.domain.dto.request;

import com.meli.socialMeli.domain.entity.Seller;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String userName;
}
