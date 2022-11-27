package com.astradev_back.astradev_back.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HabrCardDto {

    @ApiModelProperty("Название статьи")
    private String title;
    @ApiModelProperty("Часть статьи")
    private String body;
    @ApiModelProperty("Ссылка на статью")
    private String url;
}
