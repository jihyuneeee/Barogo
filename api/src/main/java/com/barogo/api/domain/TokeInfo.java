package com.barogo.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TokeInfo {

    private String grantType;
    private String accessToken;
    private String refeshToken;

}
