package lib.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.bean.BookBean;
import lib.bean.BookLendBean;
import lib.manager.BookManager;
/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		
		try {
			//パラメータget
			String action = request.getParameter("action");
			System.out.println("value of action:" + action);
			
			if (action.equals("memberBookSearch")) {
				int memberId =  Integer.parseInt(request.getParameter("id")) ;
				BookManager bManager = new BookManager();
				
				List<BookLendBean> list = bManager.getBookInfo(memberId);
				System.out.println("Information received from 「getBookInfo」" + list);
				
				boolean lendPossible = bManager.isLendable(memberId);//貸出可能かどうか確認
				System.out.println("Can lend: " + lendPossible);
				//boolean lendPossible = false;
				request.setAttribute("lendAble", lendPossible);//可能かどうかの情報をJSPに送る
				
				session.setAttribute("lentBooks", list);
				session.setAttribute("memberIdSession", memberId);
				
				gotoPage(request, response, "/memberBookInfo.jsp");
			}
			
			else if (action.equals("memberBookSearch2")) {
				int memberId = (int)session.getAttribute("withdrawMemberId");
				//int memberId =  Integer.parseInt(request.getParameter("id")) ;
				BookManager bManager = new BookManager();
				
				List<BookLendBean> list = bManager.getBookInfo(memberId);
				//System.out.println("Information received from 「getBookInfo」" + list);
				
				boolean lendPossible = bManager.isLendable(memberId);	//貸出可能かどうか確認		
				//boolean lendPossible = false;
				request.setAttribute("lendAble", lendPossible);//可能かどうかの情報をJSPに送る
				
				session.setAttribute("lentBooks", list);
				session.setAttribute("memberIdSession", memberId);
				
				gotoPage(request, response, "/memberBookInfo.jsp");
			}
			
			else if (action.equals("lendBook")) {
				session.removeAttribute("lentBooks");
				int memberId = (int)session.getAttribute("memberIdSession");
				System.out.println("(message from lenBook BLOCK)memberId = " + memberId);
				BookManager bManager = new BookManager();
				
				int bookId =  Integer.parseInt(request.getParameter("bookId"));
				
				BookManager bookManager = new BookManager();
				List<BookLendBean> list = bookManager.setLendBook(memberId, bookId);
				
				boolean lendPossible = bManager.isLendable(memberId);	//貸出可能かどうか確認	
				//boolean lendPossible = false;
				request.setAttribute("lendAble", lendPossible);//可能かどうかの情報をJSPに送る
				
				session.setAttribute("lentBooks", list);
				gotoPage(request, response, "/memberBookInfo.jsp");
			}
			
			else if (action.equals("returnBook")) {
				session.removeAttribute("lentBooks");
				BookManager bManager = new BookManager();
				
				int bookId = Integer.parseInt(request.getParameter("bookId"));
				int memberId = Integer.parseInt(request.getParameter("memberId"));
				
				BookManager bookManager = new BookManager();
				List<BookLendBean> list = bookManager.returnBook(memberId, bookId);
				
				boolean lendPossible = bManager.isLendable(memberId);	//貸出可能かどうか確認	
				//boolean lendPossible = false;
				request.setAttribute("lendAble", lendPossible);//可能かどうかの情報をJSPに送る
				
				session.setAttribute("lentBooks", list);
				gotoPage(request, response, "/memberBookInfo.jsp");
			}
			
			else if(action.equals("registBook")) {
				System.out.println("registBook Start");
				BookBean bean = new BookBean();
				System.out.println("registBook Start2");
				//int id = 2;
				bean.setIsbn(request.getParameter("isbn"));
				bean.setName(request.getParameter("bookName"));
				bean.setCategoryCode(Integer.parseInt(request.getParameter("genreCode")));
				bean.setAuthor(request.getParameter("writer"));
				bean.setPublisher(request.getParameter("publisher"));				
				bean.setPublishDate(Date.valueOf(request.getParameter("publishedDate")));
				
				//BookBean bean = new BookBean(id, isbn, name, catCode, authorNameString, publisherString, date); 
				//bean.setCoverU(request.getParameter("author"));
				System.out.println("registBook Start3");
				session.setAttribute("book", bean);
				gotoPage(request, response, "/bookRegistrationConfirm.jsp");
			}	 
			
			else if(action.contentEquals("registConfirmBook")){
				BookBean bean = (BookBean)session.getAttribute("book");
				
				BookManager bookMngr = new BookManager();
				
				BookBean bBean = bookMngr.addBook(bean.getIsbn(), bean.getName(), 
														bean.getCategoryCode(), bean.getAuthor(),
														bean.getPublisher(), bean.getPublishDate());
				
				session.removeAttribute("book");
				request.setAttribute("bookRegistrationInfo", bBean);
				gotoPage(request, response, "/bookRegistrationInfoDisplay.jsp");					
			}
			
			else if(action.contentEquals("searchBookDiscardOrLost")){
				int bookId = Integer.parseInt(request.getParameter("bookId"));
				
				BookBean bean = new BookBean();
				
				BookManager bookMngr = new BookManager();
				
				bean = bookMngr.searchBookId(bookId);
				
				//session.removeAttribute("book");
				session.setAttribute("idOfBook", bookId);
				request.setAttribute("discardBookInfo", bean);
				gotoPage(request, response, "bookDiscardOrLost.jsp");					
			}
			
			else if(action.contentEquals("registBookIdDiscardOrLost")){
				int bookId = (int)session.getAttribute("idOfBook");
				String remarks = request.getParameter("remarks");
				
				BookBean bean = new BookBean();
				
				BookManager bookMngr = new BookManager();
				
				bean = bookMngr.disposalBook(remarks, bookId);
				
				//session.removeAttribute("book");
				request.setAttribute("discardBookInfo", bean);
				gotoPage(request, response, "bookDiscardOrLost.jsp");					
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void gotoPage(HttpServletRequest request, 
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);		
	}	

}
