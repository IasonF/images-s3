package com.iason.images.services;

import com.iason.images.models.ImageType;
import com.iason.images.models.PredefinedImageType;
import com.iason.images.models.ScaleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ImageServiceConfiguration {

    @Bean
    public List<PredefinedImageType> availableTypes() {
        //todo: make a property file with types
        PredefinedImageType thumbnail = new PredefinedImageType();
        thumbnail.setHeight(10);
        thumbnail.setWidth(10);
        thumbnail.setQuality(10);
        thumbnail.setScale(ScaleType.SKEW);
        thumbnail.setFillColor(10);
        thumbnail.setType(ImageType.PNG);

        PredefinedImageType detailLarge = new PredefinedImageType();
        detailLarge.setHeight(1000);
        detailLarge.setWidth(1000);
        detailLarge.setQuality(1000);
        detailLarge.setScale(ScaleType.FILL);
        detailLarge.setFillColor(10);
        detailLarge.setType(ImageType.JPG);

        return List.of(thumbnail, detailLarge);
    }
}
