package com.ajie.api.ip.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.api.ApiInvokeException;
import com.ajie.api.ApiLog;
import com.ajie.api.ApiUrl;
import com.ajie.api.ip.IpQueryApi;
import com.ajie.api.ip.IpQueryVo;
import com.ajie.api.ip.utils.CNTable;
import com.ajie.api.ip.utils.CNTable.Wrap;
import com.ajie.api.ip.utils.Citys;
import com.ajie.chilli.common.ResponseResult;
import com.ajie.chilli.http.HttpInvoke;
import com.ajie.chilli.http.Parameter;
import com.ajie.chilli.http.exception.InvokeException;
import com.ajie.chilli.remote.RemoteCmd;
import com.ajie.chilli.remote.exception.RemoteException;
import com.ajie.chilli.utils.common.JsonUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * ip查询服务实现，如需调用含参数的接口，则需要调用set方法传入参数，如：使用gaodeKey，则在实例中需要手动set gaodeKey
 *
 * @author niezhenjie
 *
 */
public class IpQueryApiImpl implements IpQueryApi {
	private static final Logger logger = LoggerFactory
			.getLogger(IpQueryApiImpl.class);

	/** 远程命令服务 */
	private RemoteCmd cmd;

	/** 高德查询key */
	private String gaodeKey;

	private String ipstackAccessKey;

	synchronized public void setRemoteCmd(RemoteCmd cmd) {
		if (null != this.cmd) {
			return;// 设置了值不能在改变，防止一个准备线程查询时另一个线程修改了值
		}
		this.cmd = cmd;
	}

	synchronized public void setGaodeKey(String key) {
		if (null != gaodeKey) {
			return;// 设置了值不能在改变，防止一个线程准备查询时另一个线程修改了值
		}
		this.gaodeKey = key;
	}

	synchronized public void setIpstackAccessKey(String key) {
		if (null != ipstackAccessKey) {
			return;// 设置了值不能在改变，防止一个线程准备查询时另一个线程修改了值
		}
		this.ipstackAccessKey = key;
	}

	@Override
	public IpQueryVo query(String ip) throws ApiInvokeException {
		if (logger.isTraceEnabled()) {
			logger.trace("调用淘宝api查询ip：" + ip);
		}
		HttpInvoke http = HttpInvoke.getInstance(Collections
				.singletonList(ApiUrl.IP_QUERY_TAOBAO));
		try {
			ResponseResult response = http.invoke("ip",
					Parameter.valueOf("ip", ip));
			if (logger.isTraceEnabled()) {
				ApiLog.trace("query", ApiUrl.IP_QUERY_TAOBAO, response
						.getData().toString(), this);
			}
			return response.getData(IpQueryVo.class);

		} catch (InvokeException e) {
			logger.error("查询失败，1s后重试");
			return IpQueryVo._nil;
		}
	}

	@Override
	public IpQueryVo queryByCmd(String ip) throws ApiInvokeException {
		if (logger.isTraceEnabled()) {
			logger.trace("命令行查询ip：" + ip);
		}
		String url = ApiUrl.IP_QUERY_TAOBAO + "?ip=" + ip;
		try {
			String ret = cmd.cmd("curl " + url);
			if (logger.isTraceEnabled()) {
				ApiLog.trace("queryByCmd", url, ret, this);
			}
			ret = handleRespose(ret);
			if (logger.isTraceEnabled()) {
				logger.trace("ip结果：" + ret);
			}
			if (null == ret) {
				return IpQueryVo._nil;
			}
			ResponseResult res = JsonUtils.toBean(ret, ResponseResult.class);
			if (null == res) {
				return IpQueryVo._nil;
			}
			IpQueryVo vo = res.getData(IpQueryVo.class);
			if (null == vo)
				return IpQueryVo._nil;
			vo = new IpQueryVo.IpQueryVoBuilder(vo).setCode(res.getCode())
					.build();
			return vo;
		} catch (RemoteException e) {
			logger.error("ip查询失败，ip：" + ip, e);
		}
		return new IpQueryVo();
	}

