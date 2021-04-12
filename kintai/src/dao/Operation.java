package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Time;

public class Operation {

	private final String JDBC_URL ="jdbc:mysql://localhost/kenshu_db?serverTimezone = JST";
	private final String DB_USER = "root";
	private final String DB_PASS = "hsroot";

	private String selectMessage = "検索完了";
	private String addMessage = "登録成功";
	private String deleteMessage = "削除成功";





	public String getSelectMessage() {
		return selectMessage;
	}


	public String getAddMessage() {
		return addMessage;
	}


	public String getDeleteMessage() {
		return deleteMessage;
	}

	/**
	 * 検索
	 *
	 *
	 * @return List<Time>
	 */
	public List<Time> search(String ym , List<Time> times) {
		try {

			// JDBCに接続
			Class.forName("com.mysql.cj.jdbc.Driver");
			// データベースに接続
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SQL文
			String sql = "select *";
			sql = sql + " from tbl_kintai";
			sql = sql + " where kinmu_ymd like '" + ym + "%'";

			PreparedStatement stmt = conn.prepareStatement(sql);
			// 結果を保存
			ResultSet rs = stmt.executeQuery();


			while(rs.next()) {

				String kinmuDay = rs.getString("kinmu_ymd");
				String st = rs.getString("work_st");
				String ed = rs.getString("work_ed");
				String rt = rs.getString("work_rt");

				for(int i = 0; i < times.size(); i++) {

					Time time = new Time();
					time = times.get(i);
					String ymd = time.getYmd();

					if(kinmuDay.equals(ymd)) {
						time.setWorkSt(st);
						time.setWorkEd(ed);
						time.setWorkRt(rt);
					}
				}
			}
		}
		catch (SQLException e) {
//			e.printStackTrace();
			selectMessage = "検索失敗";
		}
		catch (ClassNotFoundException e) {

//			e.printStackTrace();
			selectMessage = "検索失敗";
		}

			return times;
	}


	/**
	 * 追加
	 *
	 *
	 *
	 */
	public void add(String ymd ,String st, String ed , String rt) {

		// JDBCに接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SQL文
			String sql = "insert into tbl_kintai";
			sql = sql + " values('" + ymd + "','" + st + "','" + ed + "','" + rt +"')";

			PreparedStatement stmt = conn.prepareStatement(sql);

			int rs = stmt.executeUpdate(sql);
		}
		catch (ClassNotFoundException e) {
			addMessage = "登録失敗";
		}
		catch (SQLException e) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続
				Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				String sql = "update tbl_kintai";
				sql = sql + " set work_st = '" + st + "' ," ;
				sql = sql + "work_ed = '"+ ed + "'," ;
				sql = sql + "work_rt = '"+ rt + "'" ;
				sql = sql + "where kinmu_ymd = '" + ymd + "'";

				PreparedStatement stmt = conn.prepareStatement(sql);
//				System.out.println(sql);
//				System.out.println("更新");
				int rs = stmt.executeUpdate(sql);
			}
			catch (ClassNotFoundException e1) {
				addMessage = "登録失敗";
			}
			catch (SQLException e1) {
				addMessage = "登録失敗";
			}
		}
	}

	/**
	 * 削除
	 *
	 *
	 *
	 * @param ym
	 */
	public void delete(String ym) {
		// JDBCに接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			// SQL文
			String sql = "delete";
			sql = sql + " from tbl_kintai";
			sql = sql + " where kinmu_ymd like '" + ym +"%'";

			PreparedStatement stmt = conn.prepareStatement(sql);

			int rs = stmt.executeUpdate(sql);
		}
		catch (ClassNotFoundException e) {
			deleteMessage = "削除失敗";

		}
		catch (SQLException e) {
			deleteMessage = "削除失敗";
		}
	}
}
