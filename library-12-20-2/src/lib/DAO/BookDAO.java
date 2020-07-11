package lib.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lib.bean.*;

public class BookDAO {
	private Connection con;

	public BookDAO() throws DAOException {
		getConnection();
	}

	public List<BookLendBean> findLendRecordByMemberId(int memberId) throws DAOException{
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL文の作成
			System.out.println("Creating Statement(findLendRecords)....");
			String sql = "SELECT * FROM lend_records WHERE member_id=? AND return_date IS NULL;";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, memberId);
			System.out.println("value given to st...");
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			System.out.println("value given to rs...");
			List<BookLendBean> list = new ArrayList<>();
			
			
			while (rs.next()) {
				int recordId = rs.getInt("record_id");
				int mId = rs.getInt("member_id");
				int bookId = rs.getInt("book_id");
				Date lendDate = rs.getDate("lend_date");
				Date returnDueDate = rs.getDate("due_date");
				Date returnDate = rs.getDate("return_date");
				String remarks = rs.getString("remarks");
				
				BookLendBean bean = new BookLendBean(recordId, mId, bookId, lendDate, returnDueDate, returnDate, remarks);
				
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
	

	public int countLendRecord(int memberId) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL文の作成
			System.out.println("Creating Statement(countLendRecord)....");
			String sql = "SELECT member_id, count(*) FROM lend_records WHERE member_id=? AND return_date IS NULL GROUP BY member_id;";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, memberId);
			System.out.println("value given to st...");
			// SQLの実行
			rs = st.executeQuery();
			//System.out.println(rows);
			// 結果の取得および表示
			System.out.println("value given to rs...");
			
