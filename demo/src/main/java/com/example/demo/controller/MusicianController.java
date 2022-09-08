package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Musician;
import com.example.demo.service.MusicianService;

@RestController
@RequestMapping("/api/musician")
public class MusicianController {
	@Autowired
	private MusicianService musicianService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Musician> getAllMusician() {
		return musicianService.getAllMusician();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Musician getMusicianById(@PathVariable(value = "id") Long id) {
		return musicianService.getMusicianById(id).get();
	}
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public Musician getMusicianByName(@RequestParam(value = "name", defaultValue = "World") String name) {
		return musicianService.getMusicianByName(name).get(0);
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delAllMusician() {
		musicianService.delAllMusician();
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delAllMusicianById(@PathVariable(value = "id") Long id) {
		musicianService.delMusicianById(id);
	}
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public void saveMusician (@RequestBody Musician musician) {
		musicianService.saveMusician(musician);
	}
	@RequestMapping(value = "/save_all", method = RequestMethod.PUT)
	public void saveAllMusician (@RequestBody List<Musician> musicianList) {
		musicianService.saveAllMusician(musicianList);
	}
}
