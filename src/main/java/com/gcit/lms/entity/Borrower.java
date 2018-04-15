/**
 * 
 */
package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author dmytro
 *
 */
@Entity
@Table(name="tbl_borrower")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="cardNo", scope=Borrower.class)
public class Borrower implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1946288915453523125L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cardNo", unique = true, nullable = false)
	private Integer cardNo;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="address", nullable = false)
	private String address;
	
	@Column(name="phone", nullable = false)
	private String phone;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tbl_book_loans", catalog="library", joinColumns = {@JoinColumn(name="cardNo", nullable=false)}, 
	inverseJoinColumns={@JoinColumn(name="bookId")})
	private List<Book> books;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tbl_book_loans", catalog="library", joinColumns = {@JoinColumn(name="cardNo", nullable=false)}, 
	inverseJoinColumns={@JoinColumn(name="branchId")})
	private List<Branch> branches;
	
	
	public Integer getCardNo() {
		return cardNo;
	}
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public List<Branch> getBranches() {
		return branches;
	}
	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	
	
}
