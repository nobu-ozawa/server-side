package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Tune;
import com.example.demo.service.TuneService;

@RestController
@RequestMapping("/api/tune")
public class TuneController {
	@Autowired
	private TuneService tuneService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Tune> getAllTune() {
		return tuneService.getAllTune();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Tune getTuneById(@PathVariable(value = "id") Long id) {
		return tuneService.getTuneById(id).get();
	}
	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public Tune getTuneByTitle(@RequestParam(value = "title", defaultValue = "World") String title) {
		return tuneService.getTuneByTitle(title).get(0);
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delAllTune() {
		tuneService.delAllTune();
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delTuneById(@PathVariable(value = "id") Long id) {
		tuneService.delTuneById(id);
	}
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public void saveTune(@RequestBody Tune newTune) {
		tuneService.saveTune(newTune);
	}
	@RequestMapping(value = "/save_all", method = RequestMethod.PUT)
	public void saveAllTune(@RequestBody List<Tune> tuneList) {
		tuneService.saveAllTune(tuneList);
	}
}
