package com.qmmt.edu.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.support.json.JSONUtils;
import com.qmmt.edu.persistence.po.EduOrder;
import com.qmmt.edu.pojo.EduCoursePojo;
import com.qmmt.edu.rmiclient.WxApiService;
import com.qmmt.edu.service.EduCourseService;
import com.qmmt.edu.util.Constants;
import com.qmmt.edu.util.CookieUtil;
import com.qmmt.edu.util.HessianFactoryUtil;
import com.wechat.bean.paymch.MchBaseResult;
import com.wechat.bean.paymch.MchPayNotify;
import com.wechat.bean.paymch.Unifiedorder;
import com.wechat.bean.paymch.UnifiedorderResult;
import com.wechat.util.ExpireSet;
import com.wechat.util.MD5;
import com.wechat.util.PayUtil;
import com.wechat.util.SignatureUtil;
import com.wechat.util.XMLConverUtil;
import com.wechat.utils.api.PayMchAPI;

@Controller
public class WXOrderController {
	
	final Logger logger = LoggerFactory.getLogger(WXOrderController.class);

	@Autowired
	EduCourseService eduCourseService;
	
	// 重复通知过滤 时效60秒
	private static ExpireSet<String> expireSet = new ExpireSet<String>(60);

	//公开课首页
	@RequestMapping(value = "/wxpay/pupt.htm")
	public void freeIndex(
			@RequestParam(value = "oid", required=true) Long orderId,
			@RequestParam(value = "mobile", required=false) String mobile,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		System.out.println(orderId+"-----------------"+mobile);
		
		String openId = getWxOpenId( request,  response);
		
		EduOrder eduOrder = eduCourseService.getEduOrder(orderId);
		if(StringUtils.isNotBlank(openId)){
			if(StringUtils.isNotBlank(mobile)){
				//更新手机号
				eduCourseService.saveOrUpdateStudent(openId,mobile,null);
			}
			
			if(eduOrder.getReceivableAmount().compareTo(BigDecimal.ZERO) == 0)
				eduCourseService.updateEnrollPay(orderId.toString(),openId,100,null,null);
			else
				eduCourseService.updateEnrollPay(orderId.toString(),openId,eduOrder.getReceivableAmount().multiply(new BigDecimal(100)).intValue(),null,null);
		}
		request.setAttribute(Constants.jsonResultCode,0);
	}
	
