/**
 * 
 */
package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 曲を表現するクラス
 * @author Nobutoshi Ozawa
 *
 */
@Entity
@Table(name = "tune")
public class Tune {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "title")
	private String title;
	@ManyToOne(fetch = FetchType.EAGER)
	private Musician composer;
	
	public Tune() {
		composer = new Musician();
	}

	/**
	 * 曲のタイトルを返す
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 曲のタイトルを設定する
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 曲の作曲者を返す
	 * @return composer
	 */
	public Musician getComposer() {
		return composer;
	}

	/**
	 * 曲の作曲者を設定する
	 * @param composer
	 */
	public void setComposer(Musician composer) {
		this.composer =composer;
	}

	/**
	 * IDを返す
	 * @return ID
	 */
	public Long getId() {
		return id;
	}
}
