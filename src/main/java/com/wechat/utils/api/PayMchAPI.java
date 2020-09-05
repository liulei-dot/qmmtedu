package com.wechat.utils.api;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import com.wechat.bean.paymch.Unifiedorder;
import com.wechat.bean.paymch.UnifiedorderResult;
import com.wechat.util.MapUtil;
import com.wechat.util.SignatureUtil;
import com.wechat.util.XMLConverUtil;
import com.wechat.utils.client.LocalHttpClient;

/**
 * 微信支付 基于V3.X 版本
 */
public class PayMchAPI extends BaseAPI {

	/**
	 * 统一下单 请使用 payUnifiedorder(Unifiedorder unifiedorder,String key), 自动生成sign
	 * 
	 * @param unifiedorder
	 * @return
	 */
	@Deprecated
	public static UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder) {
		return payUnifiedorder(unifiedorder, null);
	}

	/**
	 * 统一下单
	 * 
	 * @param unifiedorder
	 * @param key
	 * @return
	 */
	public static UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder, String key) {
		Map<String, String> map = MapUtil.objectToMap(unifiedorder);
		if (key != null) {
			String sign = SignatureUtil.generateSign(map, key);
			unifiedorder.setSign(sign);
		}
		String unifiedorderXML = XMLConverUtil.convertToXML(unifiedorder);
		HttpUriRequest httpUriRequest = RequestBuilder
				.post()
				.setHeader(xmlHeader)
				.setUri(MCH_URI + "/pay/unifiedorder")
				.setEntity(new StringEntity(unifiedorderXML, Charset.forName("utf-8"))).build();
		return LocalHttpClient.executeXmlResult(httpUriRequest, UnifiedorderResult.class);
	}
}
