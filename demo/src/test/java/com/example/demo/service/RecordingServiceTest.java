package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
class RecordingServiceTest {
	@Autowired
	private RecordingService recordingService;
	@Autowired
	private PlayingService playingService;
	@Autowired
	private TuneService tuneService;
	@Autowired
	private MusicianService musicianService;
	
	@BeforeEach
	void setupService() {
		recordingService.delAllRecording();
		playingService.delAllPlaying();
		tuneService.delAllTune();
		musicianService.delAllMusician();
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		musicianService.saveMusician(player);
		Musician composer = new Musician();
		composer.setName("Jim Hall");
		musicianService.saveMusician(composer);
		Tune tune = new Tune();
		tune.setTitle("Something Special");
		tune.setComposer(composer);
		tuneService.saveTune(tune);
		Playing pl = new Playing();
		pl.setPart(Part.GT);
		pl.setPlayer(player);
		playingService.savePlaying(pl);
		Recording rec = new Recording();
		rec.setTune(tune);
		rec.addPlaying(pl);
		recordingService.saveRecording(rec);
	}
	
	@Test
	void testGetRecordingById() {
		List<Recording> recList = recordingService.getAllRecording();
		if (recList.size() != 1) {
			fail("get all fail");
		}
		Long id = recList.get(0).getId();
		Optional<Recording> rec = recordingService.getRecordingById(id);
		if (rec.isEmpty()) {
			fail("get by id");
		}
	}

	@Test
	void testGetAllRecording() {
		List<Recording> rl = recordingService.getAllRecording();
		if (rl.size() != 1) {
			fail("get all");
		}
	}

	@Test
	void testGetRecordingBytuneTitle() {
		List<Recording> rl = recordingService.getRecordingByTuneTitle("Something Special");
		if (rl.size() != 1) {
			fail("get by title");
		}
	}
	
	@Test
	void testGetRecordingByPlayerName() {
		List<Recording> rl = recordingService.getRecordingByPlayerName("Nobutoshi Ozawa");
		if (rl.size() != 1) {
			fail("get by player name");
		}
	}

	@Test
	void testDelRecordingById() {
		List<Recording> recList = recordingService.getAllRecording();
		if (recList.size() != 1) {
			fail("get all fail");
		}
		Long id = recList.get(0).getId();
		recordingService.delRecordingById(id);
		recList = recordingService.getAllRecording();
		if (recList.size() != 0) {
			fail("del by id");
		}
	}

	@Test
	void testDelAllRecording() {
		recordingService.delAllRecording();
		List<Recording> recList = recordingService.getAllRecording();
		if (recList.size() != 0) {
			fail("del by id");
		}
	}

	@Test
	void testDelRecordings() {
		List<Recording> recList = recordingService.getAllRecording();
		if (recList.size() != 1) {
			fail("get all fail");
		}
		recordingService.delRecordings(recList);
		List<Recording> recList2 = recordingService.getAllRecording();
		if (recList2.size() != 0) {
			fail("del by id");
		}
	}

	@Test
	void testSaveRecording() {
		Musician player = new Musician();
		player.setName("Charles Mingus");
		Playing pl = new Playing();
		pl.setPart(Part.B);
		pl.setPlayer(player);
		Recording rec = new Recording();
		List<Tune> tuneList = tuneService.getTuneByTitle("Something Special");
		Tune tune = tuneList.get(0);
		rec.setTune(tune);
		rec.addPlaying(pl);
		musicianService.saveMusician(player);
		playingService.savePlaying(pl);
		recordingService.saveRecording(rec);
		List<Recording> rl = recordingService.getAllRecording();
		if (rl.size() != 2) {
			fail("save recording");
		}
	}

	@Test
	void testSaveAllRecording() {
		Musician player = new Musician();
		player.setName("Charles Mingus");
		Playing pl = new Playing();
		pl.setPart(Part.B);
		pl.setPlayer(player);
		Recording rec = new Recording();
		List<Tune> tuneList = tuneService.getTuneByTitle("Something Special");
		Tune tune = tuneList.get(0);
		rec.setTune(tune);
		rec.addPlaying(pl);
		List<Recording> rl = new ArrayList<Recording>();
		rl.add(rec);
		musicianService.saveMusician(player);
		playingService.savePlaying(pl);
		recordingService.saveAllRecording(rl);
		rl = recordingService.getAllRecording();
		if (rl.size() != 2) {
			fail("save all recording");
		}
	}

	@Test
	void testSaveAllAndFlush() {
		Musician player = new Musician();
		player.setName("Charles Mingus");
		Playing pl = new Playing();
		pl.setPart(Part.B);
		pl.setPlayer(player);
		Recording rec = new Recording();
		List<Tune> tuneList = tuneService.getTuneByTitle("Something Special");
		Tune tune = tuneList.get(0);
		rec.setTune(tune);
		rec.addPlaying(pl);
		List<Recording> rl = new ArrayList<Recording>();
		rl.add(rec);
		musicianService.saveMusician(player);
		playingService.savePlaying(pl);
		recordingService.saveAllAndFlush(rl);
		rl = recordingService.getAllRecording();
		if (rl.size() != 2) {
			fail("save all recording");
		}
	}
}
