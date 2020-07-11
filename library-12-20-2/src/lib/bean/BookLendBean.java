package lib.bean;

import java.io.Serializable;
import java.sql.Date;

public class BookLendBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int recordId;
	private int memberId;
	private int bookId;
	private Date lendDate;
	private Date returnDueDate;
	private Date returnDate;
	private String remarks;
	
	
	
	public BookLendBean(int recordId, int memberId, int bookId, Date lendDate, Date returnDueDate, Date returnDate,
			String remarks) {
		super();
		this.recordId = recordId;
		this.memberId = memberId;
		this.bookId = bookId;
		this.lendDate = lendDate;
		this.returnDueDate = returnDueDate;
		this.returnDate = returnDate;
		this.remarks = remarks;
	}
	
	
	
	public BookLendBean() {
		super();
	}



	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public Date getReturnDueDate() {
		return returnDueDate;
	}
	public void setReturnDueDate(Date returnDueDate) {
		this.returnDueDate = returnDueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	@Override
	public String toString() {
		return "BookLendBean [recordId=" + recordId + ", memberId=" + memberId + ", bookId=" + bookId + ", lendDate="
				+ lendDate + ", returnDueDate=" + returnDueDate + ", returnDate=" + returnDate + ", remarks=" + remarks
				+ "]" + "\n";
	}
	
}

