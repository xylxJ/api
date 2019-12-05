package com.ajie.api;

/**
 * api的调用链接统一放在这里，方便管理
 *
 * @author niezhenjie
 *
 */
public class ApiUrl {
	/** 获取secretToken的api链接 */
	public static final String SECRET_TOKEN_BASE_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	/** 获取jsapi_ticket的api链接（jsapi_ticket在调用微信js-sdk时使用） */
	public static final String JSAPI = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
	/** 淘宝查询IP API */
	public static final String IP_QUERY_TAOBAO = "http://ip.taobao.com/service/getIpInfo.php/;connect_timeout=10;read_timeout=15;";
	/** 高德查询IP API */
	public static final String IP_QUERY_GAODE = "https://restapi.amap.com/v3/ip";
	/** 高德查询IP API */
	public static final String IP_QUERY_IPSTACK = "http://api.ipstack.com";

}
