package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dbmanager.DBManager;
import dto.BookmarkGroupDto;

public class BookmarkGroupDao {
	public void insertBookmakrGroup(String name, String order) {
		LocalDateTime now = LocalDateTime.now().withNano(0);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO BOOKMARK_GROUP (NAME, ORDER_NUM, REGIST_DT, EDIT_DT)"
				+ " VALUES (?, ?, ?, '')";
		
		try {
			con = DBManager.connectDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, order);
			pstmt.setString(3, now.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<BookmarkGroupDto> selectBookmarkGroupList() {
		ArrayList<BookmarkGroupDto> bookmarkGroupList = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT *"
				+ " FROM BOOKMARK_GROUP"
				+ " ORDER BY ORDER_NUM";
		
		try {
			con = DBManager.connectDB();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				BookmarkGroupDto bookmarkGroup = BookmarkGroupDto.builder()
									.id(rs.getInt("ID"))
									.name(rs.getString("NAME"))
									.orderNum(rs.getInt("ORDER_NUM"))
									.registDt(rs.getString("REGIST_DT"))
									.editDt(rs.getString("EDIT_DT"))
									.build();
				bookmarkGroupList.add(bookmarkGroup);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		return bookmarkGroupList;
	}
	
	public BookmarkGroupDto selectBookmarkGroup(String id) {
		BookmarkGroupDto bookmarkGroup = new BookmarkGroupDto();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT ID, NAME, ORDER_NUM"
					+ " FROM BOOKMARK_GROUP"
					+ " WHERE ID = ?";
		
		try {
			con = DBManager.connectDB();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookmarkGroup = BookmarkGroupDto.builder()
					.id(rs.getInt("ID"))
					.name(rs.getString("NAME"))
					.orderNum(rs.getInt("ORDER_NUM"))
					.build();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return bookmarkGroup;
	}
	
	public void updateBookmarkGroup(String id, String name, String order) {
		LocalDateTime now = LocalDateTime.now().withNano(0);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "UPDATE BOOKMARK_GROUP SET NAME = ?, ORDER_NUM = ?, EDIT_DT = ?"
				+ " WHERE ID = ?;";
		
		try {
			con = DBManager.connectDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, Integer.parseInt(order));
			pstmt.setString(3, now.toString());
			pstmt.setInt(4, Integer.parseInt(id));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	public void deleteBookmarkGroup(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM BOOKMARK_GROUP WHERE ID = ?";
		
		try {
			con = DBManager.connectDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  Integer.parseInt(id));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
}