			int count = 0;
			if(rs.next()) {
				count = rs.getInt("count");		
			}
			return count;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}	
	}
	
	public List<BookLendBean> insertLendRecord(int memberId, int bookId,  
			Date returnDueDate) throws DAOException{
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL文の作成
			System.out.println("Creating Statement(findLendRecords)....");
			String sql = "INSERT INTO lend_records(member_id, book_id, lend_date, due_date, return_date, remarks) VALUES(?, ?, CURRENT_DATE, ?, NULL, NULL);";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, memberId);
			st.setInt(2, bookId);
			st.setDate(3, returnDueDate);
			System.out.println("value given to st...");
			// SQLの実行
			st.executeUpdate();
			// 結果の取得および表示
			System.out.println("value given to rs...");
			List<BookLendBean> list = new ArrayList<>();
				list = findLendRecordByMemberId(memberId);
				
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}	
	}
	
	public List<BookLendBean> updateReturnDate(int memberId, int bookId) throws DAOException{
		
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL文の作成
			System.out.println("Creating Statement(returnBook)....");
			String sql = "UPDATE lend_records SET return_date = CURRENT_DATE WHERE book_id = ?;";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, bookId);
			System.out.println("value given to st...");
			// SQLの実行
			st.executeUpdate();
			// 結果の取得および表示
			System.out.println("value given to rs...");
			List<BookLendBean> list = new ArrayList<>();
				list = findLendRecordByMemberId(memberId);
				
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}	
		
	}
	
	public Date getPublishedDate(int bookId) throws DAOException{
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL文の作成
			System.out.println("Creating Statement(findLendRecords)....");
			String sql = "SELECT b.id, bm.publish_date FROM book b JOIN book_master bm ON b.isbn = bm.isbn WHERE id=?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, bookId);
			System.out.println("value given to st...");
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			System.out.println("value given to rs...");				
			// カテゴリ一覧をListとして返す
			//Date publishedDate = new Date(1000000L);
			
			if(rs.next()) {
				Date publishedDate = rs.getDate("publish_date");
				return publishedDate;
			}
			else {
				
				return null;
			}	
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
	public List<Date> getReturnDueDate(int memberId) throws DAOException{
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL文の作成
			System.out.println("Creating Statement(get return due date)....");
			String sql = "SELECT member_id, due_date FROM lend_records WHERE member_id=? AND return_date IS NULL;";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, memberId);
			System.out.println("value given to st...");
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			System.out.println("value given to rs...");
			List<Date> listDate = new ArrayList<Date>();
			while(rs.next()) {
				Date returnDueDate = rs.getDate("due_date");
				listDate.add(returnDueDate);
			
			}
		return listDate;
			// カテゴリ一覧をListとして返す
		} 
	
			catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		}
	
	public List<BookLendBean> findAllLendRecordByMemberId(int memberId) throws DAOException{
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL文の作成
			System.out.println("Creating Statement(findLendRecords)....");
			String sql = "SELECT * FROM lend_records WHERE member_id=?;";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, memberId);
			System.out.println("value given to st...");
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			System.out.println("value given to rs...");
			List<BookLendBean> list = new ArrayList<>();
			
			
			while (rs.next()) {
				int recordId = rs.getInt("record_id");
				int mId = rs.getInt("member_id");
				int bookId = rs.getInt("book_id");
				Date lendDate = rs.getDate("lend_date");
				Date returnDueDate = rs.getDate("due_date");
				Date returnDate = rs.getDate("return_date");
				String remarks = rs.getString("remarks");
				
				BookLendBean bean = new BookLendBean(recordId, mId, bookId, lendDate, returnDueDate, returnDate, remarks);
				
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
	
		
//	public List<BookLendBean> insertLendRecord(int memberId, int bookId, Date lendDate, Date returnDueDate, Date returnDate, String remarks)
//			throws DAOException{
//		if (con == null)
//			getConnection();
//
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		
//		try {
//			// SQL文の作成
//			/*************************/
//						String sql = "SELECT * FROM book_ ORDER BY memberId";
//			/*************************/
//			// PreparedStatementオブジェクトの取得
//			st = con.prepareStatement(sql);
//			// SQLの実行
//			rs = st.executeQuery();
//			// 結果の取得および表示
//			List<BookLendBean> list = new ArrayList<>("int memberId, int bookId, Date lendDate, Date returnDueDate, Date returnDate, String remarks");
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				BookLendBean bean = new BookLendBean(id);
//				list.add(bean);
//			}
//			// カテゴリ一覧をListとして返す
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new DAOException("レコードの取得に失敗しました。");
//		} finally {
//			try {
//				// リソースの開放
//				if(rs != null) rs.close();
//				if(st != null) st.close();
//				close();
//			} catch (Exception e) {
//				throw new DAOException("リソースの開放に失敗しました。");
//			}
//		}
//	}
		
	
	
	
	/****************************************************************/
	/* for BookCategory											*/
	/****************************************************************/
	public List<BookCategoryBean> findAllBookCategory() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM book_category ORDER BY code";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<BookCategoryBean> list = new ArrayList<>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				BookCategoryBean bean = new BookCategoryBean(code, name);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	/****************************************************************/
	/* for BookMaster												*/
	/****************************************************************/
	public BookMasterBean addBookMaster(String isbn, String title, Integer categoryCode, String author, String publisher, Date publishDate) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			BookMasterBean bean = null;
			// SQL文の作成
			String sql = "INSERT INTO book_master VALUES(?, ?, ?::Integer, ?, ?, ?, NULL)";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			st.setString(2, title);
			st.setString(3, categoryCode == null ? null : categoryCode.toString());
			st.setString(4, author);
			st.setString(5, publisher);
			st.setDate(6, publishDate);
			//st.setString(7, coverURL);
			// SQLの実行
			int cRow = st.executeUpdate();
			if (cRow >0) {
				bean = findBookMasterByISBN(isbn);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの挿入に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public BookMasterBean updateBookMaster(String isbn, String title, Integer categoryCode, String author, String publisher, Date publishDate, String coverURL) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			BookMasterBean bean = null;
			// SQL文の作成
			String sql = "UPDATE book_master SET "
					+ "title = COALESCE(?, title), "
					+ "category_code = COALESCE(?::INTEGER, category_code), "
					+ "author = COALESCE(?, author), "
					+ "publisher = COALESCE(?, publisher), "
					+ "publish_date = COALESCE(?, publish_date), "
					+ "cover_url = COALESCE(?, cover_url) "
					+ "WHERE isbn = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, categoryCode == null ? null : categoryCode.toString());
			st.setString(3, author);
			st.setString(4, publisher);
			st.setDate(5, publishDate);
			st.setString(6, coverURL);
			st.setString(7, isbn);

			// SQLの実行
			int cRow = st.executeUpdate();
			if (cRow >0) {
				bean = findBookMasterByISBN(isbn);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの更新に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int deleteBookMaster(String isbn) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			// SQL文の作成
			String sql = "DELETE FROM book_master WHERE isbn = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			// SQLの実行
			return st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの削除に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<BookMasterBean> findAllBookMaster() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM book_master bm JOIN book_category bc ON bm.category_code = bc.code "
					+ "ORDER BY code";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<BookMasterBean> list = new ArrayList<>();
			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				int categoryCode = rs.getInt("category_code");
				String categoryName = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date publishDate = rs.getDate("publish_date");
				String coverURL = rs.getString("cover_url");
				BookMasterBean bean = new BookMasterBean(isbn, title, categoryCode, author, publisher, publishDate, coverURL);
				bean.setCategoryName(categoryName);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public BookMasterBean findBookMasterByISBN(String isbn) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM book_master bm JOIN book_category bc ON bm.category_code = bc.code "
					+ "WHERE bm.isbn = ? "
					+ "ORDER BY code";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			BookMasterBean bean = null;
			if (rs.next()) {
				String title = rs.getString("title");
				int categoryCode = rs.getInt("category_code");
				String categoryName = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date publishDate = rs.getDate("publish_date");
				String coverURL = rs.getString("cover_url");
				bean = new BookMasterBean(isbn, title, categoryCode, author, publisher, publishDate, coverURL);
				bean.setCategoryName(categoryName);
			}
			// カテゴリ一覧をListとして返す
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<BookMasterBean> findBookMaster(String title, Integer categoryCode, String author, String publisher, Date fromPublishDate, Date toPublishDate) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM book_master bm JOIN book_category bc ON bm.category_code = bc.code "
					+ "WHERE (bm.title ~('.*' || ? || '.*') AND bm.category_code = ?::INTEGER AND bm.author ~('.*' || ? || '.*') "
					+ "AND bm.publisher ~('.*' || ? || '.*') AND bm.publish_date BETWEEN ? AND ?) IS NOT FALSE "
					+ "ORDER BY bm.isbn";

			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, categoryCode == null ? null : categoryCode.toString());
			st.setString(3, author);
			st.setString(4, publisher);
			st.setDate(5, fromPublishDate);
			st.setDate(6, toPublishDate);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<BookMasterBean> list = new ArrayList<>();
			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title_ = rs.getString("title");
				int categoryCode_ = rs.getInt("category_code");
				String categoryName = rs.getString("name");
				String author_ = rs.getString("author");
				String publisher_ = rs.getString("publisher");
				Date publishDate_ = rs.getDate("publish_date");
				String coverURL_ = rs.getString("cover_url");
				BookMasterBean bean = new BookMasterBean(isbn, title_, categoryCode_, author_, publisher_, publishDate_, coverURL_);
				bean.setCategoryName(categoryName);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	/****************************************************************/
	/* for Book													*/
	/****************************************************************/
	public BookBean addBook(String isbn) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			BookBean bean = null;
			// SQL文の作成
			String sql = "INSERT INTO book(id, isbn, arrival_date, remarks) VALUES(?, ?, CURRENT_DATE, NULL)";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			int id = DAOUtil.getNextVal(con, "book_id_seq");
			st.setInt(1, id);
			st.setString(2, isbn);
			//st.setDate(3, arrivalDate);
			//st.setString(3, remarks);
			// SQLの実行
			int cRow = st.executeUpdate();
			if (cRow > 0) {
				bean = findBookByID(id);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの挿入に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
	
	public BookBean updateBook(Integer id, String isbn, Date arrivalDate, Date disposalDate, String remarks) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			BookBean bean = null;
			// SQL文の作成
			String sql = "UPDATE book SET "
					+ "isbn = COALESCE(?, isbn), "
					+ "arrival_date = COALESCE(?, arrival_date), "
					+ "disposal_date = COALESCE(?, disposal_date), "
					+ "remarks = COALESCE(?, remarks) "
					+ "WHERE id = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			st.setDate(2, arrivalDate);
			st.setDate(3, disposalDate);
			st.setString(4, remarks);
			st.setInt(5, id);

			// SQLの実行
			int cRow = st.executeUpdate();
			if (cRow >0) {
				bean = findBookByID(id);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの挿入に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int deleteBook(Integer id) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			// SQL文の作成
			String sql = "DELETE FROM book WHERE id = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, id);

			// SQLの実行
			return st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの挿入に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<BookBean> findAllBook() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM book b JOIN book_master bm USING (isbn) " + 
							" JOIN book_category bc ON bm.category_code = bc.code ORDER BY b.id";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// SQLの実行
			rs = st.executeQuery();
			
			List<BookBean> list = new ArrayList<>();
			// 結果の取得および表示
			while (rs.next()) {
				int id = rs.getInt("id");
				String isbn = rs.getString("isbn");
				Date arrivalDate = rs.getDate("arrival_date");
				Date disposalDate = rs.getDate("disposal_date");
				String remarks = rs.getString("remarks");
				BookBean bean = new BookBean(id, isbn, arrivalDate, disposalDate, remarks);
				String title = rs.getString("title");
				int categoryCode = rs.getInt("code");
				String categoryName = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date publishDate = rs.getDate("publish_date");
				String coverURL = rs.getString("cover_url");
				bean.setBookMaster(isbn, title, categoryCode, categoryName, author, publisher, publishDate, coverURL);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public BookBean findBookByID(Integer id) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM book b JOIN book_master bm USING (isbn) " + 
							" JOIN book_category bc ON bm.category_code = bc.code WHERE b.id = ? ORDER BY b.id;";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			BookBean bean = null;
			if (rs.next()) {
				String isbn = rs.getString("isbn");
				Date arrivalDate = rs.getDate("arrival_date");
				Date disposalDate = rs.getDate("disposal_date");
				String remarks = rs.getString("remarks");
				bean = new BookBean(id, isbn, arrivalDate, disposalDate, remarks);
				String title = rs.getString("title");
				int categoryCode = rs.getInt("code");
				String categoryName = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date publishDate = rs.getDate("publish_date");
				String coverURL = rs.getString("cover_url");
				bean.setBookMaster(isbn, title, categoryCode, categoryName, author, publisher, publishDate, coverURL);
			}
			// カテゴリ一覧をListとして返す
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	
	
	public List<BookBean> findBook(String isbn, Date fromArrivalDate, Date toArrivalDate, Date fromDisposalDate, Date toDisposalDate, String remarks, String title, Integer categoryCode, String author, String publisher, Date fromPublishDate, Date toPublishDate) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM book b JOIN book_master bm USING (isbn) "
					+ "JOIN book_category bc ON bm.category_code = bc.code "
					+ "WHERE (b.isbn = ? AND b.arrival_date BETWEEN ? AND ? AND b.disposal_date BETWEEN ? AND ? "
					+ "AND b.remarks ~('.*' || ? || '.*') "
					+ "AND bm.title ~('.*' || ? || '.*') AND bm.category_code = ?::INTEGER AND bm.author ~('.*' || ? || '.*') "
					+ "AND bm.publisher ~('.*' || ? || '.*') AND bm.publish_date BETWEEN ? AND ?) IS NOT FALSE "
					+ "ORDER BY bm.isbn";

			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			st.setDate(2, fromArrivalDate);
			st.setDate(3, toArrivalDate);
			st.setDate(4, fromDisposalDate);
			st.setDate(5, toDisposalDate);
			st.setString(6, remarks);			
			st.setString(7, title);
			st.setString(8, categoryCode == null ? null : categoryCode.toString());
			st.setString(9, author);
			st.setString(10, publisher);
			st.setDate(11, fromPublishDate);
			st.setDate(12, toPublishDate);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<BookBean> list = new ArrayList<>();
			// 結果の取得および表示
			while (rs.next()) {
				int id = rs.getInt("id");
				String isbn_ = rs.getString("isbn");
				Date arrivalDate = rs.getDate("arrival_date");
				Date disposalDate = rs.getDate("disposal_date");
				String remarks_ = rs.getString("remarks");
				BookBean bean = new BookBean(id, isbn, arrivalDate, disposalDate, remarks_);
				String title_ = rs.getString("title");
				int categoryCode_ = rs.getInt("code");
				String categoryName = rs.getString("name");
				String author_ = rs.getString("author");
				String publisher_ = rs.getString("publisher");
				Date publishDate = rs.getDate("publish_date");
				String coverURL = rs.getString("cover_url");
				bean.setBookMaster(isbn_, title_, categoryCode_, categoryName, author_, publisher_, publishDate, coverURL);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}
	
	public BookBean addDisposalDate(String remarks, int id) throws DAOException{

		if(con==null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL文の作成
			System.out.println("Creating statement(addDisposalDate)...");
			String sql = "UPDATE book SET disposal_date = CURRENT_DATE, remarks = ? WHERE id = ?;";

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//IDの指定
			st.setString(1, remarks);
			st.setInt(2,id);

			//SQLの実行
			st.executeUpdate();//SELECTの場合はst.executeQuery();になる
										//UPDATE/INSERTの場合はst.executeUpdate();になる
			BookBean bBean = new BookBean();
			bBean = findBookByID(id);
			return bBean;

		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}
			finally {
				try {
				//リソースの開放
				if(rs != null) rs.close();
				if(st != null) st.close();
				close();
				} catch (Exception e) {
					throw new DAOException("リソースの開放に失敗しました");
		
				}
			}
			}
	
	/*******************************************************/
	
	private void getConnection() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// URL、ユーザ名、パスワードの設定
			String url = "jdbc:postgresql:library";
			String user = "postgres";
			String pass = "himitu";
			// データベースへの接続
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("接続に失敗しました。");
		}
	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	public static void main(String[] args) {
		try {
			
			//BookDAO dao = new BookDAO();
			//Date publishedDate = dao.getPublishedDate(2);
			//List<Date> listDate = dao.getReturnDueDate(8);
//			
			//System.out.println(publishedDate);
			//System.out.println(list);
//			BookDAO dao = new BookDAO();
//			System.out.println();
//			System.out.println("findAllBookCategory**************");
//			List<BookCategoryBean> list = dao.findAllBookCategory();
//			for (BookCategoryBean bc : list) {
//				System.out.println(bc);
//			}
//			System.out.println();
//			System.out.println("findAllBookMaster**************");
//			List<BookMasterBean> listMaster = dao.findAllBookMaster();
//			for (BookMasterBean bc : listMaster) {
//				System.out.println(bc);
//			}
//			System.out.println();
//			System.out.println("findAllBook**************");
//			List<BookBean> listBook = dao.findAllBook();
//			for (BookBean b : listBook) {
//				System.out.println(b);
//			}
//
//			System.out.println();
//			System.out.println("findBookByID**************");
//			BookBean book = dao.findBookByID(1);
//			System.out.println(book);
//			
//			System.out.println();
//			System.out.println("findBook**************");
//			listBook = dao.findBook(null, Date.valueOf("2009-01-01"), Date.valueOf("2009-06-30"), null, null, null, null, null, null, null, null, null);
//			for (BookBean b : listBook) {
//				System.out.println(b);
//			}
//			System.out.println();
//			listBook = dao.findBook(null, null, null, null, null, null, "歴史", null, null, null, null, null);
//			for (BookBean b : listBook) {
//				System.out.println(b);
//			}
//			
//			
//			System.out.println();
//			System.out.println("addBookMaster**************");
//			dao.addBookMaster("9784808707859", "もっと知りたい葛飾北斎 : 生涯と作品", 8, "永田生慈／監修", "東京美術", Date.valueOf("2005-08-01"), "https://cover.openbd.jp/9784808707859.jpg");
//
//			System.out.println();
//			System.out.println("findAllBookMaster**************");
//			listMaster = dao.findAllBookMaster();
//			for (BookMasterBean bc : listMaster) {
//				System.out.println(bc);
//			}
//
//			System.out.println();
//			System.out.println("updateBookMaster**************");
//			dao.updateBookMaster("9784808707859", "もっともっと知りたい葛飾北斎", null, null, null, null, null);
//
//			System.out.println();
//			System.out.println("findBookMasterByISBN**************");
//			BookMasterBean bookMaster = dao.findBookMasterByISBN("9784808707859");
//			System.out.println(bookMaster);
//
//			System.out.println();
//			System.out.println("findBookMaster**************");
//			listMaster = dao.findBookMaster("世界", null, null, null, null, null);
//			for (BookMasterBean bc : listMaster) {
//				System.out.println(bc);
//			}
//
//			System.out.println();
//			listMaster = dao.findBookMaster(null, null, null, null, Date.valueOf("2018-01-01"), Date.valueOf("2018-12-31"));
//			for (BookMasterBean bc : listMaster) {
//				System.out.println(bc);
//			}
//
//			System.out.println();
//			listMaster = dao.findBookMaster(null, 2, null, null, null, null);
//			for (BookMasterBean bc : listMaster) {
//				System.out.println(bc);
//			}
//
//			System.out.println();
//			System.out.println("deleteBookMaster**************");
//			dao.deleteBookMaster("9784808707859");
//			listMaster = dao.findAllBookMaster();
//			for (BookMasterBean bc : listMaster) {
//				System.out.println(bc);
//			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


