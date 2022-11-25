package com.astradev_back.astradev_back.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDto {

    @ApiModelProperty("id пользователя")
    private Long id;

    @ApiModelProperty("Логин пользователя")
    private String name;

    @ApiModelProperty("Пароль пользователя")
    private String password;

    @ApiModelProperty("Почта")
    private String mail;
}
