package com.qmmt.edu.util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

public class Constants {
	
	//从配置文件config读取的配置
	public static Configuration config = null;

	public static String jsonResultCode = "jsonResultCode";
	public static String jsonResultDesc = "jsonResultDesc";
	public static String jsonResultData = "jsonResultData";
	public static String pageResultData = "pageResultData";
	public static String jsonResultSize = "jsonResultSize";
	public static String jsonHasMore= "jsonHasMore";
	
	//返回码设置为这个之后，可以自定义  desc 返回给 客户端
	public static int CUSTOM_ERROR_DESC = 100000;
	// 指定分配给 活动用的 二维码ID范围
	public static Integer MAX_ACTIVITY_QRCODE = 100000;
	public static Integer MIN_ACTIVITY_QRCODE = 95000;
	
	
	//================ 微信公众号支付账户===========================================
	public static String APPID				= "wx158ee81b6b9a0832";//公众账号ID
	public static String MCH_ID 				= "1325574701";//商户号
	public static String PARTNER_KEY 			= "AWB9D695E3E8E277831C007884B4CA29";//微信商户平台管理里面的API秘钥
	public static String WECHAT_NOTIFY2C			= "http://access.dryork.cn/yueke/wxweb/2c/pay_back_notify.json";
	public static String WECHAT_NOTIFYMP			= "http://access.dryork.cn/yueke/wxweb/mp/pay_back_notify.json";
	public static String TRADE_TYPE 			= "JSAPI";     //JSAPI，NATIVE，APP，WAP
//	public static String APICLIENT_CERT_PATH 	= "/opt/iddc/payment/apiclient_cert.p12";   //线上秘钥地址
	//================ 请求微信支付时返回的错误码 ===========================================
	public static String OK 		= "1";				//请求
	public static String FAIL 		= "0";				//请求接口失败
	public static String ERROR_300 	= "300";			//请求参数错误
	//================ 请求微信支付时返回的错误码 ===========================================

	public static String clientInfo= "clientInfo";
	

	//二维码用途
	public static enum QrPurpose {
		COURSEID("cid"),TMPPDMP("pdmp");
		private final String value;

		private QrPurpose(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	//返回状态码对应表
	public static final Map<Integer,String> codeMap=new HashMap<Integer,String>(){
		private static final long serialVersionUID = 1L;
		{
			//系统级错误
			put(0,"请求成功");
			put(-1,"系统错误");
			put(-2,"参数不正确");
			put(-99,"该功能已停用，请升级新版更新");
			put(-100,"权限验证失败，已经登出");
			//-1001 开始为业务级别错误
			put(-1001,"登录的用户名和密码不匹配");
			put(-1002,"登录名或密码为空");
		}
	};
	
	public static String getConfig(String key){
		if(config == null)
			return null;
		else
			return config.getString(key);
	}
	
	static {
		Configurations configs = new Configurations();

		try {
			URL path = Constants.class.getClassLoader().getResource("");
			System.out.println("Properties path:"+path.getPath()+"config/config.properties");
			config = configs.properties(new File(path.getPath()+"config/config.properties"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
