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

import com.example.demo.domain.Album;
import com.example.demo.domain.Musician;
import com.example.demo.domain.Part;
import com.example.demo.domain.Playing;
import com.example.demo.domain.Recording;
import com.example.demo.domain.SalesChannel;
import com.example.demo.domain.Tune;
import com.example.demo.service.MusicianService;
import com.example.demo.service.PlayingService;
import com.example.demo.service.RecordingService;
import com.example.demo.service.TuneService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AlbumControllerTest {
	@Autowired
	private AlbumController albumController;
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
		albumController.delAllAlbum();
		recordingService.delAllRecording();
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
		recordingService.saveRecording(r);
		Album a = new Album();
		a.setTitle("My Favorite Songs");
		a.setChannel(SalesChannel.CD);
		a.addContents(r);
		albumController.saveAlbum(a);
	}
	@Test
	void testGetAllAlbum() {
		List<Album> al = albumController.getAllAlbum();
		if (al.size() != 1) {
			fail("get all");
		}
	}

	@Test
	void testGetAlbumById() {
		Album a = albumController.getAllAlbum().get(0);
		Long id = a.getId();
		Album a2 = albumController.getAlbumById(id);
		if (!a.getTitle().equals(a2.getTitle())) {
			fail("get by id");
		}
	}

	@Test
	void testGetAlbumByTitle() {
		List<Album> al = albumController.getAlbumByTitle("My Favorite Songs");
		if (al.size() != 1) {
			fail("get by title");
		}
	}

	@Test
	void testSaveAlbum() {
		Tune t = new Tune();
		t.setTitle("Blue Moon");
		t.setComposer(musicianService.getMusicianByName("Richard Rodgers").get(0));
		tuneService.saveTune(t);
		t = tuneService.getTuneByTitle("Blue Moon").get(0);
		Playing p = new Playing();
		p.setPart(Part.B);
		p.setPlayer(musicianService.getMusicianByName("Nobutoshi Ozawa").get(0));
		playingService.savePlaying(p);
		p = playingService.getPlayingByMusicianName("Nobutoshi Ozawa").get(0);
		Recording r = new Recording();
		r.setTune(t);
		r.addPlaying(p);
		recordingService.saveRecording(r);
		r = recordingService.getRecordingByTuneTitle("Blue Moon").get(0);
		Album a = albumController.getAllAlbum().get(0);
		a.addContents(r);
		albumController.saveAlbum(a);
		a = albumController.getAllAlbum().get(0);
		if (a.getContents().size() != 2) {
			fail("save album");
		}
	}

	@Test
	void testSaveAllAlbum() {
		Tune t = new Tune();
		t.setTitle("Blue Moon");
		t.setComposer(musicianService.getMusicianByName("Richard Rodgers").get(0));
		tuneService.saveTune(t);
		Playing p = new Playing();
		p.setPart(Part.GT);
		p.setPlayer(musicianService.getMusicianByName("Nobutoshi Ozawa").get(0));
		playingService.savePlaying(p);
		Recording r = new Recording();
		r.setTune(t);
		r.addPlaying(p);
		recordingService.saveRecording(r);
		Album a = albumController.getAllAlbum().get(0);
		a.addContents(r);
		List<Album> al = new ArrayList<Album>();
		al.add(a);
		albumController.saveAllAlbum(al);
		Album a2 = albumController.getAllAlbum().get(0);
		if (a2.getContents().size() != 2) {
			fail("save all album " + a2.getContents().size());
		}
	}

	@Test
	void testDelAlbumById() {
		Album a = albumController.getAllAlbum().get(0);
		Long id = a.getId();
		albumController.delAlbumById(id);
		if (albumController.getAllAlbum().size() != 0) {
			fail("del by id");
		}
	}

	@Test
	void testDelAllAlbum() {
		albumController.delAllAlbum();
		if (albumController.getAllAlbum().size() != 0) {
			fail("del all");
		}
	}

}
