package lib.manager;

import lib.DAO.MemberDAO;

import java.sql.Date;

import lib.DAO.DAOException;
import lib.bean.MemberBean;

public class MemberManager {
	public MemberManager() {}
	
	public MemberBean getMemberInfo(int memberId) throws DAOException{
		MemberDAO mDAO = new MemberDAO();
		
		MemberBean mBean = new MemberBean();
		
		mBean = mDAO.findMemberById(memberId);
		
		return mBean;
	}
	
	public MemberBean memberRegister(String name, String address, String tel, String email,
												Date birthday)  throws DAOException{
		
		MemberDAO mDAO = new MemberDAO();
		
		MemberBean mBean = new MemberBean();
		
		mBean = mDAO.insertMember(name, address, tel, email, birthday);
		
		return mBean;
		
	}
	
	public MemberBean memberWithdraw(int memberID) throws DAOException{
		  
		  MemberDAO mDAO = new MemberDAO();

		  MemberBean mBean = new MemberBean();
		  
		  mBean = mDAO.addWithdrawDate(memberID);
		  
		  return mBean;
	}
	
	public MemberBean changeName(int memberID, String name) throws DAOException{
		  MemberDAO mDAO = new MemberDAO();
		  MemberBean mBean = new MemberBean();		  
		  mBean = mDAO.updateName(memberID, name); 
		  
		  return mBean;
	}
	

//	public MemberBean changeAddress(int memberID, String address) throws DAOException{
//		  MemberDAO mDAO = new MemberDAO();
//		  MemberBean mBean = new MemberBean();		  
//		  mBean = mDAO.updateAddress(memberID, address); 
//		  
//		  return mBean;
//	}
//	public MemberBean changeTel(int memberID, String tel) throws DAOException{
//		  MemberDAO mDAO = new MemberDAO();
//		  MemberBean mBean = new MemberBean();		  
//		  mBean = mDAO.updateTel(memberID, tel); 
//		  
//		  return mBean;
//	}
//	
//	public MemberBean changeEmail(int memberID, String email) throws DAOException{
//		  MemberDAO mDAO = new MemberDAO();
//		  MemberBean mBean = new MemberBean();		  
//		  mBean = mDAO.updateEmail(memberID, email); 
//		  
//		  return mBean;
//	}
//	
//	public MemberBean changeBirthday(int memberID, Date birthday) throws DAOException{
//		  MemberDAO mDAO = new MemberDAO();
//		  MemberBean mBean = new MemberBean();		  
//		  mBean = mDAO.updateBirthday(memberID, birthday); 
//		  
//		  return mBean;
//	}
	
	public static void main(String[] args) throws DAOException{
		MemberManager mManager = new MemberManager();		
		MemberBean testBean = new MemberBean();		
		testBean = mManager.changeName(8, "James");		
		System.out.println(testBean);		
	}
}
