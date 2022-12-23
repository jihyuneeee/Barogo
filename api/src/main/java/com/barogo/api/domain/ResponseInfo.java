package com.barogo.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseInfo {

    private String response;

    private String message;
}
