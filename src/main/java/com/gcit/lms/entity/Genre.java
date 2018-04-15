package com.gcit.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="tbl_genre")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="genre_id", scope=Genre.class)
public class Genre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8565076352736918915L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="genre_id", nullable = false)
	private Integer genre_id;
	
	@Column(name="genre_name", nullable = false)
	private String genre_name;

	
	public Integer getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
	}

	/**
	 * @return the genre_name
	 */
	public String getGenre_name() {
		return genre_name;
	}

	/**
	 * @param genre_name the genre_name to set
	 */
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}



	
	
	
	
}
