package com.barogo.api.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
// @Entity(name = "tbl_usr")
public class AuthInfo {

    @Id
    @NotBlank(message = "id is a required value.")
    private String id;

    @NotBlank(message = "name is a required value.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "password is a required value.")
    @Column(name = "password")
    private String password;
}
