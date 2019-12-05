package com.ajie.api.weixin.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.api.ApiLog;
import com.ajie.api.ApiUrl;
import com.ajie.api.weixin.WeixinApi;
import com.ajie.api.weixin.WeixinApiResponse;
import com.ajie.chilli.utils.HttpClientUtil;

/**
 * 微信api调用<br>
 * 调用微信的api基本都需要使用access_token，access_token是动态改变的，没两小时就会失效，
 * 但是调用api获取access_token的次数是有限的 所以需要在服务器里做好缓存和定时刷新的措施
 * 获取access_token需要使用appid和screet
 * ,appid是已知的，secret是可以在微信公众平台生成，如果忘记了，可以重新生成，项目里也要做响应的改变
 *
 * @author niezhenjie
 *
 */
public class WeixinApiImpl implements WeixinApi {
	private static final Logger logger = LoggerFactory.getLogger(WeixinApiImpl.class);

	@Override
	public String getAccessToken(String appId, String screet) {
		String url = ApiUrl.SECRET_TOKEN_BASE_URL + "&appid=" + appId + "&secret=" + screet;
		String ret = null;
		try {
			ret = HttpClientUtil.doGet(url);
			if (logger.isTraceEnabled()) {
				ApiLog.trace("getAccessToken", url, ret, this);
			}
		} catch (IOException e) {
			logger.error("调用微信api刷新access_token失败，3s后尝试重试");
			try {
				Thread.sleep(3000);
				ret = HttpClientUtil.doGet(url);
			} catch (IOException e1) {
				logger.error("调用微信api刷新access_token失败,url=" + url, e1);
			} catch (InterruptedException e2) {
			}
		}
		if (null == ret)
			return null;
		WeixinApiResponse res = new WeixinApiResponse(ret);
		// 不能用WeixinApiResponse分析返回的信息，坑爹的微信，就不能统一一下返回的字段吗
		/*if (!res.isOk()) {
			logger.error("调用微信api刷新access_token失败", res.getErrorMsg());
			throw new ApiInvokeException("刷新access_token失败 " + res.getErrorMsg());
		}*/
		String token = res.getValue("access_token");
		if (null == token) {
			logger.error("调用微信api刷新access_token失败,errorcode=" + res.getCode() + ",errmsg="
					+ res.getMsg());
			return null;
		}
		return token;
	}

	@Override
	public String getJsTicket(String token) {
		String url = ApiUrl.JSAPI + token;
		String ret = null;
		try {
			ret = HttpClientUtil.doGet(url);
			if (logger.isTraceEnabled()) {
				ApiLog.trace("getJsTicket", url, ret, this);
			}
		} catch (IOException e) {
			logger.error("调用微信api刷新jsapi_ticket失败，1s后尝试重试");
			try {
				Thread.sleep(1000);
				ret = HttpClientUtil.doGet(url);
			} catch (IOException e1) {
				logger.error("调用微信api刷新jsapi_ticket失败", e1);
			} catch (InterruptedException e2) {
			}
		}
		if (logger.isTraceEnabled()) {
			logger.trace("JsTicket查询结果：" + ret);
		}
		WeixinApiResponse res = new WeixinApiResponse(ret);
		if (!res.isOk()) {
			logger.error("调用微信api刷新jsapi_ticket失败", res.getMsg());
		}
		String val = res.getValue("ticket");
		if (null == val) {
			return null;
		}
		return val;
	}

	public static void main(String[] args) {
		/*WeixinApiImpl api = new WeixinApiImpl();
		String token = api.getAccessToken();
		String js = api.getJsTicket(token);
		System.out.println(token);
		System.out.println(js);
		String nonceStr = UUID.randomUUID().toString();
		System.out.println(nonceStr);
		long timestamp = System.currentTimeMillis() / 1000;
		System.out.println(timestamp);
		String signature = "";
		// 步骤1. 对所有待签名参数按照字段名的ASCII
		// 码从小到大排序（字典序）后，使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1：
		String param = "jsapi_ticket=" + js + "&noncestr=" + nonceStr + "&timestamp=" + timestamp
				+ "&url=http://www.ajie18.top/ajie/blog/index.do";
		// 步骤2. 对string1进行sha1签名，得到signature：
		MessageDigest crypt = null;
		try {
			crypt = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crypt.reset();
		crypt.update(param.getBytes());
		byte[] arr = crypt.digest();
		signature = Toolkits.byte2Hex(arr);
		System.out.println(signature);*/
	}

}
