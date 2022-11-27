package com.astradev_back.astradev_back.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Users_KeyWordsDto {

    @ApiModelProperty("id users_keywords")
    private Long id;

    @ApiModelProperty("user")
    private UsersDto user;

    @ApiModelProperty("keyword")
    private KeyWordsDto keyWord;
}
