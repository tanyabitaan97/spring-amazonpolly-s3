/*
 * package com.aws.s3.s3.controller;
 * 
 * import java.io.IOException; import org.apache.http.HttpStatus; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestPart; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.multipart.MultipartFile; import
 * com.amazonaws.services.s3.model.Bucket;
 * 
 * @RestController
 * 
 * @RequestMapping("/s3") public class S3Controller {
 * 
 * @Autowired CreateBucket createBucket;
 * 
 * @Autowired PutObject putObject;
 * 
 * @PostMapping("/create/bucket") public Bucket doS3Operations(@RequestBody
 * String bucketName) {
 * 
 * System.out.println("bucjet name is "+bucketName);
 * 
 * Bucket b = createBucket.createBucket(bucketName);
 * 
 * return b;
 * 
 * }
 * 
 * @GetMapping("/put/bucket/{bucketName}") public String
 * putObjectInS3(@RequestPart(value = "file") MultipartFile file, @PathVariable
 * String bucketName) throws IOException { return
 * putObject.uploadObject(bucketName,file); }
 * 
 * @GetMapping("/share/url/{bucketname}/{objectname}") public String
 * generateUrl(@PathVariable String bucketname, @PathVariable String objectname)
 * {
 * 
 * return putObject.generateUrl(bucketname,objectname); }
 * 
 * @PostMapping("/upload/largerfile/{bucketName}") public ResponseEntity<String>
 * uploadLargerFile(@PathVariable String bucketName,@RequestPart(value = "file")
 * MultipartFile file) throws IOException {
 * 
 * System.out.println("file is "+file); String str =
 * putObject.uploadLargerSizeFile(bucketName,file);
 * 
 * return new ResponseEntity<>(str, org.springframework.http.HttpStatus.OK);
 * 
 * }
 * 
 * @GetMapping("/upload/video/{bucketName}") public String
 * videoUploadInS3(@RequestPart(value = "file") MultipartFile
 * file, @PathVariable String bucketName) throws IOException { return
 * putObject.uploadVideo(bucketName,file); }
 * 
 * }
 */