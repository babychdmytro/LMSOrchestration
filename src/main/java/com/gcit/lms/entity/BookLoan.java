/**
 * 
 */
package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author babyc
 *
 */
@Entity
@Table(name="tbl_book_loans")
//@JsonIdentityInfo(generator=ObjectIdGenerators.None.class, scope=BookLoan.class)
public class BookLoan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8843193530527117359L;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="bookId")
	private Book book;

	@OneToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"books", "borrowers"})
	@JoinColumn(name="branchId")
	private Branch branch;
	
	@Column(name="cardNo", nullable = false)
	private Integer cardNo;
	
	@Id
	@Column(name="dateOut", nullable = false)
	private Date dateOut;
	
	@Column(name="dueDate")
	private Date dueDate;
	
	@Column(name="dateIn")
	private Date dateIn;
	


	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Integer getCardNo() {
		return cardNo;
	}
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
//	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
		
	
	
}
