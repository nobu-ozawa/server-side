package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 曲の演奏に参加したミュージシャンと参加の際のパートを保持する
 * @author Nobutoshi Ozawa
 *
 */
@Entity
@Table(name = "playing")
public class Playing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER)
	private Musician player;
	private Part part;
	
	public Playing() {}

	/**
	 * 該当するミュージシャンを返す
	 * @return player
	 */
	public Musician getPlayer() {
		return player;
	}

	/**
	 * 該当のミュージシャンを設定する
	 * @param player
	 */
	public void setPlayer(Musician player) {
		this.player = player;
	}

	/**
	 * 担当したパートを返す
	 * @return part
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * 担当パートを設定する
	 * @param part
	 */
	public void setPart(Part part) {
		this.part = part;
	}

	/**
	 * IDを返す
	 * @return ID
	 */
	public Long getId() {
		return id;
	}
}
