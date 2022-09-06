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
import com.example.demo.service.AlbumService;
import com.example.demo.service.PlayingService;
import com.example.demo.service.RecordingService;
import com.example.demo.service.TuneService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class MusicianControllerTest {
	@Autowired
	private MusicianController musicianController;
	@Autowired
	private TuneService tuneService;
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
		tuneService.delAllTune();
		musicianController.delAllMusician();
		Musician m = new Musician();
		m.setName("Nobutoshi Ozawa");
		musicianController.saveMusician(m);
	}
	@Test
	void testGetAllMusician() {
		List<Musician> ml = musicianController.getAllMusician();
		if (ml.size() != 1) {
			fail("get all musiian");
		}
	}

	@Test
	void testGetMusicianById() {
		Musician musician = musicianController.getAllMusician().get(0);
		Long id = musician.getId();
		musician = musicianController.getMusicianById(id);
		if (!musician.getName().equals("Nobutoshi Ozawa")) {
			fail("get by id");
		}
	}
	
	@Test
	void testGetMusicianByName() {
		Musician musician = musicianController.getAllMusician().get(0);
		String name = musician.getName();
		musician = musicianController.getMusicianByName(name);
		if (!musician.getName().equals("Nobutoshi Ozawa")) {
			fail("get by name");
		}
	}
	
	@Test
	void testDelAllMusician() {
		musicianController.delAllMusician();
		List<Musician> musicianList = musicianController.getAllMusician();
		if (musicianList.size() != 0) {
			fail("del all");
		}
	}
	
	@Test
	void testDelMusicianById() {
		Long id = musicianController.getAllMusician().get(0).getId();
		musicianController.delAllMusicianById(id);
		if (musicianController.getAllMusician().size() != 0) {
			fail("del by id");
		}
	}
	
	@Test
	void testSaveMusician() {
		Musician m = new Musician();
		m.setName("Jim Hall");
		musicianController.saveMusician(m);
		if (musicianController.getMusicianByName("Jim Hall") == null) {
			fail("save");
		}
	}
	
	@Test
	void testSaveAllMusician() {
		Musician m = new Musician();
		m.setName("Jim Hall");
		List<Musician> mlist = new ArrayList<Musician>();
		mlist.add(m);
		musicianController.saveAllMusician(mlist);
		if (musicianController.getMusicianByName("Jim Hall") == null) {
			fail("save");
		}
	}
}
