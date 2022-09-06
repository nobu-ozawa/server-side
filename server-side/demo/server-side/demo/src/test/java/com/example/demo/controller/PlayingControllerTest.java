package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Musician;
import com.example.demo.domain.Part;
import com.example.demo.domain.Playing;
import com.example.demo.service.MusicianService;
import com.example.demo.service.TuneService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class PlayingControllerTest {
	@Autowired
	private PlayingController playingController;
	@Autowired
	private MusicianService musicianService;
	@Autowired
	private TuneService tuneService;
	@BeforeEach
	void setupService() {
		tuneService.delAllTune();
		playingController.delAllPlaying();
		musicianService.delAllMusician();
		Musician m = new Musician();
		m.setName("Nobutoshi Ozawa");
		musicianService.saveMusician(m);
		Playing p = new Playing();
		p.setPlayer(m);
		p.setPart(Part.GT);
		playingController.savePlaying(p);
	}
	@Test
	void testGetAllPlaying() {
		List<Playing> pl = playingController.getAllPlaying();
		if (pl.size() != 1) {
			fail("get all");
		}
	}

	@Test
	void testGetPlayingById() {
		Long id = playingController.getAllPlaying().get(0).getId();
		Playing pl = playingController.getPlayingById(id);
		if (pl.getPart() != Part.GT) {
			fail("get by id");
		}
	}

	@Test
	void testDelAllPlaying() {
		playingController.delAllPlaying();
		List<Playing> pl = playingController.getAllPlaying();
		if (pl.size() != 0) {
			fail("del all");
		}
	}

	@Test
	void testDelPlayingById() {
		Long id = playingController.getAllPlaying().get(0).getId();
		playingController.delPlayingById(id);
		List<Playing> pl = playingController.getAllPlaying();
		if (pl.size() != 0) {
			fail("del by id");
		}
	}

	@Test
	void testSavePlaying() {
		Musician m = new Musician();
		m.setName("Jim Hall");
		musicianService.saveMusician(m);
		Playing p = new Playing();
		p.setPlayer(m);
		p.setPart(Part.GT);
		playingController.savePlaying(p);
		if (playingController.getAllPlaying().size() != 2) {
			fail("save");
		}
	}

	@Test
	void testSaveAllPlaying() {
		Musician m = new Musician();
		m.setName("Jim Hall");
		musicianService.saveMusician(m);
		Playing p = new Playing();
		p.setPlayer(m);
		p.setPart(Part.GT);
		List<Playing> pl = new ArrayList<Playing>();
		pl.add(p);
		playingController.saveAllPlaying(pl);
		if (playingController.getAllPlaying().size() != 2) {
			fail("save all");
		}
	}
}
