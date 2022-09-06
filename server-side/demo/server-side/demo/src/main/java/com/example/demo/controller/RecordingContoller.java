package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Recording;
import com.example.demo.service.RecordingService;

@RestController
@RequestMapping("/api/recording")
public class RecordingContoller {
	@Autowired
	private RecordingService recordingService;
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Recording> getAllRecording() {
		return recordingService.getAllRecording();
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Recording getReordingById(@PathVariable(value = "id") Long id) {
		return recordingService.getRecordingById(id).get();
	}
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public List<Recording> getRecordingByPlayerName(@RequestParam(value = "name", defaultValue = "Nobody") String name) {
		return recordingService.getRecordingByPlayerName(name);
	}
	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public List<Recording> getRecordingByTuneTitle(@RequestParam(value = "title", defaultValue = "Nobody") String title) {
		return recordingService.getRecordingByTuneTitle(title);
	}
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void delAllRecording() {
		recordingService.delAllRecording();
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delRecordingById(@PathVariable(value = "id") Long id) {
		recordingService.delRecordingById(id);
	}
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public void saveRecording(@RequestBody Recording recording) {
		recordingService.saveRecording(recording);
	}
	@RequestMapping(value = "/save_all", method = RequestMethod.PUT)
	public void saveAllRecording(@RequestBody List<Recording> recordingList) {
		recordingService.saveAllRecording(recordingList);
	}
}
