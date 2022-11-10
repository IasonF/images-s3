package com.iason.images.controllers;

import com.iason.images.services.ImageService;
import com.iason.images.services.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.io.IOException;

@RestController
@Validated
@RequestMapping(path = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
public class ImageServiceController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/show/{typeName}/{seoName}")
    public @ResponseBody byte[] getImage(
            @PathVariable("typeName") String typeName,
            @PathVariable("seoName") String seoName,
            @RequestParam String reference) throws IOException, InvalidRequestException {
        return imageService.getImage(typeName, reference);
    }

    @DeleteMapping("flush/{typeName}/")
    @ResponseStatus(HttpStatus.OK)
    void flushImages(
            @PathVariable("typeName") String typeName,
            @RequestParam String reference) {
        imageService.flush(typeName, reference);
    }

    @ExceptionHandler({InvalidRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleBasicValidityCheck(InvalidRequestException e) {
        return new ResponseEntity<>("Request is invalid: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class, IOException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