	@Override
	public IpQueryVo queryByGaode(String ip) throws ApiInvokeException {
		if (logger.isTraceEnabled()) {
			logger.trace("调用高德api查询ip：" + ip);
		}
		HttpInvoke http = HttpInvoke.getInstance(Collections
				.singletonList(ApiUrl.IP_QUERY_GAODE));
		Map<String, String> param = new HashMap<String, String>();
		param.put("ip", ip);
		param.put("output", "json");
		param.put("key", gaodeKey);
		try {
			ResponseResult result = http.invoke("",
					Parameter.valueOf("ip", ip),
					Parameter.valueOf("output", "json"),
					Parameter.valueOf("key", gaodeKey));
			if (logger.isTraceEnabled()) {
				ApiLog.trace("queryByGaode", ApiUrl.IP_QUERY_GAODE, result
						.getData().toString(), this);
			}
			JSONObject json = result.getData(JSONObject.class);
			IpQueryVo vo = new IpQueryVo.IpQueryVoBuilder()
					.setCode(json.getInteger("status"))
					.setProvince(json.getString("province"))
					.setCity(json.getString("city")).build();
			return vo;
		} catch (InvokeException e) {
			logger.error("ip查询失败，ip：" + ip, e);
			return IpQueryVo._nil;
		}
	}

	@Override
	public IpQueryVo queryByIpstack(String ip) throws ApiInvokeException {
		if (logger.isTraceEnabled()) {
			logger.trace("调用Ipstack API查询ip：" + ip);
		}
		HttpInvoke http = HttpInvoke.getInstance(Collections
				.singletonList(ApiUrl.IP_QUERY_IPSTACK));
		Map<String, String> param = new HashMap<String, String>();
		param.put("access_key", ipstackAccessKey);
		try {
			ResponseResult result = http.invoke(ip,
					Parameter.valueOf("access_key", ipstackAccessKey));
			JSONObject json = result.getData(JSONObject.class);
			if (logger.isTraceEnabled()) {
				ApiLog.trace("queryByIpstack", ApiUrl.IP_QUERY_IPSTACK + "/"
						+ ip, json.toString(), this);
			}
			IpQueryVo.IpQueryVoBuilder build = new IpQueryVo.IpQueryVoBuilder()
					.setIp(json.getString("ip")).setCountry(
							json.getString("country_name"));

			Wrap province = CNTable.getWrap(json.getString("region_code"),
					false);
			if (null != province) {
				build.setProvince(province.getName());
			} else {
				build.setProvince(json.getString("region_code"));
			}
			Wrap city = Citys.getCity(json.getString("city"));
			if (null != city) {
				build.setCity(city.getName());
			} else {
				build.setCity(json.getString("city"));
			}
			return build.build();
		} catch (InvokeException e) {
			logger.error("ip查询失败，ip：" + ip, e);
		}
		return IpQueryVo._nil;
	}

	@Override
	public IpQueryVo query(String ip, int provider) throws ApiInvokeException {
		if (PROVIDER_TAOBAO.getId() == provider) {
			return query(ip);
		} else if (PROVIDER_GAODE.getId() == provider) {
			return queryByGaode(ip);
		} else if (PROVIDER_CMD.getId() == provider) {
			return queryByCmd(ip);
		} else if (PROVIDER_IPSTACK.getId() == provider) {
			return queryByIpstack(ip);
		} else {
			throw new ApiInvokeException("未知接口商");
		}
	}

	@Override
	public void injectGaodeKey(String key) {
		this.setGaodeKey(key);

	}

	@Override
	public void injectRemoteCmd(RemoteCmd cmd) {
		this.setRemoteCmd(cmd);
	}

	@Override
	public void injectIpStackAccessKey(String key) {
		this.setIpstackAccessKey(key);
	}

	/**
	 * 将处理结果处理成json
	 * 
	 * @param ret
	 * @return
	 */
	private String handleRespose(String ret) {
		if (null == ret)
			return "{}";
		int left = ret.indexOf("{");
		int right = ret.lastIndexOf("}");
		if (left == -1 || right == -1)
			return "{}";
		return ret.substring(left, right + 1);
	}

	public static void main(String[] args) {
	}

}
