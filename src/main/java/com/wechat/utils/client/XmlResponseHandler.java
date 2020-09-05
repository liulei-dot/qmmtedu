/**  
 * Copyright © 2015公司名字. 惠车科技.
 *
 * @Title: XmlResponseHandler.java
 * @Prject: huiche.weixin
 * @Package: com.huiche.utils.client
 * @Description: TODO
 * @author: Liu Wen
 * @date: 2015年11月30日 下午9:59:09
 * @version: V1.0  
 */
package com.wechat.utils.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.wechat.util.XMLConverUtil;

/**
 * @ClassName: XmlResponseHandler
 * @Description: TODO
 * @author: Liu Wen
 * @date: 2015年11月30日 下午9:59:09
 */
public class XmlResponseHandler {

	private static Map<String, ResponseHandler<?>> map = new HashMap<String, ResponseHandler<?>>();

	@SuppressWarnings("unchecked")
	public static <T> ResponseHandler<T> createResponseHandler(final Class<T> clazz){
		if(map.containsKey(clazz.getName())){
			return (ResponseHandler<T>)map.get(clazz.getName());
		}else{
			ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
				@Override
				public T handleResponse(HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
	                if (status >= 200 && status < 300) {
	                    HttpEntity entity = response.getEntity();
	                    String str = EntityUtils.toString(entity);
	                    Header contentType = response.getEntity().getContentType();
	                    if(contentType!=null&&contentType.toString().matches(".*[uU][tT][fF]-8$")){
	                    	return XMLConverUtil.convertToObject(clazz,str);
	                    }else{
	                    	return XMLConverUtil.convertToObject(clazz,new String(str.getBytes("iso-8859-1"),"utf-8"));
	                    }
	                } else {
	                    throw new ClientProtocolException("Unexpected response status: " + status);
	                }

				}
			};
			map.put(clazz.getName(), responseHandler);
			return responseHandler;
		}
	}
}
