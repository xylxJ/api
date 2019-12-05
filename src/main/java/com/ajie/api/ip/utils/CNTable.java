package com.ajie.api.ip.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址英文转中文对照表
 *
 * @author niezhenjie
 *
 */
public class CNTable {
	/** 广东省 */
	static public final Wrap GUANGDONG = Wrap.valueOf("gd", "广东省", "guangdong");
	/** 北京市 */
	static public final Wrap BEIJING = Wrap.valueOf("bj", "北京市", "beijing");
	/** 上海市 */
	static public final Wrap SHANGHAI = Wrap.valueOf("sh", "上海市", "shanghai");
	/** 天津市 */
	static public final Wrap TIAJING = Wrap.valueOf("tj", "天津市", "tianjin");
	/** 重庆市 */
	static public final Wrap CHOGNQING = Wrap.valueOf("cq", "重庆市", "chongqing");
	/** 河南省 */
	static public final Wrap HENAN = Wrap.valueOf("ha", "河南省", "henan");
	/** 河北省 */
	static public final Wrap HEBEI = Wrap.valueOf("he", "河北省", "hebei");
	/** 山西省 */
	static public final Wrap SHANXI = Wrap.valueOf("sx", "山西省", "shanxi");
	/** 山东省 */
	static public final Wrap SHANDONG = Wrap.valueOf("sd", "山东省", "shandong");
	/** 陕西省 */
	static public final Wrap SHAANXI = Wrap.valueOf("sn", "陕西省", "shaanxi");
	/** 黑龙江省 */
	static public final Wrap HEILONGJINAG = Wrap.valueOf("hlj", "黑龙江省", "heilongjiang");
	/** 吉林省 */
	static public final Wrap JILIN = Wrap.valueOf("jl", "吉林省", "jilin");
	/** 辽宁省 */
	static public final Wrap LIAONING = Wrap.valueOf("ln", "辽宁省", "liaoning");
	/** 江苏省 */
	static public final Wrap JIAGNSU = Wrap.valueOf("js", "江苏省", "jiangsu");
	/** 浙江省 */
	static public final Wrap ZHEJIANG = Wrap.valueOf("zj", "浙江省", "zhejiang");
	/** 安徽省 */
	static public final Wrap ANHUI = Wrap.valueOf("ah", "安徽省", "anhui");
	/** 福建省 */
	static public final Wrap FUJIAN = Wrap.valueOf("fj", "福建省", "fujian");
	/** 江西省 */
	static public final Wrap JIANGXI = Wrap.valueOf("jX", "江西省", "jiangxi");
	/** 湖北省 */
	static public final Wrap HUBEI = Wrap.valueOf("hb", "湖北省", "hubei");
	/** 湖南省 */
	static public final Wrap HUNAN = Wrap.valueOf("hn", "湖南省", "hunan");
	/** 海南省 */
	static public final Wrap HAINAN = Wrap.valueOf("hi", "海南省", "hainan");
	/** 四川省 */
	static public final Wrap SICHUAN = Wrap.valueOf("sc", "四川省", "sichuan");
	/** 贵州省 */
	static public final Wrap GUIZHOU = Wrap.valueOf("gz", "贵州省", "guizhou");
	/** 云南省 */
	static public final Wrap YUNNAN = Wrap.valueOf("yn", "云南省", "yunnan");
	/** 甘肃省 */
	static public final Wrap GANSU = Wrap.valueOf("gs", "甘肃省", "gansu");
	/** 青海省 */
	static public final Wrap QINGHAI = Wrap.valueOf("qh", "青海省", "qinghai");
	/** 台湾省 */
	static public final Wrap TAIWAN = Wrap.valueOf("tw", "台湾省", "taiwan");
	/** 内蒙古自治区 */
	static public final Wrap NAMENGGU = Wrap.valueOf("nm", "内蒙古自治区", "neimenggu");
	/** 广西壮族自治区 */
	static public final Wrap GUANGXI = Wrap.valueOf("gx", "广西壮族自治区", "guangxi");
	/** 西藏自治区 */
	static public final Wrap XIZANG = Wrap.valueOf("xz", "西藏自治区", "xizang");
	/** 宁夏回族自治区 */
	static public final Wrap NINGXIA = Wrap.valueOf("nx", "宁夏回族自治区", "ningxia");
	/** 新疆维吾尔自治区 */
	static public final Wrap XINJINAG = Wrap.valueOf("xj", "新疆维吾尔自治区", "xinjiang");
	/** 香港特别行政区 */
	static public final Wrap XIANGGANG = Wrap.valueOf("xg", "香港特别行政区", "xianggang");
	/** 澳门特别行政区 */
	static public final Wrap AOMEN = Wrap.valueOf("am", "澳门特别行政区", "aoman");

