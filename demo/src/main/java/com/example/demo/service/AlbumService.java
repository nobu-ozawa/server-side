package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Album;
import com.example.demo.domain.AlbumRepository;

/**
 * アルバムのCRUDの操作を提供するクラス
 * @author Nobutoshi Ozawa
 *
 */
@Service
@Transactional
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	/**
	 * IDを条件にしてアルバムを検索し結果をOptionalで返す
	 * @param id
	 * @return 対象のアルバムを返すOptional
	 */
	public Optional<Album> getAlbumById(Long id) {
		return albumRepository.findById(id);
	}
	
	/**
	 * 全てのアルバムを返す
	 * @return アルバムのリスト
	 */
	public List<Album> getAllAlbum() {
		return albumRepository.findAll();
	}
	
	/**
	 * タイトルを条件にアルバムを検索し結果をリストで返す
	 * @param title
	 * @return 該当のアルバムをリスト
	 */
	public List<Album> getAlbumByTitle(String title) {
		return albumRepository.findAll().stream()
			.filter(t -> t.getTitle().equals(title))
			.collect(Collectors.toList());
	}
	
	/**
	 * IDを条件にアルバムの削除を行う
	 * @param id
	 */
	public void delAlbumById(Long id) {
		albumRepository.deleteById(id);
	}
	/**
	 * 全アルバムの削除を行う
	 */
	public void delAllAlbum() {
		albumRepository.deleteAll();
	}
	/**
	 * リストに含まれるアルバムを削除する
	 * @param albumList
	 */
	public void delAlbums(List<Album> albumList) {
		albumRepository.deleteAll(albumList);
	}
	
	/**
	 * パラメータで引き渡されたアルバムを保存する
	 * @param newAlbum
	 */
	public void saveAlbum(Album newAlbum) {
		albumRepository.save(newAlbum);
	}
	/**
	 * パラメータで引き渡されたリストに含まれるアルバムを保存する
	 * @param albumList
	 */
	public void saveAllAlbum(List<Album> albumList) {
		albumRepository.saveAll(albumList);
	}
	/**
	 * パラメータで引き渡されたリストに含まれるアルバムを保存しフラッシュする
	 * @param albumList
	 */
	public void saveAllAndFlush(List<Album> albumList) {
		albumRepository.saveAllAndFlush(albumList);
	}
	/**
	 * 全ての操作をフラッシュする
	 */
	public void flush() {
		albumRepository.flush();
	}
}
