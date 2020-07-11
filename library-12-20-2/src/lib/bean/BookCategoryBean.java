package lib.bean;

import java.io.Serializable;

public class BookCategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String name;
	
	public BookCategoryBean(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BookCategoryBean [code=" + code + ", name=" + name + "]";
	}
	
}
