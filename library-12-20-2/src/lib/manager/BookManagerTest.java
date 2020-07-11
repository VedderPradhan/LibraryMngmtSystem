package lib.manager;

import lib.DAO.DAOException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lib.DAO.BookDAO;
import lib.bean.BookLendBean;
//import sun.jvm.hotspot.oops.java_lang_Class;

public class BookManagerTest {
	
	public BookManagerTest() {}
	
	public List<BookLendBean> getBookInfo(int memberId) throws DAOException{
		BookDAO bDAO = new BookDAO();
		
		List<BookLendBean> bBean = new ArrayList<>();
		
		bBean = bDAO.findLendRecordByMemberId(memberId);
		
		return bBean;
	}
	
	public List<BookLendBean> lendBook(int memberId, int bookId) throws DAOException{
		
		Date returnDueDate = 
		BookDAO dao = new BookDAO();
		
		
	}
	
	public static void main(String[] args) throws DAOException{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date date = new java.util.Date();
//		//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
//		String dateString = dateFormat.format(date);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new java.util.Date()); // Now use today date.
		c.add(Calendar.DATE, 40); // Adding 5 days
		String output = dateFormat.format(c.getTime());
		System.out.println(output);
		
//		LocalDate d1 = LocalDate.parse("2019-11-28", DateTimeFormatter.ISO_LOCAL_DATE);
//		LocalDate d2 = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
//		Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
//		long diffDays = diff.toDays();
//		System.out.println("日付の差は:" + diffDays);
//		
//		if (diffDays <= 90)
//			System.out.println("You can borrow the book for 10 days only. :(");
//		
//		else if (diffDays >90 ) {
//			System.out.println("You can borrow the book for 15 days.");
//		}

		//		BookManager bMngr = new BookManager();
//		List<BookLendBean> beans = bMngr.getBookInfo(2);
//		System.out.println();
//		System.out.println("find methodのテスト");
//		System.out.println(beans);
	}
	
}