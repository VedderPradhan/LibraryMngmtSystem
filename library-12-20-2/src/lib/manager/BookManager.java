package lib.manager;

import lib.DAO.DAOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import lib.DAO.BookDAO;
import lib.bean.BookBean;
import lib.bean.BookLendBean;
//import sun.jvm.hotspot.oops.java_lang_Class;

public class BookManager {
	
	public BookManager() {}
	
	public List<BookLendBean> getBookInfo(int memberId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		
		List<BookLendBean> bBean = new ArrayList<>();
		
		bBean = bDAO.findLendRecordByMemberId(memberId);
		
		return bBean;
	}
	
	public List<BookLendBean> setLendBook(int memberId, int bookId) throws DAOException, ParseException{
		
		//---------------------------今日の日付を設定する----------------------------------
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateToday = new java.util.Date();
		//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		String dateTodayString = dateFormat.format(dateToday);
		//-----------------------------------------------------------------------------------
		
		BookDAO dao = new BookDAO(); 
		
		java.util.Date publishedDate = dao.getPublishedDate(bookId);		
		String publishedDateString = dateFormat.format(publishedDate);
		
		java.util.Date returnDueDate = new java.util.Date();
		
		//----------------------------日付の比較してreturnDueDateを設定する----------------------------------
		LocalDate d1 = LocalDate.parse(publishedDateString, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate d2 = LocalDate.parse(dateTodayString, DateTimeFormatter.ISO_LOCAL_DATE);
		Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
		long diffDays = diff.toDays();
		System.out.println("日付の差は:" + diffDays);		
		if (diffDays <= 90)
			returnDueDate = addDate(10);//新刊本は10日以内に返却
		
		else if (diffDays >90 ) {
			returnDueDate = addDate(15);//新刊本以外は15日以内に返却
		}
		//--------------------------------------------------------------------------------------------------------
		String returnDueDateString = dateFormat.format(returnDueDate);
		
		
		List<BookLendBean> list = dao.insertLendRecord(memberId, bookId, Date.valueOf(returnDueDateString));
		
		return list;
		
	}
	
	public List<BookLendBean> returnBook(int memberId, int bookId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		
		List<BookLendBean> bBean = new ArrayList<>();
		
		bBean = bDAO.updateReturnDate(memberId, bookId);
		
		return bBean;
	}
	
	public boolean isLendable(int memberId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		int count = bDAO.countLendRecord(memberId);
		System.out.println("(message from BookManger)借りている本count:" + count);
		//Date returnDueDate = bDAO.getReturnDueDate(memberId);
		//---------------------------今日の日付を設定する----------------------------------
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateToday = new java.util.Date();
		//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		//String dateTodayString = dateFormat.format(dateToday);
		//-----------------------------------------------------------------------------------
		//date1.before(date2)
		boolean isReturnDateCrossed = false;
		List<Date> listDate = bDAO.getReturnDueDate(memberId);
		//List<Date> listDate = new ArrayList<Date>();
		//listDate.add(Date.valueOf("2019-07-01"));
		//listDate.add(Date.valueOf("2019-12-30"));
		//listDate.add(Date.valueOf("2019-12-31"));
		for (Date date : listDate) {
			isReturnDateCrossed = date.before(dateToday);
			if(isReturnDateCrossed) {
				break;
			}else {
				continue;
			}
		}
		System.out.println("count : " + count);
		System.out.println("boolean : " + isReturnDateCrossed);
		if (count >= 5 || isReturnDateCrossed) {
			return false;
			
		}else {
			
			return true;
		}
		
	}	
	
	public BookBean addBook(String isbn) throws DAOException {
		BookDAO dao = new BookDAO();
		return dao.addBook(isbn);
	}
	public BookBean addBook(String isbn, String title, Integer categoryCode, String author, String publisher, Date publishDate) throws DAOException {
		BookDAO dao = new BookDAO();
		if (dao.findBookMasterByISBN(isbn) == null) {
			dao.addBookMaster(isbn, title, categoryCode, author, publisher, publishDate);
		}
		return addBook(isbn);
	}
	public java.util.Date addDate(int noOfDays) throws DAOException, ParseException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new java.util.Date()); // Now use today date.
		c.add(Calendar.DATE, noOfDays); // Adding 5 days
		String outputStringDate = dateFormat.format(c.getTime());		  
		java.util.Date outputDate =new SimpleDateFormat("yyyy-MM-dd").parse(outputStringDate);  
		return outputDate;
	}
	
	public BookBean searchBookId(int bookId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		 
		BookBean bBean = new BookBean();
		
		bBean = bDAO.findBookByID(bookId);
		
		return bBean;
	}
	
	public BookBean disposalBook(String remarks, int bookId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		 
		BookBean bBean = new BookBean();
		
		bBean = bDAO.addDisposalDate(remarks, bookId);
		
		return bBean;
	}
	
	public int getLendBooksCount(int memberId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		int count = bDAO.countLendRecord(memberId);
		System.out.println("count : " + count);
		//System.out.println("boolean : " + isWithdrawOk);
		return count;		
	}
	
	public List<BookLendBean> getAllBookInfo(int memberId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		
		List<BookLendBean> bBean = new ArrayList<>();
		
		bBean = bDAO.findAllLendRecordByMemberId(memberId);
		
		return bBean;
	}
	
		
		public static void main(String[] args) throws DAOException, ParseException{
		
//		BookManager bMngr = new BookManager();
//		boolean lendPossible = bMngr.isLendable(2);
//		if(lendPossible) {
//			System.out.println("貸出可能");
//		}else {
//			System.out.println("貸出不可");
//		}
//				System.out.println();
//		System.out.println("return Bookのテスト");
//		System.out.println(lendPossible);
		
		BookManager bMngr = new BookManager();
		List<BookLendBean> bBean = bMngr.getAllBookInfo(2);
		
				System.out.println();
		System.out.println("get bookのテスト");
		System.out.println(bBean);
	}
	
}
