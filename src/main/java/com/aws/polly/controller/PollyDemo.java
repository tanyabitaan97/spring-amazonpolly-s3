package com.aws.polly.controller;

import java.io.IOException;
import java.io.InputStream;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;

public class PollyDemo {

	private AmazonPolly polly;
	private final Voice voice;
	private String SAMPLE;
	

	public String getSAMPLE() {
		return SAMPLE;
	}

	public void setSAMPLE(String sAMPLE) {
		SAMPLE = sAMPLE;
	}

	public PollyDemo(Region region) {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA3FLD4S3I3PCRBI7L", "ef4PjvQCVuCCpnWAs4yWJhIfNgflspj8/WZQCvpd");
		// create an Amazon Polly client in a specific region
		polly = AmazonPollyClientBuilder.standard()
			    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
			    .withRegion("us-east-1")
			    .build();
		// Create describe voices request.
		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

		// Synchronously ask Amazon Polly to describe available TTS voices.
		DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
		voice = describeVoicesResult.getVoices().get(0);
	}

	public InputStream synthesize(String text, OutputFormat format) throws IOException {
		SynthesizeSpeechRequest synthReq = 
		new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
				.withOutputFormat(format).withEngine("neural");
		SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

		return synthRes.getAudioStream();
	}
} 