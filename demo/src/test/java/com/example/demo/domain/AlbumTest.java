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
class AlbumTest {
	private Album album;
	@BeforeEach
	void setup() {
		album = new Album();
	}
	@Test
	void testGetTitle() {
		album.setTitle("Hello World");
		if (!album.getTitle().equals("Hello World")) {
			fail("get title");
		}
	}

	@Test
	void testSetTitle() {
		album.setTitle("Hello World");
		if (!album.getTitle().equals("Hello World")) {
			fail("set title");
		}
	}

	@Test
	void testGetContents() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Musician composer = new Musician();
		composer.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Romain");
		tune.setComposer(composer);
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		List<Playing> list = new ArrayList<Playing>();
		list.add(playing);
		Recording recording = new Recording();
		recording.setTune(tune);
		recording.setPlayingList(list);
		album.addContents(recording);
		if (!album.getContents().get(0).getTune().getTitle().equals("Romain")) {
			fail("get contents");
		}
	}

	@Test
	void testAddContents() {
		Musician player = new Musician();
		player.setName("Nobutoshi Ozawa");
		Musician composer = new Musician();
		composer.setName("Nobutoshi Ozawa");
		Tune tune = new Tune();
		tune.setTitle("Romain");
		tune.setComposer(composer);
		Playing playing = new Playing();
		playing.setPlayer(player);
		playing.setPart(Part.GT);
		List<Playing> list = new ArrayList<Playing>();
		list.add(playing);
		Recording recording = new Recording();
		recording.setTune(tune);
		recording.setPlayingList(list);
		album.addContents(recording);
		if (!album.getContents().get(0).getTune().getTitle().equals("Romain")) {
			fail("set contents");
		}
	}

	@Test
	void testGetChannel() {
		album.setChannel(SalesChannel.LP);
		if (album.getChannel() != SalesChannel.LP) {
			fail("get channel");
		}
	}

	@Test
	void testSetChannel() {
		album.setChannel(SalesChannel.LP);
		if (album.getChannel() != SalesChannel.LP) {
			fail("set channel");
		}
	}
}
