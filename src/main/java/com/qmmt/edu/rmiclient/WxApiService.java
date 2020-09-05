package com.qmmt.edu.rmiclient;

public interface WxApiService {
	
	public String getWxOpenid(String weixinAccountid,String code);
	
	public Long createWxUser(String openid,String weixinAccountid);

	public void openNotify(String wxaccount, String openId, String courseName, String dateStr, String remark, String url);

	public String getShortUrl(String wxaccountid,String longUrl);
	
	public String createQrcodeUrl(String wxaccountid,String expire_seconds,Integer qrcodeid,Integer qtype,String purpose);

}
