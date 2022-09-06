package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Playing;
import com.example.demo.service.PlayingService;

@RestController
@RequestMapping("/api/playing")
public class PlayingController {
	@Autowired
	private PlayingService playingService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Playing> getAllPlaying() {
		return playingService.getAllPlaying();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Playing getPlayingById(@PathVariable(value = "id") Long id) {
		return playingService.getPlayingById(id).get();
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delAllPlaying() {
		playingService.delAllPlaying();
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delPlayingById(@PathVariable(value = "id") Long id) {
		playingService.delPlayingById(id);
	}
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public void savePlaying(@RequestBody Playing playing) {
		playingService.savePlaying(playing);
	}
	@RequestMapping(value = "/save_all", method = RequestMethod.PUT)
	public void saveAllPlaying(@RequestBody List<Playing> playingList) {
		playingService.saveAllPlaying(playingList);
	}
}
