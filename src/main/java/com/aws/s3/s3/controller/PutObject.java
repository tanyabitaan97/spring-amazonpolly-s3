//package com.aws.s3.s3.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
//import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
//import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
//import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
//import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PartETag;
//import com.amazonaws.services.s3.model.PutObjectResult;
//import com.amazonaws.services.s3.model.UploadPartRequest;
//import com.amazonaws.services.s3.model.UploadPartResult;
//import com.amazonaws.util.IOUtils;
//
//@Service
//public class PutObject {
//	
//	@Autowired
//	AmazonS3 s3;
//	
//	public String uploadObject(String bucketName, MultipartFile file) 
//		   throws IOException {
//		
//		Path absolutePath = Paths.get("C:\\Users\\INDIA\\Downloads\\s3\\s3").resolve(file.getOriginalFilename()).normalize().toAbsolutePath();
//		
//		Files.copy(file.getInputStream(), absolutePath);
//	
//	    FileSystemResource fsr = new FileSystemResource(file.getOriginalFilename());
//	    File file1 = fsr.getFile();
//	
//	    PutObjectResult putObject = s3.putObject(bucketName, file1.getName().toString(), file1);
//	
//	    return putObject.getVersionId();
//	
//	}
//	
//	public void deleteObject(String bucketName,String fileName) {
//		
//		s3.deleteObject(bucketName, fileName);
//		
//	}
//	
//	//To create URL
//	public String generateUrl(String bucketName, String objectKey) {
//		java.util.Date expiration = new java.util.Date();
//		long expTimeMillis = expiration.getTime();
//		expTimeMillis += 1000 * 60 * 60;
//		expiration.setTime(expTimeMillis);
//		
//		GeneratePresignedUrlRequest generatePresignedUrlRequest =
//				new GeneratePresignedUrlRequest(bucketName, objectKey)
//				.withExpiration(expiration);
//				URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
//				
//				System.out.println("Pre-Signed URL: " + url.toString());
//				
//		return url.toString();
//	}
//	
//	
//	//To upload file greater than specific length
//	public String uploadLargerSizeFile(String bucketName,MultipartFile multiPartFile) 
//		   throws IOException {
//		
//        Path absolutePath = Paths.get("C:\\Users\\INDIA\\Downloads\\s3\\s3").resolve(multiPartFile.getOriginalFilename()).normalize().toAbsolutePath();
//		Files.copy(multiPartFile.getInputStream(), absolutePath);
//		FileSystemResource resource = new FileSystemResource(multiPartFile.getOriginalFilename());
//		
//		File file = resource.getFile();
//		long contentLength = file.length();
//		long partSize = 5 * 1024 * 1024; //Set part size to 5 MB.
//		
//		if(file.length()<5*1024*1024) {
//		    PutObjectResult putObject = s3.putObject(bucketName, file.getName().toString(), file);
//		    file.delete();
//		    return putObject.getETag();
//		    } else {
//	       List<PartETag> partETags = new ArrayList<PartETag>();
//	       
//	       InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, file.getName().toLowerCase().toString());
//           InitiateMultipartUploadResult initResponse = s3.initiateMultipartUpload(initRequest);
//           
//           long filePosition = 0;
//           
//           for (int i = 1; filePosition < contentLength; i++) {
//               // Because the last part could be less than 5 MB, adjust the part size as
//               // needed.
//               partSize = Math.min(partSize, (contentLength - filePosition));
//
//               // Create the request to upload a part.
//               UploadPartRequest uploadRequest = new UploadPartRequest()
//                       .withBucketName(bucketName)
//                       .withKey(file.getName().toLowerCase().toString())
//                       .withUploadId(initResponse.getUploadId())
//                       .withPartNumber(i)
//                       .withFileOffset(filePosition)
//                       .withFile(file)
//                       .withPartSize(partSize);
//
//               // Upload the part and add the response's ETag to our list.
//               UploadPartResult uploadResult = s3.uploadPart(uploadRequest);
//               partETags.add(uploadResult.getPartETag());
//
//               filePosition += partSize;
//               
//               System.out.println("file poistion is "+filePosition);
//           }
//           
//           CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, file.getName().toString(),
//                   initResponse.getUploadId(), partETags);
//           
//           CompleteMultipartUploadResult completeMultipartUpload = s3.completeMultipartUpload(compRequest);
//           
//           System.out.println(completeMultipartUpload.getLocation());
//           
//           //To delete from class path
//           file.delete();
//		
//           return completeMultipartUpload.getLocation();
//		}
//  
//	}
//	
//	//To upload a video
//	public String uploadVideo(String bucketName,  MultipartFile file) 
//		   throws IOException {
//		
//		InputStream inputStream = file.getInputStream();
//		byte[] contentLengthInBytes = IOUtils.toByteArray(inputStream);
//		
//		Long contentLength = Long.valueOf(contentLengthInBytes.length);
//		
//		ObjectMetadata metadata = new ObjectMetadata();
//		metadata.setContentLength(contentLength);
//		
//		InputStream inputStreamToUpload = file.getInputStream();
//		
//		PutObjectResult putObject = s3.putObject(bucketName,file.getOriginalFilename(),inputStreamToUpload,metadata);
//		
//		System.out.println("version Id is "+putObject.getETag());
//		
//		return putObject.getETag();
//	}
//	
//}
