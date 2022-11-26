package com.astradev_back.astradev_back.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HHModel {
    private int founds;
    private int maxSal;
    private int minSal;
    private int avgSal;

    public HHModel(int founds, int maxSal, int minSal, int avgSal) {
        this.founds = founds;
        this.maxSal = maxSal;
        this.minSal = minSal;
        this.avgSal = avgSal;
    }
}