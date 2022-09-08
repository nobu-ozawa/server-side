package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Tune;
import com.example.demo.domain.TuneRepository;

@Service
@Transactional
public class TuneService {
	@Autowired
	private TuneRepository tuneRepository;
	
	public Optional<Tune> getTuneById(Long id) {
		return tuneRepository.findById(id);
	}
	
	public List<Tune> getAllTune() {
		return tuneRepository.findAll();
	}
	
	public List<Tune> getTuneByTitle(String title) {
		return tuneRepository.findAll().stream()
			.filter(t -> t.getTitle().equals(title))
			.collect(Collectors.toList());
	}
	
	public void delTuneById(Long id) {
		tuneRepository.deleteById(id);
	}
	public void delAllTune() {
		tuneRepository.deleteAll();
	}
	public void delTunes(List<Tune> tuneList) {
		tuneRepository.deleteAll(tuneList);
	}
	
	public void saveTune(Tune newTune) {
		tuneRepository.save(newTune);
	}
	public void saveAllTune(List<Tune> tuneList) {
		tuneRepository.saveAll(tuneList);
	}
	public void saveAllAndFlush(List<Tune> tuneList) {
		tuneRepository.saveAllAndFlush(tuneList);
	}
	
	public void flush() {
		tuneRepository.flush();
	}
}
