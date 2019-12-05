package com.ajie.api.ip.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.chilli.utils.common.StringUtils;

/**
 * 从citys.txt文件中获取city信息
 *
 * @author niezhenjie
 *
 */
public class Citys {
	private static final Logger logger = LoggerFactory.getLogger(Citys.class);

	/** 空格 */
	public static final String SPACE = " ";

	/**
	 * 根据key搜索city信息
	 * 
	 * @param key
	 *            可以是中文，可以是拼音全拼也可以是缩写，如广东|guangdong|gd，不区分大小写，如果拼音相同，
	 *            则只返回第一个匹配中的
	 * @return
	 */
	public static CNTable.Wrap getCity(String key) {
		if (StringUtils.isEmpty(key))
			return null;
		// 在api项目里运行或打成jar包部署时的情况跟
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("citys.txt");
		if (null != is) {
			try {
				return findByInputStream(is, key);
			} catch (IOException e) {
				logger.error("citys文件加载失败", e);
				return null;
			} finally {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		// 在eclipse maven环境中运行的情况
		// IpQueryApiImpl所在包路径/com/ajie/api/ip/impl
		URL url = Citys.class.getResource("");
		logger.info(url.getPath());
		InputStream inputStream = null;
		CNTable.Wrap wrap = null;
		try {
			// 打开IpQueryApiImpl所在的jar包api.jar
			JarURLConnection jar = (JarURLConnection) url.openConnection();
			JarFile jarFile = jar.getJarFile();
			// jar包下的所有文件
			Enumeration<JarEntry> jarEntrys = jarFile.entries();
			while (jarEntrys.hasMoreElements()) {
				JarEntry entry = jarEntrys.nextElement();
				String name = entry.getName();
				// citys.txt文件
				if ("citys.txt".equals(name)) {
					inputStream = jarFile.getInputStream(entry);
					wrap = findByInputStream(inputStream, key);
				}
			}
		} catch (IOException e) {
			logger.error("citys文件加载失败", e);
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (IOException e) {
			}
		}
		return wrap;
	}

	private static CNTable.Wrap findByInputStream(InputStream in, String key) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			// 先获得line以utf-8编码格式的流，再转换成utf-8字符串，
			line = new String(line.getBytes("utf-8"), "utf-8");
			if (line.toUpperCase().indexOf(key.toUpperCase()) > -1) {
				// 匹配上
				int idx = line.indexOf(SPACE);
				int lIdx = line.lastIndexOf(SPACE);
				// 中文名
				String city = line.substring(0, idx);
				// 全拼
				String pyCity = line.substring(idx, lIdx);
				// 拼音缩写
				String sPyCity = line.substring(lIdx);
				CNTable.Wrap wrap = new CNTable.Wrap();
				wrap.setName(city + "市");
				wrap.setPyName(pyCity);
				wrap.setShortName(sPyCity);
				return wrap;
			}
		}
		return null;
	}

}
