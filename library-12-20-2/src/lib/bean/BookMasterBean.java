package lib.bean;

import java.io.Serializable;
import java.sql.Date;

public class BookMasterBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String title;
	private int categoryCode;
	private String categoryName;
	private String author;
	private String publisher;
	private Date publishDate;
	private String coverURL;
	public BookMasterBean(String isbn, String title, int categoryCode, String author, String publisher, Date publishDate, String coverURL) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.categoryCode = categoryCode;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.coverURL = coverURL;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCoverURL() {
		return coverURL;
	}
	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}
	@Override
	public String toString() {
		return "BookMasterBean [isbn=" + isbn + ", title=" + title + ", categoryCode=" + categoryCode
				+ ", categoryName=" + categoryName + ", author=" + author + ", publisher=" + publisher
				+ ", publishDate=" + publishDate + ", coverURL=" + coverURL + "]";
	}

}

