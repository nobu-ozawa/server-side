/**
 * 
 */
package com.example.demo.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 録音を表すクラス
 * @author Nobutoshi Ozawa
 *
 */
@Entity
@Table(name = "recording")
public class Recording {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Tune tune;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "rec_id")
	private Set<Playing> playingList;
	
	public Recording() {
		playingList = new HashSet<Playing>();
	}

	/**
	 * 演奏された曲を返す
	 * @return tune
	 */
	public Tune getTune() {
		return tune;
	}

	/**
	 * 演奏された曲を設定する
	 * @param tune
	 */
	public void setTune(Tune tune) {
		this.tune = tune;
	}

	/**
	 * 録音を構成する演奏をリストで返す
	 * @return playingList
	 */
	public List<Playing> getPlayingList() {
		return new ArrayList<Playing>(playingList);
	}

	/**
	 * 録音を構成する演奏リストを設定する
	 * @param playingList
	 */
	public void setPlayingList(List<Playing> playingList) {
		this.playingList = new HashSet<Playing>(playingList);
	}
	
	/**
	 * 構成する演奏リストから氏名で指定されたミュージシャンの物をリストで返す
	 * @param name
	 * @return 対象の演奏のリスト
	 */
	public List<Playing> getPlayingListByPlayerName(String name) {
		int s = playingList.size();
		System.out.println(s);
		return playingList.stream()
				.filter(p -> p.getPlayer().getName().equals(name))
				.collect(Collectors.toList());
	}
	
	/**
	 * 演奏を追加する
	 * @param playing
	 */
	public void addPlaying(Playing playing) {
		playingList.add(playing);
	}

	/**
	 * ID を返す
	 * @return ID
	 */
	public Long getId() {
		return id;
	}
}
