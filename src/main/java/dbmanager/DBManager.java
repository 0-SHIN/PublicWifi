package dbmanager;

import java.sql.*;

public class DBManager {
	
	public static Connection connectDB() {
		Connection con = null;
		String path = "DB file path";
		String url = "jdbc:sqlite:" + path;
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void truncateTable() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = connectDB();
			con.setAutoCommit(false);
			stmt = con.createStatement();
			
			String sql = "DELETE FROM WIFI";
			stmt.addBatch(sql);

			sql = "DELETE FROM HISTORY";
			stmt.addBatch(sql);
			
			sql = "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'HISTORY'";
			stmt.addBatch(sql);
			
			sql = "DELETE FROM BOOKMARK_GROUP";
			stmt.addBatch(sql);
			
			sql = "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'BOOKMARK_GROUP'";
			stmt.addBatch(sql);
			
			sql = "DELETE FROM BOOKMARK";
			stmt.addBatch(sql);
			
			sql = "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = 'BOOKMARK'";
			stmt.addBatch(sql);
			
			stmt.executeBatch();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, rs);
		}
	}
}
