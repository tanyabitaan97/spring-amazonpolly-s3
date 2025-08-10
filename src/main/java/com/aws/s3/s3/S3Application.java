package com.aws.s3.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class },scanBasePackages = {"com.aws.polly.controller","com.aws.s3.s3.controller"})
public class S3Application {

	public static void main(String[] args) {
		SpringApplication.run(S3Application.class, args);
	}

}
