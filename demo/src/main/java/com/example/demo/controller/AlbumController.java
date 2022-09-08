package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Album;
import com.example.demo.service.AlbumService;

@RestController
@RequestMapping("/api/album")
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Album> getAllAlbum() {
		return albumService.getAllAlbum();
	}
	@RequestMapping(value = "/{ID}", method = RequestMethod.GET)
	public Album getAlbumById(@PathVariable(value = "ID") Long id) {
		return albumService.getAlbumById(id).get();
	}
	@RequestMapping(value = "/{title}", method = RequestMethod.GET)
	public List<Album> getAlbumByTitle(@PathVariable(value = "title") String title) {
		return albumService.getAlbumByTitle(title);
	}
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public void saveAlbum(@RequestBody Album album) {
		albumService.saveAlbum(album);
	}
	@RequestMapping(value = "/save_all", method = RequestMethod.PUT)
	public void saveAllAlbum(@RequestBody List<Album> albumList) {
		albumService.saveAllAlbum(albumList);
	}
	@RequestMapping(value = "/delete/{ID}", method = RequestMethod.DELETE)
	public void delAlbumById(@PathVariable(value = "ID") Long id) {
		albumService.delAlbumById(id);
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delAllAlbum() {
		albumService.delAllAlbum();
	}
}
