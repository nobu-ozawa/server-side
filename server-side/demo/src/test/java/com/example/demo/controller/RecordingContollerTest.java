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
import com.example.demo.domain.Recording;
import com.example.demo.domain.Tune;
import com.example.demo.service.MusicianService;
import com.example.demo.service.PlayingService;
import com.example.demo.service.TuneService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class RecordingContollerTest {
	@Autowired
	private RecordingContoller recordingController;
	@Autowired
	private TuneService tuneService;
	@Autowired
	private PlayingService playingService;
	@Autowired
	private MusicianService musicianService;
	
	@BeforeEach
	void setupService() {
		recordingController.delAllRecording();
		playingService.delAllPlaying();
		tuneService.delAllTune();
		musicianService.delAllMusician();
		Musician m = new Musician();
		m.setName("Nobutoshi Ozawa");
		musicianService.saveMusician(m);
		Musician c = new Musician();
		c.setName("Richard Rodgers");
		musicianService.saveMusician(c);
		Tune t = new Tune();
		t.setTitle("My Funny Valentine");
		t.setComposer(c);
		tuneService.saveTune(t);
		Playing p = new Playing();
		p.setPlayer(m);
		p.setPart(Part.GT);
		playingService.savePlaying(p);
		Recording r = new Recording();
		r.setTune(t);
		r.addPlaying(p);
		recordingController.saveRecording(r);
	}
	@Test
	void testGetAllRecording() {
		List<Recording> rl = recordingController.getAllRecording();
		if (rl.size() != 1) {
			fail("get all");
		}
	}

	@Test
	void testGetReordingById() {
		List<Recording> rl = recordingController.getAllRecording();
		Long id = rl.get(0).getId();
		Recording r = recordingController.getReordingById(id);
		if (!r.getTune().getTitle().equals("My Funny Valentine")) {
			fail("get by id");
		}
	}

	@Test
	void testGetRecordingByPlayerName() {
		List<Recording> rl = recordingController.getRecordingByPlayerName("Nobutoshi Ozawa");
		if (rl.size() != 1) {
			fail("get by name");
		}
	}

	@Test
	void testGetRecordingByTuneTitle() {
		List<Recording> rl = recordingController.getRecordingByTuneTitle("My Funny Valentine");
		if (rl.size() != 1) {
			fail("get by title");
		}
	}

	@Test
	void testDelAllRecording() {
		recordingController.delAllRecording();
		if (recordingController.getAllRecording().size() != 0) {
			fail("del all");
		}
	}

	@Test
	void testDelRecordingById() {
		Recording r = recordingController.getAllRecording().get(0);
		recordingController.delRecordingById(r.getId());
		if (recordingController.getAllRecording().size() != 0) {
			fail("del by id");
		}
	}

	@Test
	void testSaveRecording() {
		Musician m = musicianService.getMusicianByName("Nobutoshi Ozawa").get(0);
		Playing p = new Playing();
		p.setPlayer(m);
		p.setPart(Part.B);
		playingService.savePlaying(p);
		Recording r = recordingController.getRecordingByTuneTitle("My Funny Valentine").get(0);
		r.addPlaying(p);
		recordingController.saveRecording(r);
		if (recordingController.getAllRecording().size() != 1) {
			fail("save");
		}
	}

	@Test
	void testSaveAllRecording() {
		Musician m = musicianService.getMusicianByName("Nobutoshi Ozawa").get(0);
		Playing p = new Playing();
		p.setPlayer(m);
		p.setPart(Part.B);
		playingService.savePlaying(p);
		Recording r = recordingController.getRecordingByTuneTitle("My Funny Valentine").get(0);
		r.addPlaying(p);
		List<Recording> rl = new ArrayList<Recording>();
		rl.add(r);
		recordingController.saveAllRecording(rl);
		if (recordingController.getAllRecording().size() != 1) {
			fail("save all");
		}
	}
}
