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
import com.example.demo.domain.Tune;
import com.example.demo.service.AlbumService;
import com.example.demo.service.MusicianService;
import com.example.demo.service.PlayingService;
import com.example.demo.service.RecordingService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class TuneControllerTest {
	@Autowired
	private TuneController tuneController;
	@Autowired
	private MusicianService musicianService;
	@Autowired
	private PlayingService playingService;
	@Autowired
	private RecordingService recordingService;
	@Autowired
	private AlbumService albumService;
	
	@BeforeEach
	void setupService() {
		albumService.delAllAlbum();
		recordingService.delAllRecording();
		playingService.delAllPlaying();
		tuneController.delAllTune();
		musicianService.delAllMusician();
		Musician m = new Musician();
		m.setName("Richard Rodgers");
		musicianService.saveMusician(m);
		Tune t = new Tune();
		t.setTitle("My Favorite Things");
		t.setComposer(m);
		tuneController.saveTune(t);
	}
	@Test
	void testGetAllTune() {
		List<Tune> tl = tuneController.getAllTune();
		if (tl.size() != 1) {
			fail("get all");
		}
	}

	@Test
	void testGetTuneById() {
		Tune t = tuneController.getTuneByTitle("My Favorite Things");
		Long id = t.getId();
		t = tuneController.getTuneById(id);
		if (!t.getTitle().equals("My Favorite Things")) {
			fail("get by id");
		}
	}

	@Test
	void testGetTuneByTitle() {
		Tune t = tuneController.getTuneByTitle("My Favorite Things");
		if (!t.getTitle().equals("My Favorite Things")) {
			fail("get by id");
		}
	}

	@Test
	void testDelAllTune() {
		tuneController.delAllTune();
		if (tuneController.getAllTune().size() != 0) {
			fail("del all");
		}
	}

	@Test
	void testDelTuneById() {
		Tune t = tuneController.getTuneByTitle("My Favorite Things");
		Long id = t.getId();
		tuneController.delTuneById(id);
		if (tuneController.getAllTune().size() != 0) {
			fail("del by id");
		}
	}

	@Test
	void testSaveTune() {
		Tune t = new Tune();
		t.setTitle("Blue Moon");
		t.setComposer(musicianService.getMusicianByName("Richard Rodgers").get(0));
		tuneController.saveTune(t);
		if (tuneController.getAllTune().size() != 2) {
			fail("save");
		}
	}

	@Test
	void testSaveAllTune() {
		Tune t = new Tune();
		t.setTitle("Blue Moon");
		t.setComposer(musicianService.getMusicianByName("Richard Rodgers").get(0));
		List<Tune> tl = new ArrayList<Tune>();
		tl.add(t);
		tuneController.saveAllTune(tl);
		if (tuneController.getAllTune().size() != 2) {
			fail("save");
		}
	}
}