	static public final List<Wrap> CHINA_PROVINCES = new ArrayList<Wrap>();

	static {
		CHINA_PROVINCES.add(GUANGDONG);
		CHINA_PROVINCES.add(BEIJING);
		CHINA_PROVINCES.add(SHANGHAI);
		CHINA_PROVINCES.add(TIAJING);
		CHINA_PROVINCES.add(CHOGNQING);
		CHINA_PROVINCES.add(HENAN);
		CHINA_PROVINCES.add(HEBEI);
		CHINA_PROVINCES.add(SHANXI);
		CHINA_PROVINCES.add(SHANDONG);
		CHINA_PROVINCES.add(SHAANXI);
		CHINA_PROVINCES.add(HEILONGJINAG);
		CHINA_PROVINCES.add(JILIN);
		CHINA_PROVINCES.add(LIAONING);
		CHINA_PROVINCES.add(JIAGNSU);
		CHINA_PROVINCES.add(ZHEJIANG);
		CHINA_PROVINCES.add(ANHUI);
		CHINA_PROVINCES.add(FUJIAN);
		CHINA_PROVINCES.add(JIANGXI);
		CHINA_PROVINCES.add(HUBEI);
		CHINA_PROVINCES.add(HUNAN);
		CHINA_PROVINCES.add(HAINAN);
		CHINA_PROVINCES.add(SICHUAN);
		CHINA_PROVINCES.add(GUIZHOU);
		CHINA_PROVINCES.add(YUNNAN);
		CHINA_PROVINCES.add(GANSU);
		CHINA_PROVINCES.add(QINGHAI);
		CHINA_PROVINCES.add(TAIWAN);
		CHINA_PROVINCES.add(NAMENGGU);
		CHINA_PROVINCES.add(GUANGXI);
		CHINA_PROVINCES.add(XIZANG);
		CHINA_PROVINCES.add(NINGXIA);
		CHINA_PROVINCES.add(XINJINAG);
		CHINA_PROVINCES.add(XIANGGANG);
		CHINA_PROVINCES.add(AOMEN);
	}

	/**
	 * 根据关键字查找对应的结果，
	 * 
	 * @param key
	 * @param caseSensitive
	 *            true区分大小写，false不区分
	 * @return
	 */
	public static Wrap getWrap(String key, boolean caseSensitive) {
		if (null == key)
			return null;
		for (Wrap wrap : CHINA_PROVINCES) {
			if (caseSensitive) {
				if (wrap.name.equals(key) || wrap.pyName.equals(key) || wrap.shortName.equals(key)) {
					return wrap;
				}
			} else {
				if (wrap.name.equalsIgnoreCase(key) || wrap.pyName.equalsIgnoreCase(key)
						|| wrap.shortName.equalsIgnoreCase(key)) {
					return wrap;
				}
			}
		}
		return null;
	}

	public static class Wrap {
		/** 拼音缩写，小写 */
		String shortName;
		/** 名称，一般是中文名称 */
		String name;
		/** 全拼音 */
		String pyName;

		public static Wrap valueOf(String shortName, String name, String pyName) {
			Wrap wrap = new Wrap();
			wrap.shortName = shortName;
			wrap.name = name;
			wrap.pyName = pyName;
			return wrap;
		}

		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPyName() {
			return pyName;
		}

		public void setPyName(String pyName) {
			this.pyName = pyName;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("{shortName:").append(shortName).append(",");
			sb.append("name:").append(name).append(",");
			sb.append("pyName:").append(pyName).append("}");
			return sb.toString();
		}
	}
}
