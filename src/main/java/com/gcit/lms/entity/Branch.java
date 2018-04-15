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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author dmytro
 *
 */

@Entity
@Table(name="tbl_library_branch")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="branchId", scope=Branch.class)
public class Branch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3885644549212691543L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	@Column(name="branchId", unique = true, nullable = false)
	private Integer branchId;
	
	@Column(name="branchName", nullable = false)
	private String branchName;
	
	@Column(name="branchAddress", nullable = false)
	private String branchAddress;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tbl_book_copies", catalog="library", joinColumns = {@JoinColumn(name="branchId", nullable=false)}, 
	inverseJoinColumns={@JoinColumn(name="bookId")})
	private List <Book> books;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"books", "branches"})
	@JoinTable(name="tbl_book_loans", catalog="library", joinColumns = {@JoinColumn(name="branchId", nullable=false)}, 
	inverseJoinColumns={@JoinColumn(name="cardNo")})
	private List <Borrower> borrowers;
	
	
	//private List <BookCopies> bookCopies;

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(List<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchAddress == null) ? 0 : branchAddress.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((branchName == null) ? 0 : branchName.hashCode());
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
		Branch other = (Branch) obj;
		if (branchAddress == null) {
			if (other.branchAddress != null)
				return false;
		} else if (!branchAddress.equals(other.branchAddress))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (branchName == null) {
			if (other.branchName != null)
				return false;
		} else if (!branchName.equals(other.branchName))
			return false;
		return true;
	}
	
	
	

}
