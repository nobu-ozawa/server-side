package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Musician;
import com.example.demo.domain.MusicianRepository;

/**
 * ミュージシャンのアクセスを提供するクラス
 * @author Nobutoshi Ozawa
 *
 */
@Service
@Transactional
public class MusicianService {
	@Autowired
	private MusicianRepository musicianRepository;
	
	/**
	 * 指定されたIDに対応するミュージシャンを返す
	 * @param id
	 * @return 該当ミュージシャン
	 */
	public Optional<Musician> getMusicianById(Long id) {
		return musicianRepository.findById(id);
	}
	
	/**
	 * 全てのミュージシャンを返す
	 * @return　該当ミュージシャンのリスト
	 */
	public List<Musician> getAllMusician() {
		return musicianRepository.findAll();
	}
	
	/**
	 * 指定された名前により対応するミュージシャンを返す
	 * @param name
	 * @return　該当のミュージシャンのリスト
	 */
	public List<Musician> getMusicianByName(String name) {
		return musicianRepository.findAll().stream()
			.filter(t -> t.getName().equals(name))
			.collect(Collectors.toList());
	}
	
	/**
	 * 指定されたIDのミュージシャンを削除する
	 * @param id
	 */
	public void delMusicianById(Long id) {
		musicianRepository.deleteById(id);
	}
	/**
	 * 全てのミュージシャンを削除する
	 */
	public void delAllMusician() {
		musicianRepository.deleteAll();
	}
	/**
	 * 指定されたミュージシャンを削除する
	 * @param musicianList
	 */
	public void delMusicians(List<Musician> musicianList) {
		musicianRepository.deleteAll(musicianList);
	}
	/**
	 * 指定されたミュージシャンを登録・更新する
	 * @param newMusician
	 */
	public void saveMusician(Musician newMusician) {
		musicianRepository.save(newMusician);
	}
	/**
	 * 指定されたミュージシャンを登録する
	 * @param musicianList
	 */
	public void saveAllMusician(List<Musician> musicianList) {
		musicianRepository.saveAll(musicianList);
	}
	/**
	 * 指定されたミュージシャンを登録・更新してフラッシュする
	 * @param musicianList
	 */
	public void saveAllAndFlush(List<Musician> musicianList) {
		musicianRepository.saveAllAndFlush(musicianList);
	}
	
	/**
	 * フラッシュする
	 */
	public void flush() {
		musicianRepository.flush();
	}
}
