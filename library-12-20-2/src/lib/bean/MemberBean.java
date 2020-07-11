package lib.bean;

import java.io.Serializable;
import java.sql.Date;

public class MemberBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String address;
	private String tel;
	private String email;
	private Date birthday;
	private Date joinedDate;
	private Date withdrawDate;
	
	public MemberBean(int id, String name, String address, String tel, String email, Date birthday, Date joinedDate,
			Date withdrawDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.joinedDate = joinedDate;
		this.withdrawDate = withdrawDate;
	}
	public MemberBean() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public Date getWithdrawDate() {
		return withdrawDate;
	}
	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", name=" + name + ", address=" + address + ", tel=" + tel + ", email=" + email
				+ ", birthday=" + birthday + ", joinedDate=" + joinedDate + ", withdrawDate=" + withdrawDate + "]";
	}
	
	
}
