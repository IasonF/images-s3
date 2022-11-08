package com.iason.images.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ImageService {

    private final AmazonS3 client = AmazonS3ClientBuilder
            .standard()
            .withPathStyleAccessEnabled(true)
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "us-east-1"))
            .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
            .build();

    public byte[] getImage(String typeName, String reference) throws IOException {
        String path = findBucketPath(typeName, reference);
        S3Object object = client.getObject(path, reference);
        return IOUtils.toByteArray(object.getObjectContent());
    }

    public void flush(String typeName, String reference) {

    }

    public static String findBucketPath(String typeName, String reference) {
        StringBuilder bucketBuilder = new StringBuilder("~/");
        bucketBuilder.append(typeName);
        bucketBuilder.append("/");
        //remove ".jpg" from the end and replace forward slashes
        reference = reference.substring(0, reference.length() - 4);
        reference = reference.replace("/", "_");
        if (reference.length() > 4) {
            bucketBuilder.append(reference.substring(0, 4)).append("/");
        }
        if (reference.length() > 8) {
            bucketBuilder.append(reference.substring(4, 8)).append("/");
        }
        return bucketBuilder.toString();
    }


}
