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
@Table(name="tbl_book_authors")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="bookId", scope=BookAuthors.class)
public class BookAuthors implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1577466214169442312L;

	@Id
	@Column(name="bookId", nullable = false)
	private Integer bookId;
	
	@Column(name="authorId", nullable = false)
	private Integer authorId;

	

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	
	
	
}
