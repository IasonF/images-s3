package com.iason.images.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {

    @Test
    void findBucketPath() {
        String path = ImageService.findBucketPath("thumbnail", "abcdefghij.jpg");
        assertEquals("~/thumbnail/abcd/efgh/", path);
        path = ImageService.findBucketPath("thumbnail", "abcde.jpg");
        assertEquals("~/thumbnail/abcd/", path);
        path = ImageService.findBucketPath("thumbnail", "/somedir/anotherdir/abcdef.jpg");
        assertEquals("~/thumbnail/_som/edir/", path);
    }
}