	@RequestMapping("/wxpay/mch_notify.json")
	public void wxpayNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/**
		 * 解析微信支付成功回调参数
		 */
		MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class, request.getInputStream());
		System.out.println("微信支付回调通知接口，请求参数：[" + com.qmmt.edu.util.JSONUtils.toJSONString(payNotify) + "]");
		/**
		 * 判断是否重复通知，已处理 去重
		 */
		if (expireSet.contains(payNotify.getTransaction_id())) {
			System.out.println("微信支付回调通知接口，订单重复通知！");
			return;
		}
		// 签名验证，判断回调通知是否来自 微信服务的通知
		if (SignatureUtil.validateAppSignature(payNotify, PARTNER_KEY)) {
			/** 
			 * 请求微信支付时 生成的商户支付单号
			 * 使用该订单号码，查询到用户的 支付信息和订单信息
			 * 判断支付的金额是否等于订单应支付金额。
			 * 如果支付金额和订单的应支付金额相等，处理订单和支付单表的状态。
			 */
			String pay_order_id 	= payNotify.getOut_trade_no();			// 商户生成的支付单ID
			Integer cash_fee 		= payNotify.getCash_fee();				// 微信用户的支付金额
			String bank_type 		= payNotify.getBank_type();				// 银行卡类型
			String transaction_id 	= payNotify.getTransaction_id();		// 微信支付生成的支付流水编号
			System.out.println("openid="+payNotify.getOpenid());
			System.out.println("pay_order_id="+pay_order_id);
			System.out.println("cash_fee="+cash_fee);
			System.out.println("bank_type="+bank_type);
			System.out.println("transaction_id="+transaction_id);
			System.out.println("微信支付处理成功");
			/** 微信支付处理成功后，给微信的应答报文信息 */
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("SUCCESS");
			baseResult.setReturn_msg("OK");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
			response.getOutputStream().flush();
			eduCourseService.updateEnrollPay(pay_order_id,payNotify.getOpenid(),cash_fee,bank_type,transaction_id);
			return;
		} else {
			System.out.println("微信支付回调通知接口，签名验证失败！");
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("FAIL");
			baseResult.setReturn_msg("ERROR");
			response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
			return;
		}
	}
	
	
	@RequestMapping(value = "/wxpay/pre_pay.htm", method = RequestMethod.GET)
	public String getDentist(
			@RequestParam(value = "course_id", required=true) Long courseId,
			Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String openId = getWxOpenId( request,  response);
		
		EduCoursePojo eduCoursePojo = eduCourseService.getEduCourseDetail(courseId);
		model.addAttribute("eduCourse", eduCoursePojo);
		
		if(StringUtils.isBlank(openId)) {
			return "wxpage/error";
		}
		
		
		/**
		 * orderid 查询订单信息，取到订单应支付金额
		 */
		EduOrder eduOrder = eduCourseService.getOrCreatePreOrder(openId,courseId);
		
		model.addAttribute("order_id", eduOrder.getId());
		if(eduCoursePojo.getCourseFee().compareTo(BigDecimal.ZERO) == 0)
			return "wxpage/pre_freepay";
		
		if(eduOrder == null)
			throw new java.lang.RuntimeException("系统错误，无法创建订单");
		/**
		 * openid 判断该微信用户支付合法
		 */
		Map<String, Object> responsemap = new HashMap<String, Object>();
		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(APPID);
		unifiedorder.setMch_id(MCH_ID);
		unifiedorder.setOpenid(openId);									// 微信的唯一识别码
		unifiedorder.setTrade_type(TRADE_TYPE);			    // JSAPI，NATIVE，APP，WAP
		unifiedorder.setNotify_url(WECHAT_NOTIFY);			// 支付成功后微信异步通知url地址
		
		unifiedorder.setNonce_str(MD5.MD5Encode(UUID.randomUUID().toString()).toUpperCase());		// 一个随机参数，每次不能相同 
		// 用户支付商品的信息，微信用户可见。
		unifiedorder.setBody("园众学校在线教育网课："+eduCoursePojo.getCourseName());		
		// 商户支付单编码/订单号，该支付单编码只能使用一次。
		unifiedorder.setOut_trade_no(eduOrder.getId().toString());     	
		
		//course.getPreFee()
		unifiedorder.setTotal_fee(((eduOrder.getReceivableAmount().multiply(new BigDecimal(100))).intValue())+""); // 测试 1分用户应支付金额，单位分
		System.out.println("==========================");
		System.out.println(eduOrder.getId().toString());
		System.out.println(((eduOrder.getReceivableAmount().multiply(new BigDecimal(100))).intValue())+"");
		
		unifiedorder.setSpbill_create_ip("127.0.0.1");		// 用户真实IP
		
		
		
		
		UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder, PARTNER_KEY);
		String responsejson = PayUtil.generateMchPayJsRequestJson(unifiedorderResult.getPrepay_id(), APPID, PARTNER_KEY);
		responsemap.put("status", "1");
		responsemap.put("msg", "OK");
		responsemap.put("data", responsejson);
		
		System.out.println(responsejson);
		
		
		
		model.addAttribute("wxpaydata", responsejson);
		
		return "wxpage/pre_pay";
	}
	
	
	
	private String getWxOpenId(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		//从COOKIE里取openid,没有存储到COOKIE
		Cookie cookieopenid = CookieUtil.getCookieByName(request, "open_id");
		String openid = null;
		if(cookieopenid != null)
			openid = cookieopenid.getValue();
		
		System.out.println("cookie openid="+openid);
		
		if(StringUtils.isBlank(openid) && StringUtils.isNotBlank(code)){			
			//通过code取微信的openid	
			try {
				WxApiService wxApiService = (WxApiService)HessianFactoryUtil.getHessianObj(WxApiService.class);
				openid = wxApiService.getWxOpenid(WxOpenid, code);
				//openid = WXPubUtil.getSnsapiUserinfo(code);
				if(null!=openid){
					System.out.println("addCookie"+openid);
					Cookie cookie = new Cookie("open_id",openid);
					cookie.setMaxAge(60*60*24);//过期时间为24小时
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		return openid;
	}
	
	//================ 微信公众号支付账户===========================================
	public static String WxOpenid 					= "gh_4bd836e8b19a";//公众账号ID
	public static String APPID 					= "wxe5fa7d21e1a6dbf5";//公众账号ID
	public static String MCH_ID 				= "1493815972";//商户号
	public static String PARTNER_KEY 			= "SLC53B1FC8A641D48UH0D0522F472DDC";//微信商户平台管理里面的API秘钥
	public static String WECHAT_NOTIFY			= "http://edu.gdzqyz.cn/qmmtedu/wxpay/mch_notify.json";
	public static String TRADE_TYPE 			= "JSAPI";     //JSAPI，NATIVE，APP，WAP
//	public static String APICLIENT_CERT_PATH 	= "/opt/iddc/payment/apiclient_cert.p12";   //线上秘钥地址
	//================ 请求微信支付时返回的错误码 ===========================================
	public static String OK 		= "1";				//请求
	public static String FAIL 		= "0";				//请求接口失败
	public static String ERROR_300 	= "300";			//请求参数错误
	//================ 请求微信支付时返回的错误码 ===========================================


}
