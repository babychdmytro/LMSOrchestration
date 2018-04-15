package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="tbl_book")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="bookId", scope=Book.class)
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1927425564546125927L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	@Column(name="bookId", unique=true)
	private Integer bookId;
	
	@Column(name="title", nullable=false)
	private String title;

//	@Column(name="pubId", nullable=false)
//	private Integer pubId;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="pubId")
	private Publisher publisher;
	

	@ManyToMany(fetch=FetchType.LAZY)
	@JsonIgnoreProperties("books")
	@JoinTable(name="tbl_book_authors", catalog="library", joinColumns = {@JoinColumn(name="bookId", nullable=false)}, 
	inverseJoinColumns={@JoinColumn(name="authorId")})
	private List<Author> authors = new ArrayList<>();
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tbl_book_genres", catalog="library", joinColumns = {@JoinColumn(name="bookId", nullable=false)}, 
	inverseJoinColumns={@JoinColumn(name="genre_id")})
	private List<Genre> genres = new ArrayList<>();
	
	
	
	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
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
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the authors
	 */
	
	
	
	public List<Author> getAuthors() {
		return authors;
	}
	/**
	 * @return the pubId
	 */
//	public Integer getPubId() {
//		return pubId;
//	}
//	/**
//	 * @param pubId the pubId to set
//	 */
//	public void setPubId(Integer pubId) {
//		this.pubId = pubId;
//	}
	/**
	 * @param authors the authors to set
	 */

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	/**
	 * @return the publisher
	 */
	
	
	
	
}
