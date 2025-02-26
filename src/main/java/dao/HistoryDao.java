package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dbmanager.DBManager;
import dto.HistoryDto;

public class HistoryDao {
	public void insertHistoryData(String lat, String lnt) {
		LocalDateTime now = LocalDateTime.now().withNano(0);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO HISTORY (LAT, LNT, VIEW_DT) "
				+ "VALUES (?, ?, ?)";
		
		try {
			con = DBManager.connectDB();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lat);
			pstmt.setString(2, lnt);
			pstmt.setString(3, now.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<HistoryDto> selectHistoryData() {
		ArrayList<HistoryDto> historyList = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT *"
				+ " FROM HISTORY"
				+ " ORDER BY ID DESC";
		
		try {
			con = DBManager.connectDB();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				HistoryDto history = HistoryDto.builder()
									.id(rs.getInt("ID"))
									.lat(rs.getString("LAT"))
									.lnt(rs.getString("LNT"))
									.viewDt(rs.getString("VIEW_DT"))
									.build();
				historyList.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, stmt, rs);
		}
		return historyList;
	}
	
	public void deleteHistoryData(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM HISTORY WHERE ID = ?";
		
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
