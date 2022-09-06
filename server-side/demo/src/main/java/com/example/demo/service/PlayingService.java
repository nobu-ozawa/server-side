package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Playing;
import com.example.demo.domain.PlayingRepository;

@Service
@Transactional
public class PlayingService {
	@Autowired
	private PlayingRepository playingRepository;
	
	public Optional<Playing> getPlayingById(Long id) {
		return playingRepository.findById(id);
	}
	
	public List<Playing> getAllPlaying() {
		return playingRepository.findAll();
	}
	
	public List<Playing> getPlayingByMusicianName(String name) {
		return playingRepository.findAll().stream()
				.filter(t -> t.getPlayer().getName().equals(name))
				.collect(Collectors.toList());
	}
	
	public void delPlayingById(Long id) {
		playingRepository.deleteById(id);
	}
	public void delAllPlaying() {
		playingRepository.deleteAll();
	}
	public void delPlaying(List<Playing> playingList) {
		playingRepository.deleteAll(playingList);
	}
	
	public void savePlaying(Playing newPlaying) {
		playingRepository.save(newPlaying);
	}
	public void saveAllPlaying(List<Playing> playingList) {
		playingRepository.saveAll(playingList);
	}
	public void saveAllAndFlush(List<Playing> playingList) {
		playingRepository.saveAllAndFlush(playingList);
	}
	
	public void flush() {
		playingRepository.flush();
	}
}
