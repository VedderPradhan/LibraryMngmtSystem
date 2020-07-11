package lib.DAO;

import lib.bean.MemberBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import lib.DAO.DAOException;

public class MemberDAO {
	private Connection con;	
	public MemberDAO() throws DAOException{
		getConnection();
	}
	
	public MemberBean findMemberById(int id) throws DAOException{
		if(con==null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//SQL文の作成
			System.out.println("Creating statement...");
			String sql = "SELECT * FROM member_list WHERE member_id=?";
			
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//IDの指定
			st.setInt(1,id);
			
			//SQLの実行
			rs = st.executeQuery();//SELECTの場合はst.executeQuery();になる
										//UPDATE/INSERTの場合はst.excuteUpdate();になる			
			//MemberBean mBean = new MemberBean();
			//結果の取得および表示
			
			if (rs.next()) {
				int memberId = rs.getInt("member_id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				Date joinedDate = rs.getDate("joined_date");
				Date withdrawDate = rs.getDate("withdraw_date");
				
				MemberBean mBean = new MemberBean(memberId, name, address, tel, 
						email, birthday, joinedDate, withdrawDate);
				
//				会員情報をmBeanとして返す
				return mBean;
			}
			else {
				return null;
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}finally {
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
	
	public MemberBean insertMember(String name, String address, String tel, String email, Date birthday) throws DAOException{
		if(con==null) {//コネクションが確立する
			getConnection();
		}
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//SQL文の作成
			System.out.println("Creating statement(INSERT command)...");
			String sql = "INSERT INTO member_list VALUES(?,?,?,?,?,?, CURRENT_DATE, NULL)";
			
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			
			int id = DAOUtil.getNextVal(con, "member_list_member_id_seq");
			
			//引数で与えられた会員情報(name, address, tel, email, birthday)の指定
			st.setInt(1, id);
			st.setString(2, name);
			st.setString(3, address);
			st.setString(4, tel);
			st.setString(5, email);
			st.setDate(6, birthday);
			
			//SQLの実行
			st.executeUpdate();//SELECTの場合はst.executeQuery();になる
										//UPDATE/INSERTの場合はst.excuteUpdate();になる
			
			MemberBean iBean = new MemberBean();			
			iBean = findMemberById(id);
			return iBean;
			//結果の取得および表示		
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		}finally {
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
	
	public MemberBean addWithdrawDate(int id) throws DAOException{

		if(con==null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL文の作成
			System.out.println("Creating statement(addWithdrawDate)...");
			String sql = "UPDATE member_list SET withdraw_date = CURRENT_DATE WHERE member_id = ?;";

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//IDの指定
			st.setInt(1,id);

			//SQLの実行
			st.executeUpdate();//SELECTの場合はst.executeQuery();になる
										//UPDATE/INSERTの場合はst.executeUpdate();になる
			
			MemberBean mBean = new MemberBean();
			mBean = findMemberById(id);
			return mBean;

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
	
	public MemberBean updateName(int id, String name) throws DAOException{
		
		if(con==null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//SQL文の作成
			System.out.println("Creating statement(updateName)...");
			String sql = "UPDATE member_list SET name=? WHERE member_id=?;";

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//IDと名前の指定
			st.setInt(2,id);
			st.setString(1,name);

			//SQLの実行
			st.executeUpdate();//SELECTの場合はst.executeQuery();になる
										//UPDATE/INSERTの場合はst.executeUpdate();になる
			
			MemberBean mBean = new MemberBean();
			mBean = findMemberById(id);
			return mBean;

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

	
	private void getConnection() throws DAOException{

		try {
			//JDBC ドライバの登録
			Class.forName("org.postgresql.Driver");
			//URL、ユーザ名、パスワードの設定
			String url = "jdbc:postgresql:library";
			String user = "postgres";
			String pass = "himitu";
			
			//データベースの接続
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection(url, user, pass);			
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました");
		}
	}
	
	private void close() throws SQLException{
		if (con != null) {
			con.close();
			con = null;
		}
	}

	public static void main(String[] args) throws DAOException{
		MemberDAO mDAO = new MemberDAO();
		
		MemberBean testBean = new MemberBean();
		testBean = mDAO.updateName(8, "Jame");
		
		System.out.println(testBean);
		
	}
}


