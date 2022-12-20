package com.barogo.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "id는 필수 값입니다.")
    private String id;

    @NotBlank(message = "name은 필수 값입니다.")
    private String name;

    private String password;

}
