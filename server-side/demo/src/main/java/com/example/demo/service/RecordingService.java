package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Recording;
import com.example.demo.domain.RecordingRepository;

@Service
@Transactional
public class RecordingService {
	@Autowired
	private RecordingRepository recordingRepository;
	
	public Optional<Recording> getRecordingById(Long id) {
		return recordingRepository.findById(id);
	}
	
	public List<Recording> getAllRecording() {
		return recordingRepository.findAll();
	}
	
	public List<Recording> getRecordingByTuneTitle(String title) {
		return recordingRepository.findAll().stream()
			.filter(t -> t.getTune().getTitle().equals(title))
			.collect(Collectors.toList());
	}
	public List<Recording> getRecordingByPlayerName(String name) {
		return recordingRepository.findAll().stream()
				.filter(r -> !(r.getPlayingListByPlayerName(name).isEmpty()))
				.collect(Collectors.toList());
	}
	public void delRecordingById(Long id) {
		recordingRepository.deleteById(id);
	}
	public void delAllRecording() {
		recordingRepository.deleteAll();
	}
	public void delRecordings(List<Recording> recordingList) {
		recordingRepository.deleteAll(recordingList);
	}
	
	public void saveRecording(Recording newRecording) {
		recordingRepository.save(newRecording);
	}
	public void saveAllRecording(List<Recording> recordingList) {
		recordingRepository.saveAll(recordingList);
	}
	public void saveAllAndFlush(List<Recording> recordingList) {
		recordingRepository.saveAllAndFlush(recordingList);
	}
	
	public void flush() {
		recordingRepository.flush();
	}
}
