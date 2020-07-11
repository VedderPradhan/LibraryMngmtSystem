package lib.bean;

import java.io.Serializable;
import java.sql.Date;

public class BookBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String isbn;
	Date arrivalDate;
	Date disposalDate;
	String remarks;
	private String name;
	private int categoryCode;
	private String categoryName;
	private String author;
	private String publisher;
	private Date publishDate;
	private String coverURL;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getDisposalDate() {
		return disposalDate;
	}
	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getCoverURL() {
		return coverURL;
	}
	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BookBean(int id, String isbn, Date arrivalDate, Date disposalDate, String remarks, String name,
			int categoryCode, String categoryName, String author, String publisher, Date publishDate, String coverURL) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.arrivalDate = arrivalDate;
		this.disposalDate = disposalDate;
		this.remarks = remarks;
		this.name = name;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.coverURL = coverURL;
	}
	public BookBean() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public BookBean(int id, String isbn, Date arrivalDate, Date disposalDate, String remarks) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.arrivalDate = arrivalDate;
		this.disposalDate = disposalDate;
		this.remarks = remarks;
	}
	public void setBookMaster(String isbn, String name, int categoryCode, String categoryName, String author, String publisher,
			Date publishDate, String coverURL) {
		this.isbn = isbn;
		this.name = name;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.coverURL = coverURL;
	}
	@Override
	public String toString() {
		return "BookBean [id=" + id + ", isbn=" + isbn + ", arrivalDate=" + arrivalDate + ", disposalDate="
				+ disposalDate + ", remarks=" + remarks + ", name=" + name + ", categoryCode=" + categoryCode
				+ ", categoryName=" + categoryName + ", author=" + author + ", publisher=" + publisher
				+ ", publishDate=" + publishDate + ", coverURL=" + coverURL + "]";
	}
	


}
	

