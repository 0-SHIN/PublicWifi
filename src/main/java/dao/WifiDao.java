package dao;

import java.sql.*;
import java.util.ArrayList;

import dbmanager.DBManager;
import dto.WifiDto;

import com.google.gson.JsonObject;

import api_service.CallAPI;

public class WifiDao {
	private static int BATCH_SIZE = 4999;
	
	public static int insertWifiData() {
		ArrayList<JsonObject> wifiList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO WIFI VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			wifiList = CallAPI.callAPI();
			con = DBManager.connectDB();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < wifiList.size(); i++) {
				pstmt.setString(1, wifiList.get(i).get("X_SWIFI_MGR_NO").getAsString());
				pstmt.setString(2, wifiList.get(i).get("X_SWIFI_WRDOFC").getAsString());
				pstmt.setString(3, wifiList.get(i).get("X_SWIFI_MAIN_NM").getAsString());
				pstmt.setString(4, wifiList.get(i).get("X_SWIFI_ADRES1").getAsString());
				pstmt.setString(5, wifiList.get(i).get("X_SWIFI_ADRES2").getAsString());
				pstmt.setString(6, wifiList.get(i).get("X_SWIFI_INSTL_FLOOR").getAsString());
				pstmt.setString(7, wifiList.get(i).get("X_SWIFI_INSTL_TY").getAsString());
				pstmt.setString(8, wifiList.get(i).get("X_SWIFI_INSTL_MBY").getAsString());
				pstmt.setString(9, wifiList.get(i).get("X_SWIFI_SVC_SE").getAsString());
				pstmt.setString(10, wifiList.get(i).get("X_SWIFI_CMCWR").getAsString());
				pstmt.setString(11, wifiList.get(i).get("X_SWIFI_CNSTC_YEAR").getAsString());
				pstmt.setString(12, wifiList.get(i).get("X_SWIFI_INOUT_DOOR").getAsString());
				pstmt.setString(13, wifiList.get(i).get("X_SWIFI_REMARS3").getAsString());
				pstmt.setString(14, wifiList.get(i).get("LAT").getAsString());
				pstmt.setString(15, wifiList.get(i).get("LNT").getAsString());
				pstmt.setString(16, wifiList.get(i).get("WORK_DTTM").getAsString());
				
				pstmt.addBatch();
				pstmt.clearParameters();
				
				if ((i + 1) % BATCH_SIZE == 0) {
					pstmt.executeBatch();
					pstmt.clearBatch();
					con.commit();
				}
			}
			
			pstmt.executeBatch();
			pstmt.clearBatch();
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		
		return wifiList.size();
	}
	
	public ArrayList<WifiDto> getNearbyWifiInfo(String lat, String lnt) {
		ArrayList<WifiDto> wifiList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT ROUND(6371 * ACOS(COS(RADIANS(?)) * COS(RADIANS(LAT)) * COS(RADIANS(LNT) - RADIANS(?))"
					+ "	+ SIN(RADIANS(?)) * SIN(RADIANS(LAT))) ,4) AS DISTANCE,"
					+ " *"
					+ " FROM WIFI"
					+ " ORDER BY DISTANCE"
					+ " LIMIT 20";
		
		try {
			con = DBManager.connectDB();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, Double.parseDouble(lat));
			pstmt.setDouble(2, Double.parseDouble(lnt));
			pstmt.setDouble(3, Double.parseDouble(lat));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				WifiDto wifi = WifiDto.builder()
						.distance(rs.getDouble("DISTANCE"))
						.mgrNo(rs.getString("X_SWIFI_MGR_NO"))
						.wrdofc(rs.getString("X_SWIFI_WRDOFC"))
						.mainNm(rs.getString("X_SWIFI_MAIN_NM"))
						.adres1(rs.getString("X_SWIFI_ADRES1"))
						.adres2(rs.getString("X_SWIFI_ADRES2"))
						.instlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"))
						.instlTy(rs.getString("X_SWIFI_INSTL_TY"))
						.instlMby(rs.getString("X_SWIFI_INSTL_MBY"))
						.svcSe(rs.getString("X_SWIFI_SVC_SE"))
						.cmcwr(rs.getString("X_SWIFI_CMCWR"))
						.cnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"))
						.inoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"))
						.remars3(rs.getString("X_SWIFI_REMARS3"))
						.lat(rs.getString("LAT"))
						.lnt(rs.getString("LNT"))
						.workDttm(rs.getString("WORK_DTTM"))
						.build();
				
				wifiList.add(wifi);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return wifiList;
	}
	
	public WifiDto selectWifi(String mgrNo) {
		WifiDto wifi = new WifiDto();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT *"
					+ " FROM WIFI"
					+ " WHERE X_SWIFI_MGR_NO = ?";
		
		try {
			con = DBManager.connectDB();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mgrNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				wifi = WifiDto.builder()
					.mgrNo(rs.getString("X_SWIFI_MGR_NO"))
					.wrdofc(rs.getString("X_SWIFI_WRDOFC"))
					.mainNm(rs.getString("X_SWIFI_MAIN_NM"))
					.adres1(rs.getString("X_SWIFI_ADRES1"))
					.adres2(rs.getString("X_SWIFI_ADRES2"))
					.instlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"))
					.instlTy(rs.getString("X_SWIFI_INSTL_TY"))
					.instlMby(rs.getString("X_SWIFI_INSTL_MBY"))
					.svcSe(rs.getString("X_SWIFI_SVC_SE"))
					.cmcwr(rs.getString("X_SWIFI_CMCWR"))
					.cnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"))
					.inoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"))
					.remars3(rs.getString("X_SWIFI_REMARS3"))
					.lat(rs.getString("LAT"))
					.lnt(rs.getString("LNT"))
					.workDttm(rs.getString("WORK_DTTM"))
					.build();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return wifi;
	}
		
}
