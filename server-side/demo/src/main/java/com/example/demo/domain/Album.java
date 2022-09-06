package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * アルバムを管理するクラス
 * @author Nobutoshi Ozawa
 *
 */
@Entity
@Table(name = "album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "title")
	private String title;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "album_id")
	private List<Recording> contents;
	@Column(name = "channel")
	private SalesChannel channel;
	
	/**
	 * アルバムのタイトルを返す
	 * @return アルバムのタイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * アルバムのタイトルを設定する
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * コンストラクター
	 */
	public Album() {
		contents = new ArrayList<Recording>();
	}

	/**
	 * アルバムの収録内容を返す
	 * @return 収録内容
	 */
	public List<Recording> getContents() {
		return contents;
	}

	/**
	 * アルバムの収録内容を追加する
	 * @param content
	 */
	public void addContents(Recording content) {
		this.contents.add(content);
	}

	/**
	 * アルバムの販売チャネルを返す
	 * @return 販売チャネル
	 */
	public SalesChannel getChannel() {
		return channel;
	}

	/**
	 * 販売チャネルを設定する
	 * @param channel
	 */
	public void setChannel(SalesChannel channel) {
		this.channel = channel;
	}

	/**
	 * IDを返す
	 * @return ID
	 */
	public Long getId() {
		return id;
	}
}
