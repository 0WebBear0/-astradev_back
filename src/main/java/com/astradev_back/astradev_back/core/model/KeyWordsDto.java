package com.astradev_back.astradev_back.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeyWordsDto {

    @ApiModelProperty("id keyWord")
    private Long id;

    @ApiModelProperty("Название товара")
    private String word;
}

