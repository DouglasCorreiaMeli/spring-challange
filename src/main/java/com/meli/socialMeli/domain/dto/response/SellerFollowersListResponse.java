package com.meli.socialMeli.domain.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerFollowersListResponse {

    private Integer userId;

    private String userName;

    private List <FollowedSellers> followers;
}
