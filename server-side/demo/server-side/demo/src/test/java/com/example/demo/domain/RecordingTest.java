package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class RecordingTest {
	private Recording recording;
	@BeforeEach
	void setup() {
		recording = new Recording();
	}
	@Test
	void testGetTune() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Answer Is Yes");
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		recording.setTune(tune);
		recording.addPlaying(playing);
		if (!recording.getTune().getTitle().equals("Answer Is Yes")) {
			fail("get tune");
		}
	}

	@Test
	void testSetTune() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Answer Is Yes");
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		recording.setTune(tune);
		recording.addPlaying(playing);
		if (!recording.getTune().getTitle().equals("Answer Is Yes")) {
			fail("get tune");
		}
	}

	@Test
	void testGetPlayingList() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Answer Is Yes");
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		recording.setTune(tune);
		recording.addPlaying(playing);
		if (recording.getPlayingList().size() != 1) {
			fail("get playing list");
		}
	}

	@Test
	void testSetPlayingList() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Answer Is Yes");
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		List<Playing> pl = new ArrayList<Playing>();
		pl.add(playing);
		recording.setTune(tune);
		recording.setPlayingList(pl);
		if (recording.getPlayingList().size() != 1) {
			fail("get playing list");
		}
	}

	@Test
	void testGetPlayingListByPlayerName() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Answer Is Yes");
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		List<Playing> pl = new ArrayList<Playing>();
		pl.add(playing);
		recording.setTune(tune);
		recording.setPlayingList(pl);
		if (recording.getPlayingListByPlayerName("Nobutoshi Ozawa").size() != 1) {
			fail("get playing list by name");
		}
	}

	@Test
	void testAddPlaying() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Answer Is Yes");
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		recording.setTune(tune);
		recording.addPlaying(playing);
		if (recording.getPlayingList().size() != 1) {
			fail("add playing list");
		}
	}
}
