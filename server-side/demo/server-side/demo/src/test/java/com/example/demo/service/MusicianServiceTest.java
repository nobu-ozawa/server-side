package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Musician;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class MusicianServiceTest {
	@Autowired
	private MusicianService musicianService;
	private Musician musician;
	@Autowired
	private TuneService tuneService;
	
	@BeforeAll
	void setupService() {
		tuneService.delAllTune();
		musicianService.delAllMusician();
		musician = new Musician();
		musician.setName("Nobutoshi Ozawa");
		musicianService.saveMusician(musician);
	}
	
	@Test
	void testGetMusicianById() {
		musicianService.saveMusician(musician);
		List<Musician> musicianList = musicianService.getMusicianByName("Nobutoshi Ozawa");
		if (musicianList.size() <= 0) {
			fail("getByName filed");
		}
		Optional<Musician> m = musicianService.getMusicianById(musicianList.get(0).getId());
		if (m.isEmpty()) {
			fail("can't get");
		}
	}

	@Test
	void testGetAllMusician() {
		List<Musician> musicianList = musicianService.getAllMusician();
		if (musicianList.size() <= 0) {
			fail("can't get List");
		}
	}

	@Test
	@Disabled
	void testGetMusicianByName() {
		List<Musician> musicianList = musicianService.getMusicianByName("Nobutoshi Ozawa");
		if (musicianList.size() <= 0) {
			fail("can't get List");
		}
	}

	@Test
	void testDelMusicianById() {
		Musician m = musicianService.getMusicianByName("Nobutoshi Ozawa").get(0);
		musicianService.delMusicianById(m.getId());
		List<Musician> mList = musicianService.getMusicianByName("Nobutoshi Ozawa");
		if (mList.size() != 0) {
			fail("削除エラー");
		}
		musicianService.saveMusician(m);
	}

	@Test
	void testDelAllMusician() {
		Musician m = musicianService.getMusicianByName("Nobutoshi Ozawa").get(0);
		musicianService.delAllMusician();
		List<Musician> mList = musicianService.getAllMusician();
		if (mList.size() != 0) {
			fail("削除エラー");
		}
		musicianService.saveMusician(m);
	}

	@Test
	void testDelMusicians() {
		Musician m = musicianService.getMusicianByName("Nobutoshi Ozawa").get(0);
		List<Musician> mList = musicianService.getAllMusician();
		musicianService.delMusicians(mList);
		List<Musician> mList2 = musicianService.getAllMusician();
		if (mList2.size() != 0) {
			fail("削除エラー");
		}
		musicianService.saveMusician(m);
	}

	@Test
	void testSaveMusician() {
		Musician m = musicianService.getMusicianByName("Nobutoshi Ozawa").get(0);
		musicianService.delAllMusician();
		musicianService.saveMusician(m);
		List<Musician> mList = musicianService.getAllMusician();
		if (mList.size() != 1) {
			fail("save error");
		}
	}

	@Test
	void testSaveAllMusician() {
		List<Musician> mList = musicianService.getMusicianByName("Nobutoshi Ozawa");
		musicianService.delAllMusician();
		musicianService.saveAllMusician(mList);
		List<Musician> mList2 = musicianService.getAllMusician();
		if (mList2.size() != 1) {
			fail("saveAll error");
		}
	}

	@Test
	void testSaveAllAndFlush() {
		List<Musician> mList = musicianService.getMusicianByName("Nobutoshi Ozawa");
		musicianService.delAllMusician();
		musicianService.saveAllAndFlush(mList);
		List<Musician> mList2 = musicianService.getAllMusician();
		if (mList2.size() != 1) {
			fail("saveAll error");
		}
	}
}
