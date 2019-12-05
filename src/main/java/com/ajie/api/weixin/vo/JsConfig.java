package com.ajie.api.weixin.vo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.chilli.utils.Toolkits;
import com.ajie.chilli.utils.common.JsonUtils;

/**
 * 微信js调用需要的配置，注意，需要获得jsapi_ticket之后才就可以生成JS-SDK权限验证的签名了。<br>
 * wx.config({<br>
 * 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。<br>
 * debug: true,<br>
 * appId: '', // 必填，公众号的唯一标识<br>
 * timestamp: , // 必填，生成签名的时间戳<br>
 * nonceStr: '', // 必填，生成签名的随机串<br>
 * signature: '',// 必填，签名<br>
 * jsApiList: [] // 必填，需要使用的JS接口列表<br>
 * });
 *
 * @author niezhenjie
 *
 */
public class JsConfig {
	private static final Logger logger = LoggerFactory.getLogger(JsConfig.class);
	private String jsapiTicket;
	/**
	 * 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，
	 * 仅在pc端时才会打印
	 */
	private boolean debug;
	/** 公众号的唯一标识 */
	private String appid;
	/** 生成签名的时间戳 */
	private long timestamp;
	/** 生成签名的随机串 */
	private String nonceStr;
	/** 签名 */
	private String signature;
	/** 需要使用的JS接口列表 如：updateAppMessageShareData updateTimelineShareData */
	private List<String> jsApiList;

	public JsConfig(String appid, String jsapiTicket) {
		this.appid = appid;
		this.jsapiTicket = jsapiTicket;
	}

	/**
	 * 对url路径进行签名
	 * 
	 * @param url
	 */
	public void sign(String url) {
		if (null == url) {
			if (logger.isTraceEnabled())
				logger.trace("签名链接为空");
		}
		String nonceStr = getRand();
		// 这里是秒
		long timestamp = System.currentTimeMillis() / 1000;
		String signature = "";
		// 步骤1. 对所有待签名参数按照字段名的ASCII
		// 码从小到大排序（字典序）后，使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1：
		String param = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp="
				+ timestamp + "&url=" + url;
		// 步骤2. 对string1进行sha1签名，得到signature：
		MessageDigest crypt;
		try {
			crypt = MessageDigest.getInstance("SHA1");
			crypt.reset();
			crypt.update(param.getBytes());
			byte[] arr = crypt.digest();
			signature = Toolkits.byte2Hex(arr);
		} catch (NoSuchAlgorithmException e) {
			logger.error("签名失败", e);
		}

		this.nonceStr = nonceStr;
		this.timestamp = timestamp;
		this.signature = signature;
	}

	private String getRand() {
		// return Toolkits.genRandomStr(16);
		return UUID.randomUUID().toString();
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public List<String> getJsApiList() {
		return jsApiList;
	}

	public void setJsApiList(List<String> jsApiList) {
		this.jsApiList = jsApiList;
	}

	public String toString() {
		String str = null;
		try {
			str = JsonUtils.toJSONString(this);
		} catch (Exception e) {
		}
		if (null == str) {
			return super.toString();
		}
		return str;
	}

}
