package api_service;

import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.*;
import okhttp3.*;

public class CallAPI {
	private static OkHttpClient client = new OkHttpClient();
	private static String urlAPI = "http://openapi.seoul.go.kr:8088/436859457868797334346149445148/json/TbPublicWifiInfo/";
	private static final int CALL_LIMIT = 1000;
	
	public static int countAPI() {
		int totalCnt = 0;
		String url = urlAPI + "1/1/";
		Request request = new Request.Builder().url(url).build();
		
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				if (response.body() != null) {
					JsonElement je = JsonParser.parseString(response.body().string());
					totalCnt = je.getAsJsonObject().get("TbPublicWifiInfo")
								 .getAsJsonObject().get("list_total_count")
								 .getAsInt();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalCnt;
	}
	
	public static ArrayList<JsonObject> callAPI() {
		int totalCnt = countAPI();
		ArrayList<JsonObject> wifiList = new ArrayList<>();		
		
		for (int i = 0; i <= totalCnt / CALL_LIMIT; i++) {
			int start = (CALL_LIMIT * i) + 1;
			int end = (i == totalCnt / CALL_LIMIT) ? totalCnt : CALL_LIMIT * (i + 1);
			
			try {
				String url = String.format(urlAPI + "%d/%d/", start, end);
				Request request = new Request.Builder().url(url).build();
				Response response = client.newCall(request).execute();
				
				if (response.isSuccessful()) {
					ResponseBody reponsebody = response.body();
					if(response.body() != null) {
						JsonElement je = JsonParser.parseString(reponsebody.string());
						JsonObject jo = (JsonObject)je.getAsJsonObject().get("TbPublicWifiInfo");
						JsonArray ja = jo.getAsJsonObject().get("row")
								 		 .getAsJsonArray();
						
						for (JsonElement e: ja) {
							wifiList.add(e.getAsJsonObject());
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return wifiList;
	}
}
