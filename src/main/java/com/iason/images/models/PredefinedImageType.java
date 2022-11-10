package com.iason.images.models;

import lombok.Data;

@Data
public class PredefinedImageType {

    private String name;

    private int height;

    private int width;

    private int quality;

    private Enum<ScaleType> scale;

    private int fillColor;

    private Enum<ImageType> type;

    private String sourceName;

}
