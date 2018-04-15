/**
 * 
 */
package com.gcit.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author babyc
 *
 */
@Entity
@Table(name="tbl_book_genres")
public class BookGenres implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1048310767642738159L;

	
	@Column(name="genre_id")
	private Integer genre_id;
	
	@Id
	@Column(name="bookId")
	private Integer bookId;

	/**
	 * @return the genre_id
	 */
	public Integer getGenre_id() {
		return genre_id;
	}

	/**
	 * @param genre_id the genre_id to set
	 */
	public void setGenre_id(Integer genre_id) {
		this.genre_id = genre_id;
	}

	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	
	
}
