package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dbmanager.DBManager;
import dto.BookmarkDto;

public class BookmarkDao {
	public void insertBookmarkData(String bookmarkId, String mgrNo) {
		LocalDateTime now = LocalDateTime.now().withNano(0);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO BOOKMARK (BOOKMARKGROUP_ID, MGR_NO, REGIST_DT) "
				+ "VALUES (?, ?, ?)";
		
		try {
			con = DBManager.connectDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookmarkId);
			pstmt.setString(2, mgrNo);
			pstmt.setString(3, now.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<BookmarkDto> selectBookmarkList() {
		ArrayList<BookmarkDto> BookmarkList = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT b.ID AS BID, bg.NAME AS NAME, w.X_SWIFI_MAIN_NM AS MAIN_NM, b.REGIST_DT AS REGIST_DT"
				+ " FROM BOOKMARK b"
				+ " LEFT JOIN BOOKMARK_GROUP bg ON b.BOOKMARKGROUP_ID = bg.ID"
				+ " LEFT JOIN WIFI w ON b.MGR_NO = w.X_SWIFI_MGR_NO"
				+ " ORDER BY b.ID";
		
		try {
			con = DBManager.connectDB();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				BookmarkDto bookmark = BookmarkDto.builder()
									.id(rs.getInt("BID"))
									.bookmarkGroupName(rs.getString("NAME"))
									.mainNm(rs.getString("MAIN_NM"))
									.registDt(rs.getString("REGIST_DT"))
									.build();
				BookmarkList.add(bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		return BookmarkList;
	}
	
	public BookmarkDto selectBookmarkData(String id) {
		BookmarkDto bookmark = new BookmarkDto();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT b.ID AS BID, bg.NAME AS NAME, w.X_SWIFI_MAIN_NM AS MAIN_NM, b.REGIST_DT AS REGIST_DT"
				+ " FROM BOOKMARK b"
				+ " LEFT JOIN BOOKMARK_GROUP bg ON b.BOOKMARKGROUP_ID = bg.ID"
				+ " LEFT JOIN WIFI w ON b.MGR_NO = w.X_SWIFI_MGR_NO"
				+ " WHERE b.ID = ?";
		
		try {
			con = DBManager.connectDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bookmark = BookmarkDto.builder()
						.id(rs.getInt("BID"))
						.bookmarkGroupName(rs.getString("NAME"))
						.mainNm(rs.getString("MAIN_NM"))
						.registDt(rs.getString("REGIST_DT"))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return bookmark;
	}
	
	public void deleteBookmarkData(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM BOOKMARK WHERE ID = ?";
		
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
	
	public int checkExistBookmark(String bookmarkId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM BOOKMARK WHERE BOOKMARKGROUP_ID = ?";
		
		try {
			con = DBManager.connectDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bookmarkId));
			rs = pstmt.executeQuery();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return cnt;
	}
}
