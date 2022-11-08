package com.iason.images;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Mock {
    public static void main(String[] args) {
        io.findify.s3mock.S3Mock api = io.findify.s3mock.S3Mock.create(8001, "/tmp/s3");
        api.start();

        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8001", "us-east-1"))
                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .build();
        client.createBucket("testbucket");
        System.out.println("Bucket created");

        client.putObject("testbucket", "file/name", "contents");
    }
}