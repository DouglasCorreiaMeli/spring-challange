package com.meli.socialMeli.domain.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerFollowersResponse {

    Integer userId;
    String userName;
    Integer followers_count;
}
