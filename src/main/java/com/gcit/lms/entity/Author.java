package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "tbl_author")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="authorId", scope=Author.class)
public class Author implements Serializable {

	
	//New changes
	private static final long serialVersionUID = 2323860177160839664L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorId", unique = true, nullable = false)
	private Integer authorId;

	@Column(name = "authorName", nullable = false)
	private String authorName;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"authors", "genres"})
	@JoinTable(name="tbl_book_authors", catalog="library", joinColumns = {@JoinColumn(name="authorId", nullable=false)}, 
	inverseJoinColumns={@JoinColumn(name="bookId")})
	private List<Book> books = new ArrayList<>();
	

	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
