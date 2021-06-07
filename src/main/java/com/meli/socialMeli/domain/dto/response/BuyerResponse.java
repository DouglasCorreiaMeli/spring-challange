package com.meli.socialMeli.domain.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyerResponse {

    private Integer userId;

    private String userName;

    private List<FollowedSellers> followed;
}
