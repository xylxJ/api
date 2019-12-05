package com.ajie.api.ip;

import com.ajie.api.ApiInvokeException;
import com.ajie.chilli.common.KVpair;
import com.ajie.chilli.remote.RemoteCmd;

/**
 * ip查询服务
 *
 * @author niezhenjie
 *
 */
public interface IpQueryApi {

	/** ip查询供应商 -- 淘宝 */
	public static final KVpair PROVIDER_TAOBAO = KVpair.valueOf("淘宝", 1);
	/** ip查询供应商 -- 高德 */
	public static final KVpair PROVIDER_GAODE = KVpair.valueOf("高德", 2);
	/** ip查询供应商 -- 服务器命令行调用淘宝查询（解决httpclient查询结果成功率低问题） */
	public static final KVpair PROVIDER_CMD = KVpair.valueOf("服务器", 3);
	/** ip查询供应商 -- ipstack */
	public static final KVpair PROVIDER_IPSTACK = KVpair.valueOf("ipstack", 4);

	/**
	 * 调用淘宝api查询ip，但是成功率很低
	 * 
	 * @param ip
	 * @return
	 * @throws ApiInvokeException
	 */
	IpQueryVo query(String ip) throws ApiInvokeException;

	/**
	 * 使用服务器命令调用淘宝的api查询
	 * 
	 * @param ip
	 * @return
	 * @throws ApiInvokeException
	 */
	IpQueryVo queryByCmd(String ip) throws ApiInvokeException;

	/**
	 * 使用高德api查询（高效，但信息较少，而且每天只能调用100次）
	 * 
	 * @param ip
	 * @return
	 * @throws ApiInvokeException
	 */
	IpQueryVo queryByGaode(String ip) throws ApiInvokeException;

	/**
	 * 使用Ipstack API查询（高效，每天可调用10000次，但返回信息是英文）
	 * 
	 * @param ip
	 * @return
	 * @throws ApiInvokeException
	 */
	IpQueryVo queryByIpstack(String ip) throws ApiInvokeException;

	/**
	 * 指定接口商查询
	 * 
	 * @param ip
	 * @param provinde
	 * @return
	 * @throws ApiInvokeException
	 */
	IpQueryVo query(String ip, int provider) throws ApiInvokeException;

	/**
	 * 设置高德key
	 * 
	 * @param key
	 */
	void injectGaodeKey(String key);

	/**
	 * 注入远程接口
	 * 
	 * @param cmd
	 */
	void injectRemoteCmd(RemoteCmd cmd);

	/**
	 * 注入ipstack Access Key
	 * 
	 * @param ipstackAccessKey
	 */
	void injectIpStackAccessKey(String key);
}
