package lib.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.bean.BookLendBean;
import lib.bean.MemberBean;
import lib.manager.BookManager;
import lib.manager.MemberManager;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
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
			
//			if (action.equals("confirm")) {
//				MemberBean bean = new MemberBean();
//				bean.setName(request.getParameter("name"));
//				bean.setAddress(request.getParameter("address"));
//				bean.setTel(request.getParameter("tel"));
//				bean.setEmail(request.getParameter("email"));
//				bean.setBirthday(Date.valueOf(request.getParameter("birthday")));
//				session.setAttribute("member", bean);
//				gotoPage(request, response, "/registrationConfirm.jsp");
//			}
			
			if (action.equals("confirm")) {
				System.out.println("Confirm start");
				MemberBean bean = new MemberBean();
				bean.setName(request.getParameter("name"));
				bean.setAddress(request.getParameter("address"));
				bean.setTel(request.getParameter("tel"));
				bean.setEmail(request.getParameter("email"));
				System.out.println("Confirm start2");
				String Year = (request.getParameter("birth1"));
				String Month = (request.getParameter("birth2"));
				String Day = (request.getParameter("birth3"));
				String birthday = Year + "-" + Month + "-" + Day;
				bean.setBirthday(Date.valueOf(birthday));
				session.setAttribute("member", bean);
				gotoPage(request, response, "/registrationConfirm.jsp");
				System.out.println("Confirm start3");
			}
			
			else if (action.equals("register")) {
				MemberBean bean = (MemberBean)session.getAttribute("member");
				System.out.println("registerブロックに入りました");
				if (bean == null) {
					request.setAttribute("message", "正しく操作してください");
					gotoPage(request, response, "/errInternal.jsp");
				}
				
				MemberManager mManager = new MemberManager();
				MemberBean mBean = new MemberBean();
				
				mBean = mManager.memberRegister(bean.getName(), bean.getAddress(), 
														bean.getTel(), bean.getEmail(),
														bean.getBirthday());
				
				session.removeAttribute("member");
				request.setAttribute("memberRegisteredInfo", mBean);
				gotoPage(request, response, "/registrationInfoDisplay.jsp");
			}
			
			else if (action.equals("allLentBooks")) {
				System.out.println("allLentBooksブロックに入りました");
				int memberId = (int)session.getAttribute("withdrawMemberId");
				BookManager bManager = new BookManager();
				List<BookLendBean> bBean = new ArrayList<>();
				bBean = bManager.getAllBookInfo(memberId);
				session.setAttribute("bookList", bBean);
				System.out.println("mBeanの中身：" + bBean);
				gotoPage(request, response, "/bookLentHistory.jsp");
			}
			
			else if (action.equals("memberSearch")) {
				System.out.println("searchブロックに入りました");
				int memberId =  Integer.parseInt(request.getParameter("id")) ;
				MemberManager mManager = new MemberManager();
				MemberBean mBean = new MemberBean();
				
				BookManager bManager = new BookManager();
				int bookLendCount = bManager.getLendBooksCount(memberId);
				mBean = mManager.getMemberInfo(memberId);
				session.setAttribute("numberOfBooksLent", bookLendCount);
				session.setAttribute("memberSearchInfo", mBean);
				session.setAttribute("withdrawMemberId", memberId);
				System.out.println("mBeanの中身：" + mBean);
				gotoPage(request, response, "/changeMemberInformation.jsp");
			}
			
			else if (action.equals("memberWithdraw")) {
				//session.getAttribute("memberSearchInfo");

				int memberId = (int)session.getAttribute("withdrawMemberId");
				System.out.println("withdrawブロックに入りました");
				//int memberId =  Integer.parseInt(request.getParameter("id")) ;
				MemberManager mManager = new MemberManager();
				MemberBean mBean = new MemberBean();
				BookManager bManager = new BookManager();
				
				int bookLendCount = bManager.getLendBooksCount(memberId);
				boolean isMemberWithdrawPossible;

				if (bookLendCount == 0){
						isMemberWithdrawPossible = true;
						mBean = mManager.memberWithdraw(memberId);
						session.removeAttribute("memberSearchInfo");
						session.setAttribute("memberSearchInfo", mBean);
				}
				else{
						isMemberWithdrawPossible = false;						
				}
				session.setAttribute("numberOfBooksLent", bookLendCount);
				request.setAttribute("isMemeberWithdrawAllowed", isMemberWithdrawPossible);

				//System.out.println("mBeanの中身：" + mBean);
				gotoPage(request, response, "/changeMemberInformation.jsp");
			}
			
