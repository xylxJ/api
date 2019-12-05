package com.ajie.api.weixin;

import com.ajie.chilli.utils.common.JsonUtils;
import com.ajie.chilli.utils.common.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 调用微信api响应结果
 *
 * @author niezhenjie
 *
 */
public class WeixinApiResponse {
	/** 调用成功状态码 */
	public final static int SUC_CODE = 0;
	/** 调用结果无返回码 */
	public final static int WITHOUT_CODE = -100;

	/** 调用成功状态值 */
	public final static String SUC_MSG = "ok";
	/** 返回结果的字串 */
	private String source;
	/** 返回码 */
	private int code;
	/** 是否是成功的状态 */
	private boolean isOk;
	/** 错误提示 */
	private String msg;
	/** 返回结果转换成json */
	private JSONObject json;

	public WeixinApiResponse(String source) {
		this.source = source;
		analysis();
	}

	public void analysis() {
		if (StringUtils.isEmpty(source)) {
			return;
		}
		JSONObject json = JsonUtils.toBean(source, JSONObject.class);
		this.json = json;
		try {
			int code = json.getInteger("errcode");
			this.code = code;
		} catch (Exception e) {
			this.code = WITHOUT_CODE;
		}
		try {
			String msg = json.getString("errmsg");
			this.msg = msg;
		} catch (Exception e) {
			this.msg = "ok";
		}
		if (code == SUC_CODE || null == msg) {
			isOk = true;
		} else {
			isOk = false;
		}
	}

	public boolean isOk() {
		return isOk;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * 获取指定key的值
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (null == json)
			return null;
		try {
			String val = json.getString(key);
			return val;
		} catch (Exception e) {
		}
		return null;

	}
}
