/**
 * 
 */
package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nobu_ozawa
 *
 */
/**
 * ミュージシャンを軽いするクラス
 * @author Nobutoshi Ozawa
 *
 */
@Entity
@Table(name = "musician")
public class Musician {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name")
	private String name;

	/**
	 * ミュージシャンの氏名を返す
	 * @return 氏名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ミュージシャンの氏名を設定する
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * IDを返す
	 * @return ID
	 */
	public Long getId() {
		return id;
	}
}
