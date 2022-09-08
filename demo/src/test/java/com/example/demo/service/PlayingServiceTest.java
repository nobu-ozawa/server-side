package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Musician;
import com.example.demo.domain.Part;
import com.example.demo.domain.Playing;
import com.example.demo.domain.Recording;
import com.example.demo.domain.Tune;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class PlayingServiceTest {
	@Autowired
	private PlayingService playingService;
	@Autowired
	private MusicianService musicianService;
	@Autowired
	private TuneService tuneService;
	
	@BeforeEach
	void setupService() {
		playingService.delAllPlaying();
		tuneService.delAllTune();
		musicianService.delAllMusician();
		
		Playing playing = new Playing();
		
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		
		Musician composer = new Musician();
		composer.setName("Jim Hall");
		musicianService.saveMusician(player);
		musicianService.saveMusician(composer);
		Recording recording = new Recording();
		Tune tune = new Tune();
		tune.setTitle("Something Special");
		tune.setComposer(composer);
		tuneService.saveTune(tune);
		recording.setTune(tune);
		playing.setPart(Part.GT);
		playing.setPlayer(player);		
		playingService.savePlaying(playing);
	}
	@Test
	void testGetPlayingById() {
		List<Playing> playingList = playingService.getAllPlaying();
		Playing playing = playingList.get(0);
		Long id = playing.getId();
		Optional<Playing> playing2 = playingService.getPlayingById(id);
		if (playing2.isEmpty()) {
			fail("get by id fail");
		}
	}

	@Test
	void testGetAllPlaying() {
		List<Playing> playingList = playingService.getAllPlaying();
		if (playingList.size() != 1) {
			fail("get all fail");
		}
	}

	@Test
	void testGetPlayingByMusicianName() {
		List<Playing> playingList = playingService.getPlayingByMusicianName("Nobutoshi Ozawa");
		if (playingList.size() != 1) {
			fail("get all fail");
		}
	}

	@Test
	void testDelPlayingById() {
		List<Playing> playingList = playingService.getAllPlaying();
		Playing playing = playingList.get(0);
		Long id = playing.getId();
		playingService.delPlayingById(id);
		List<Playing> playingList2 = playingService.getAllPlaying();
		if (playingList2.size() > 0) {
			fail("del by id fail");
		}
		playingService.saveAllPlaying(playingList);
	}

	@Test
	void testDelAllPlaying() {
		List<Playing> playingList = playingService.getAllPlaying();
		playingService.delAllPlaying();
		List<Playing> playingList2 = playingService.getAllPlaying();
		if (playingList2.size() > 0) {
			fail("del by id fail");
		}
		playingService.saveAllPlaying(playingList);
	}

	@Test
	void testDelPlaying() {
		List<Playing> playingList = playingService.getAllPlaying();
		playingService.delPlaying(playingList);
		List<Playing> playingList2 = playingService.getAllPlaying();
		if (playingList2.size() > 0) {
			fail("del by id fail");
		}
		playingService.saveAllPlaying(playingList);
	}

	@Test
	void testSavePlaying() {
		List<Playing> playingList = playingService.getAllPlaying();
		Playing playing = playingList.get(0);
		playingService.delPlaying(playingList);
		List<Playing> playingList2 = playingService.getAllPlaying();
		if (playingList2.size() > 0) {
			fail("del by id fail");
		}
		playingService.savePlaying(playing);
		List<Playing> playingList3 = playingService.getAllPlaying();
		if (playingList3.size() != 1) {
			fail("save fail");
		}
	}

	@Test
	void testSaveAllPlaying() {
		List<Playing> playingList = playingService.getAllPlaying();
		playingService.saveAllPlaying(playingList);
		List<Playing> playingList3 = playingService.getAllPlaying();
		if (playingList3.size() != 1) {
			fail("save fail");
		}
	}

	@Test
	void testSaveAllAndFlush() {
		List<Playing> playingList = playingService.getAllPlaying();
		playingService.saveAllAndFlush(playingList);
		List<Playing> playingList3 = playingService.getAllPlaying();
		if (playingList3.size() != 1) {
			fail("save fail");
		}
	}
}
