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

import com.example.demo.domain.Album;
import com.example.demo.domain.Recording;
import com.example.demo.domain.SalesChannel;
import com.example.demo.domain.Tune;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AlbumServiceTest {
	@Autowired
	private AlbumService albumService;
	
	@BeforeAll
	void setupService() {
		Tune tune = new Tune();
		tune.setTitle("Blue Bossa");
		Recording rec = new Recording();
		Album album = new Album();
		album.setTitle("Hello World");
		album.setChannel(SalesChannel.CD);
		album.addContents(rec);
		albumService.saveAlbum(album);
	}
	
	@Test
	void testGetAlbumById() {
		List<Album> albumList = albumService.getAllAlbum();
		Album album = albumList.get(0);
		Long id = album.getId();
		Optional<Album> album2 = albumService.getAlbumById(id);
		if (album2.isEmpty()) {
			fail("get album fail");
		}
		if (album.getId() != album2.get().getId()) {
			fail("get album deffer");
		}
	}

	@Test
	void testGetAllAlbum() {
		List<Album> albumList = albumService.getAllAlbum();
		if (albumList.size() <= 0) {
			fail("get all album fail");
		}
	}

	@Test
	void testGetAlbumByTitle() {
		List<Album> albumList = albumService.getAlbumByTitle("Hello World");
		if (albumList.size() != 1) {
			fail("get album by tirle fail");
		}
	}

	@Test
	void testDelAlbumById() {
		List<Album> albumList = albumService.getAllAlbum();
		Album album = albumList.get(0);
		albumService.delAllAlbum();
		albumService.saveAlbum(album);
		album = albumService.getAllAlbum().get(0);
		Long id = album.getId();
		albumService.delAlbumById(id);
		List<Album> albumList2 = albumService.getAllAlbum();
		if (albumList2.size() != 0) {
			fail("delete album fail");
		}
		albumService.saveAlbum(album);
	}

	@Test
	void testDelAllAlbum() {
		List<Album> albumList = albumService.getAllAlbum();
		albumService.delAllAlbum();
		List<Album> albumList2 = albumService.getAllAlbum();
		if (albumList2.size() != 0) {
			fail("delete all album fail");
		}
		albumService.saveAllAlbum(albumList);
	}

	@Test
	void testDelAlbums() {
		List<Album> albumList = albumService.getAllAlbum();
		albumService.delAlbums(albumList);
		List<Album> albumList2 = albumService.getAllAlbum();
		if (albumList2.size() != 0) {
			fail("delete all album fail");
		}
		albumService.saveAllAlbum(albumList);
	}

	@Test
	void testSaveAlbum() {
		albumService.delAllAlbum();
		Tune tune = new Tune();
		tune.setTitle("Blue Bossa");
		Recording rec = new Recording();
		Album album = new Album();
		album.setTitle("Hello World");
		album.setChannel(SalesChannel.CD);
		album.addContents(rec);
		albumService.saveAlbum(album);
		if (albumService.getAllAlbum().size() != 1) {
			fail("save fail");
		}
	}

	@Test
	void testSaveAllAlbum() {
		albumService.delAllAlbum();
		Tune tune = new Tune();
		tune.setTitle("Blue Bossa");
		Recording rec = new Recording();
		Album album = new Album();
		album.setTitle("Hello World");
		album.setChannel(SalesChannel.CD);
		album.addContents(rec);
		List<Album> albumList = new ArrayList<Album>();
		albumList.add(album);
		albumService.saveAllAlbum(albumList);
		if (albumService.getAllAlbum().size() != 1) {
			fail("save fail");
		}
	}

	@Test
	void testSaveAllAndFlush() {
		albumService.delAllAlbum();
		Tune tune = new Tune();
		tune.setTitle("Blue Bossa");
		Recording rec = new Recording();
		Album album = new Album();
		album.setTitle("Hello World");
		album.setChannel(SalesChannel.CD);
		album.addContents(rec);
		List<Album> albumList = new ArrayList<Album>();
		albumList.add(album);
		albumService.saveAllAndFlush(albumList);
		if (albumService.getAllAlbum().size() != 1) {
			fail("save fail");
		}
	}
}
