package com.ajie.api.weixin;


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
public interface WeixinApi {

	/**
	 * 获取access token
	 * 
	 * @param appId
	 *            公众号id
	 * @param screet
	 *            密钥
	 * @return
	 */
	String getAccessToken(String appId, String screet);

	/**
	 * 获取js ticket
	 * 
	 * @param token
	 *            accesstoken
	 * @return
	 */
	String getJsTicket(String token);

}
