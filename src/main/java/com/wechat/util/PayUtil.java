package com.wechat.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.wechat.bean.pay.PayJsRequest;
import com.wechat.bean.pay.PayPackage;


public class PayUtil {

	/**
	 * 生成支付JS请求JSON
	 * 
	 * @param payPackage
	 * @param appId
	 * @param paternerKey
	 * @param paySignkey
	 *            appkey
	 * @return
	 */
	public static String generatePayJsRequestJson(PayPackage payPackage, String appId, String paternerKey, String paySignkey) {
		Map<String, String> mapP = MapUtil.objectToMap(payPackage);
		String package_ = SignatureUtil.generatePackage(mapP, paternerKey);
		PayJsRequest payJsRequest = new PayJsRequest();
		payJsRequest.setAppId(appId);
		payJsRequest.setNonceStr(UUID.randomUUID().toString());
		payJsRequest.setPackage_(package_);
		payJsRequest.setSignType("sha1");
		payJsRequest.setTimeStamp(System.currentTimeMillis() / 1000 + "");
		Map<String, String> mapS = MapUtil.objectToMap(payJsRequest, "signType", "paySign");
		String paySign = SignatureUtil.generatePaySign(mapS, paySignkey);
		payJsRequest.setPaySign(paySign);
		return ALJsonUtil.toJSONString(payJsRequest);
	}
	// MCH -------------------------------------------------
	/**
	 * (MCH)生成支付JS请求对象
	 * 
	 * @param prepay_id  预支付订单号
	 * @param appId
	 * @param key 商户支付密钥
	 * @return
	 */
	public static String generateMchPayJsRequestJson(String prepay_id, String appId, String key) {
		String package_ = "prepay_id=" + prepay_id;
		PayJsRequest payJsRequest = new PayJsRequest();
		payJsRequest.setAppId(appId);
		payJsRequest.setNonceStr(MD5.MD5Encode(UUID.randomUUID().toString()).toUpperCase());
		payJsRequest.setPackage_(package_);
		payJsRequest.setSignType("MD5");
		payJsRequest.setTimeStamp(System.currentTimeMillis() / 1000 + "");
		// @fantycool 提交修正bug
		// Map<String, String> mapS =
		// MapUtil.objectToMap(payJsRequest,"signType","paySign");
		Map<String, String> mapS = MapUtil.objectToMap(payJsRequest);
		String paySign = SignatureUtil.generateSign(mapS, key);
		payJsRequest.setPaySign(paySign);
		return ALJsonUtil.toJSONString(payJsRequest);
	}

}
