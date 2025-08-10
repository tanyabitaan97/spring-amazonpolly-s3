/*
 * package com.aws.s3.s3.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.amazonaws.services.s3.AmazonS3; import
 * com.amazonaws.services.s3.model.AmazonS3Exception;
 * 
 * import software.amazon.awssdk.services.s3.model.Bucket;
 * 
 * @Service public class CreateBucket {
 * 
 * @Autowired AmazonS3 s3;
 * 
 * public Bucket createBucket(String bucketName) { //final AmazonS3 s3 =
 * AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
 * 
 * Bucket b = null;
 * 
 * if (s3.doesBucketExistV2(bucketName)) {
 * System.out.format("Bucket %s already exists.\n", bucketName); b =
 * getBucket(bucketName); } else { try { b = s3.createBucket(bucketName); }
 * catch (AmazonS3Exception e) { System.err.println(e.getErrorMessage()); } }
 * 
 * return b; }
 * 
 * public Bucket getBucket(String bucketName) { //final AmazonS3 s3 =
 * AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
 * Bucket named_bucket = null; List<Bucket> buckets = s3.listBuckets(); for
 * (Bucket b : buckets) { if (b.getName().equals(bucketName)) { named_bucket =
 * b; } }
 * 
 * return named_bucket; }
 * 
 * }
 */