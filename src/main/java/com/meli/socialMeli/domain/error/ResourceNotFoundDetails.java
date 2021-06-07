package com.meli.socialMeli.domain.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceNotFoundDetails {
    private String title;
    private int status;
    private String detail;
    private LocalDateTime timestamp;
    private String developerMessage;


}
