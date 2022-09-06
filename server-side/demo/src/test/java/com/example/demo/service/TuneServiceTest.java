package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Musician;
import com.example.demo.domain.Tune;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class TuneServiceTest {
	@Autowired
	private TuneService tuneService;
	@Autowired
	private MusicianService musicianService;
	
	@BeforeAll
	void setupService() {
		Musician composer = new Musician();
		composer.setName("Nobutoshi Ozawa");
		musicianService.saveMusician(composer);
		Tune tune = new Tune();
		tune.setTitle("For James");
		tune.setComposer(composer);
		tuneService.saveTune(tune);
	}

	@Test
	void testGetTuneById() {
		List<Tune> tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("Tune List is empty");
		}
		Tune tune = tuneList.get(0);
		Long id = tune.getId();
		Optional<Tune> res;
		res = tuneService.getTuneById(id);
		if (res.isEmpty()) {
			fail("Can't gret by ID");
		}
	}

	@Test
	void testGetAllTune() {
		List<Tune> tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("GetAll fail");
		}
	}

	@Test
	void testGetTuneByTitle() {
		List<Tune> tuneList = tuneService.getTuneByTitle("For James");
		if (tuneList.size() <= 0) {
			fail("Get By Title fail");
		}
	}

	@Test
	void testDelTuneById() {
		List<Tune> tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("Tune List is empty");
		}
		Tune tune = tuneList.get(0);
		Long id = tune.getId();
		Optional<Tune> res;
		tuneService.delTuneById(id);
		res = tuneService.getTuneById(id);
		if (res.isPresent()) {
			fail("Can't delete by ID");
		}
		tuneService.saveAllTune(tuneList);
	}

	@Test
	void testDelAllTune() {
		List<Tune> tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("Tune List is empty");
		}
		Tune tune = tuneList.get(0);
		tuneService.delAllTune();
		tuneList = tuneService.getAllTune();
		if (tuneList.size() != 0) {
			fail("Tune List is NOT empty");
		}
		tuneService.saveTune(tune);
	}

	@Test
	void testDelTunes() {
		List<Tune> tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("Tune List is empty");
		}
		Tune tune = tuneList.get(0);
		tuneService.delTunes(tuneList);
		tuneList = tuneService.getAllTune();
		if (tuneList.size() != 0) {
			fail("Tune List is NOT empty");
		}
		tuneService.saveTune(tune);
	}

	@Test
	void testSaveTune() {
		List<Tune> tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("save fail");
		}
	}

	@Test
	void testSaveAllTune() {
		tuneService.delAllTune();
		Musician composer = musicianService.getAllMusician().get(0);
		Tune tune = new Tune();
		tune.setTitle("For James");
		tune.setComposer(composer);
		List<Tune> tuneList = new ArrayList<Tune>();
		tuneList.add(tune);
		tuneService.saveAllTune(tuneList);
		tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("save fail");
		}
	}

	@Test
	void testSaveAllAndFlush() {
		tuneService.delAllTune();
		Musician composer = musicianService.getAllMusician().get(0);
		Tune tune = new Tune();
		tune.setTitle("For James");
		tune.setComposer(composer);
		List<Tune> tuneList = new ArrayList<Tune>();
		tuneList.add(tune);
		tuneService.saveAllAndFlush(tuneList);
		tuneList = tuneService.getAllTune();
		if (tuneList.size() <= 0) {
			fail("save fail");
		}
	}
}
