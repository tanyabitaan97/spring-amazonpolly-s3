package com.aws.polly.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.polly.model.OutputFormat;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;

@RestController
public class PollyDemoController {
	
	@PostMapping("/generate/audio")
	public void generateAudio(@RequestBody HashMap<String,Object> codeExplanation) throws IOException, JavaLayerException {
		
		String explanationCode = codeExplanation.get("explanation").toString();
		
		//create the test class
				PollyDemo helloWorld = new PollyDemo(Region.getRegion(Regions.US_EAST_1));
				helloWorld.setSAMPLE(explanationCode);
				
				//get the audio stream
				InputStream speechStream = helloWorld.synthesize(helloWorld.getSAMPLE(), OutputFormat.Mp3);

				//create an MP3 player
				AdvancedPlayer player = new AdvancedPlayer(speechStream,
						javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

				player.setPlayBackListener(new PlaybackListener() {
					@Override
					public void playbackStarted(PlaybackEvent evt) {
						System.out.println("Playback started");
						System.out.println(helloWorld.getSAMPLE());
					}
					
					@Override
					public void playbackFinished(PlaybackEvent evt) {
						System.out.println("Playback finished");
					}
				});
				
				
				// play it!
				player.play();
		
		
	}

}
