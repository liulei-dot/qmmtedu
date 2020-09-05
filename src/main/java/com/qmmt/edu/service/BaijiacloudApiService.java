package com.qmmt.edu.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qmmt.edu.util.HttpsUtils;
import com.qmmt.edu.util.JSONUtils;

@Service("baijiacloudApiService")
public class BaijiacloudApiService {
	
	
	//百家云开发者
	public static String partner_id = "49670801";
	
	public static String paternerKey = "ptfv2pCqXpVeu3qujHT6S4crLakrQbBcXcmWwOiVmspMPGguHOv478e+dwws4JL28r05iEfCKCWhzpvQwLrKLQ==";
	

	 public String createStudentCode(String roomId,String userNumber,String userAvatar) {
		 if(StringUtils.isBlank(roomId) ||StringUtils.isBlank(userNumber) ) {
				return null;
			}
			
		 
			String url ="https://api.baijiayun.com/openapi/room/getcode";
			
			Map<String,String> map = new HashMap<String,String>();  
			map.put("partner_id",partner_id);  
			map.put("room_id",roomId);  
			map.put("user_number",userNumber);  
			map.put("user_avatar",userAvatar); 
			map.put("timestamp",""+System.currentTimeMillis()/1000);  
			String sign = generateSign( map,paternerKey);
			map.put("sign",sign);  
			
			
			
			try {
				String res = HttpsUtils.post(url, null, map, null);
				System.out.println(res);
				JSONObject jobj = JSONUtils.toJSONObject(res);
				JSONObject datajobj = jobj.getJSONObject("data");
				if(datajobj != null) {
					if(datajobj.getString("student_code") != null)
						return datajobj.getString("student_code");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	public HashMap<String,String> createRoom(String title,Date startTime,Date endTime,Integer maxUsers) {
		HashMap<String,String> retMap = new HashMap<String,String>();
		if(StringUtils.isBlank(title) || startTime == null || endTime == null || maxUsers == null || maxUsers < 1) {
			return retMap;
		}
		
		
		
		String url ="https://api.baijiayun.com/openapi/room/create";
		
		Map<String,String> map = new HashMap<String,String>();  
		map.put("partner_id",partner_id);  
		map.put("title",title);  
		map.put("start_time",""+(startTime.getTime()/1000));  
		map.put("end_time",""+(endTime.getTime()/1000));  
		map.put("type","2");  
		map.put("media_type","0");  
		map.put("max_users",maxUsers.toString());  
		map.put("pre_enter_time","0");  
		map.put("timestamp",""+System.currentTimeMillis()/1000);  
		String sign = generateSign( map,paternerKey);
		System.out.println(sign);
		map.put("sign",sign);  
		
		
		
		try {
			String res = HttpsUtils.post(url, null, map, null);
			System.out.println(res);
			JSONObject jobj = JSONUtils.toJSONObject(res);
			JSONObject datajobj = jobj.getJSONObject("data");
			if(datajobj != null) {
				if(datajobj.getString("room_id") != null)
					retMap.put("room_id", datajobj.getString("room_id"));
				if(datajobj.getString("admin_code") != null)
					retMap.put("admin_code", datajobj.getString("admin_code"));
				if(datajobj.getString("teacher_code") != null)
					retMap.put("teacher_code", datajobj.getString("teacher_code"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retMap;

	}
	
	public static String generateSign(Map<String, String> map,String paternerKey){
		Map<String, String> tmap = order(map);
		if(tmap.containsKey("sign")){
			tmap.remove("sign");
		}
		String str = mapJoin(tmap, false, false);
		return DigestUtils.md5Hex(str+"&partner_key="+paternerKey);
	}
	
	/**
	 * Map key 排序
	 * @param map
	 * @return
	 */
	public static Map<String, String> order(Map<String, String> map) {
		HashMap<String, String> tempMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (int i = 0; i < infoIds.size(); i++) {
			Map.Entry<String, String> item = infoIds.get(i);
			tempMap.put(item.getKey(), item.getValue());
		}

		return tempMap;
	}
	
	/**
	 * url 参数串连
	 * @param map
	 * @param keyLower
	 * @param valueUrlencode
	 * @return
	 */
	public static String mapJoin(Map<String, String> map,boolean keyLower,boolean valueUrlencode){
		StringBuilder stringBuilder = new StringBuilder();
		for(String key :map.keySet()){
			if(map.get(key)!=null&&!"".equals(map.get(key))){
				try {
					String temp = (key.endsWith("_")&&key.length()>1)?key.substring(0,key.length()-1):key;
					stringBuilder.append(keyLower?temp.toLowerCase():temp)
								 .append("=")
								 .append(valueUrlencode?URLEncoder.encode(map.get(key),"utf-8").replace("+", "%20"):map.get(key))
								 .append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		if(stringBuilder.length()>0){
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
		}
		return stringBuilder.toString();
	}

	public String enterRoomData(String roomId, Long id, String nickname, String headimgurl) {
		Map<String,String> map = new HashMap<String,String>();  
		map.put("room_id",roomId);  
		map.put("user_number",id.toString());  
		map.put("user_name",""+nickname);  
		map.put("user_role","0");  
		map.put("user_avatar",headimgurl);
		String sign = generateSign( map,paternerKey);
		/*
		 * 参数	类型	是否必填	示例/默认值	描述
			room_id	string	是	12345678901234	房间ID
			user_number	int	是	3413414	合作方账号体系下的用户ID号
			user_name	string	是	张三	显示的用户昵称
			user_role	int	是	0	0:学生 1:老师 2:管理员
			user_avatar	string	否	http://xxx.png	用户头像
			sign	string	是	xxxxxxxxxxxxxxxx	请求接口参数签名
		 * */
		String data = "room_id="+roomId+"" + 
				"&user_number="+id+"" + 
				"&user_role=0" + 
				"&user_avatar="+headimgurl+"" + 
				"&user_name="+nickname+"" + 
				"&sign="+sign+"";
		return data;
	}

}
