package com.aws.polly.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import javazoom.jl.decoder.JavaLayerException;
import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PollyDemoController {
	
	@Autowired
	AmazonS3 s3;
	
	@PostMapping("/generate/audio")
	public ResponseEntity<Object> generateAudio(@RequestBody HashMap<String,Object> codeExplanation) throws IOException, JavaLayerException {
		
		String explanationCode = codeExplanation.get("explanation").toString();
		
		//create the test class
				PollyDemo helloWorld = new PollyDemo(Region.getRegion(Regions.US_EAST_1));
				helloWorld.setSAMPLE(explanationCode);
				
				//get the audio stream
				InputStream speechStream = helloWorld.synthesize(helloWorld.getSAMPLE(), OutputFormat.Mp3);
				
				// Save to file
		        File audioFile = new File("polly-output.mp3");
		        try (FileOutputStream out = new FileOutputStream(audioFile)) {
		            byte[] buffer = new byte[2 * 1024];
		            int read;
		            while ((read = speechStream.read(buffer)) > 0) {
		                out.write(buffer, 0, read);
		            }
		        }
		        
		        PutObjectRequest putRequest = new PutObjectRequest(
		        	    "s3-assignment1-intellipaat", 
		        	    "polly-output-en.mp3", 
		        	    audioFile
		        	).withCannedAcl(CannedAccessControlList.PublicReadWrite);
		        
		        s3.putObject(putRequest);
		        
		        System.out.println("Uploaded to S3: https://" + "s3-assignment1-intellipaat" + ".s3.amazonaws.com/" + "polly-output-en.mp3");
		        

				/*
				 * //create an MP3 player AdvancedPlayer player = new
				 * AdvancedPlayer(speechStream,
				 * javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
				 * 
				 * player.setPlayBackListener(new PlaybackListener() {
				 * 
				 * @Override public void playbackStarted(PlaybackEvent evt) {
				 * System.out.println("Playback started");
				 * System.out.println(helloWorld.getSAMPLE()); }
				 * 
				 * @Override public void playbackFinished(PlaybackEvent evt) {
				 * System.out.println("Playback finished"); } });
				 * 
				 * 
				 * // play it! player.play();
				 */
		        
		        byte[] audioBytes = speechStream.readAllBytes();

		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        headers.set("Content-Disposition", "inline; filename=\"polly-output-en.mp3\"");

		        return new ResponseEntity<>(audioBytes, headers, HttpStatus.OK);
		
	}
	
	
	@Configuration
	public class WebConfig implements WebMvcConfigurer {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("http://localhost:3000")
	                .allowedMethods("GET", "POST", "PUT", "DELETE")
	                .allowedHeaders("*");
	    }
	}

}