//			else if (action.equals("memberWithdraw")) {
//				//session.getAttribute("memberSearchInfo");
//				session.removeAttribute("memberSearchInfo");
//				int memberId = (int)session.getAttribute("withdrawMemberId");
//				System.out.println("withdrawブロックに入りました");
//				//int memberId =  Integer.parseInt(request.getParameter("id")) ;
//				MemberManager mManager = new MemberManager();
//				MemberBean mBean = new MemberBean();
//				mBean = mManager.memberWithdraw(memberId);
//				session.setAttribute("memberSearchInfo", mBean);
//				//System.out.println("mBeanの中身：" + mBean);
//				gotoPage(request, response, "/changeMemberInformation.jsp");
//			}
			
			else if (action.equals("changeMemberName")) {
				session.removeAttribute("memberSearchInfo");
				int memberId = (int)session.getAttribute("withdrawMemberId");
				String name = request.getParameter("name");
				System.out.println("withdrawブロックに入りました");
				MemberManager mManager = new MemberManager();
				MemberBean mBean = new MemberBean();
				mBean = mManager.changeName(memberId, name);
				session.setAttribute("memberSearchInfo", mBean);
				gotoPage(request, response, "/changeMemberInformation.jsp");
			}
			
//			else if (action.equals("changeMemberAddress")) {
//				session.removeAttribute("memberSearchInfo");
//				int memberId = (int)session.getAttribute("withdrawMemberId");
//				String address = request.getParameter("address");
//				System.out.println("withdrawブロックに入りました");
//				MemberManager mManager = new MemberManager();
//				MemberBean mBean = new MemberBean();
//				mBean = mManager.changeAddress(memberId, address);
//				session.setAttribute("memberSearchInfo", mBean);
//				gotoPage(request, response, "/changeMemberInformation.jsp");
//			}
//			
//			else if (action.equals("changeMemberTel")) {
//				session.removeAttribute("memberSearchInfo");
//				int memberId = (int)session.getAttribute("withdrawMemberId");
//				String tel = request.getParameter("tel");
//				System.out.println("withdrawブロックに入りました");
//				MemberManager mManager = new MemberManager();
//				MemberBean mBean = new MemberBean();
//				mBean = mManager.changeTel(memberId, tel);
//				session.setAttribute("memberSearchInfo", mBean);
//				gotoPage(request, response, "/changeMemberInformation.jsp");
//			}
//			else if (action.equals("changeMemberEmail")) {
//				session.removeAttribute("memberSearchInfo");
//				int memberId = (int)session.getAttribute("withdrawMemberId");
//				String email = request.getParameter("email");
//				System.out.println("withdrawブロックに入りました");
//				MemberManager mManager = new MemberManager();
//				MemberBean mBean = new MemberBean();
//				mBean = mManager.changeEmail(memberId, email);
//				session.setAttribute("memberSearchInfo", mBean);
//				gotoPage(request, response, "/changeMemberInformation.jsp");
//			}
//			
//			else if (action.equals("changeMemberBirthday")) {
//				session.removeAttribute("memberSearchInfo");
//				int memberId = (int)session.getAttribute("withdrawMemberId");
//				MemberManager mManager = new MemberManager();
//				MemberBean mBean = new MemberBean();
//				String Year = (request.getParameter("birth1"));
//				String Month = (request.getParameter("birth2"));
//				String Day = (request.getParameter("birth3"));
//				String birthday = Year + "-" + Month + "-" + Day;
//				Date birthdayDate = Date.valueOf(birthday);
//				//Date birthday = Date.valueOf(request.getParameter("birthday"));
//				System.out.println("withdrawブロックに入りました");
//				mBean = mManager.changeBirthday(memberId, birthdayDate);
//				session.setAttribute("memberSearchInfo", mBean);
//				gotoPage(request, response, "/changeMemberInformation.jsp");
//			}
//			
//			
			
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
