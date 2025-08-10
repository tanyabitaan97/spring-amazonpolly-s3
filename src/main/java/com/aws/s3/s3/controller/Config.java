/*
 * package com.aws.s3.s3.controller;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import com.amazonaws.auth.AWSCredentials; import
 * com.amazonaws.auth.AWSStaticCredentialsProvider; import
 * com.amazonaws.auth.BasicAWSCredentials; import com.amazonaws.regions.Region;
 * import com.amazonaws.regions.Regions; import
 * com.amazonaws.services.s3.AmazonS3; import
 * com.amazonaws.services.s3.AmazonS3ClientBuilder;
 * 
 * import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
 * import software.amazon.awssdk.services.s3.S3AsyncClient; import
 * software.amazon.awssdk.transfer.s3.S3TransferManager;
 * 
 * @Configuration public class Config {
 * 
 * @Value("${cloud.aws.credentials.access-key}") String awsAccessKey;
 * 
 * @Value("${cloud.aws.credentials.secret-key}") String awsSecretKey;
 * 
 * 
 * @Bean public AmazonS3 getAmazonS3Client() { AWSCredentials credentails = new
 * BasicAWSCredentials(awsAccessKey,awsSecretKey); return
 * AmazonS3ClientBuilder.standard() .withCredentials(new
 * AWSStaticCredentialsProvider(credentails)) .withRegion(Regions.EU_NORTH_1)
 * .build(); }
 * 
 * 
 * 
 * 
 * }
 */