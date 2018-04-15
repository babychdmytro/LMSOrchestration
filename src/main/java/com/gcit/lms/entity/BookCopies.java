/**
 * 
 */
package com.gcit.lms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author dmytro
 *
 */

@Entity
@Table(name="tbl_book_copies")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="bookCopiesId", scope=BookCopies.class)
public class BookCopies implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5715355931751063471L;
	
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="bookCopiesId", nullable = false)
//	private Integer bookCopiesId;
	
	@Id
	@Column(name="bookId", nullable = false)
	private Integer bookId;
	
	@Column(name="branchId", nullable = false)
	private Integer branchId;
	
	@Column(name="noOfCopies")
	private Integer noOfCopies;

	
	
//	public Integer getBookCopiesId() {
//		return bookCopiesId;
//	}
//
//	public void setBookCopiesId(Integer bookCopiesId) {
//		this.bookCopiesId = bookCopiesId;
//	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noOfCopies;
		result = prime * result + bookId;
		result = prime * result + branchId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookCopies other = (BookCopies) obj;
		if (noOfCopies != other.noOfCopies)
			return false;
		if (bookId != other.bookId)
			return false;
		if (branchId != other.branchId)
			return false;
		return true;
	}

}
