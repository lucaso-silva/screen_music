package com.example.screen_music;

import com.example.screen_music.main.ScreenMusicApp;
import com.example.screen_music.repository.ArtistRepository;
import com.example.screen_music.service.UseChatGPT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMusicApplication implements CommandLineRunner {
	@Autowired
	private ArtistRepository repository;
	@Autowired
	private UseChatGPT chatGPTService;

	@Override
	public void run(String... args) throws Exception {
		ScreenMusicApp app = new ScreenMusicApp(repository, chatGPTService);
		app.displayMenu();
	}

	public static void main(String[] args) {
		SpringApplication.run(ScreenMusicApplication.class, args);
	}

}
