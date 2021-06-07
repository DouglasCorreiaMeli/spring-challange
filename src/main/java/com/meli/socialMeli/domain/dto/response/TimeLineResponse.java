package com.meli.socialMeli.domain.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeLineResponse {

    private Integer userId;
    private List<PublicationResponse> posts;
}
