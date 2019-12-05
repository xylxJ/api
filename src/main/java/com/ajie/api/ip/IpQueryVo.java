package com.ajie.api.ip;

import com.ajie.chilli.utils.common.JsonUtils;

/**
 * ip查询结果封装
 *
 * @author niezhenjie
 *
 */
public class IpQueryVo {

	// {"code":0,"data":{"ip":"","country":"","area":"","region":"","city":"","county":"",
	// "isp":"","country_id":"","area_id":"","region_id":"","city_id":"","county_id":"","isp_id":""}}}
	/** 状态码 */
	protected int code;
	/** ip */
	protected String ip;
	/** 国家 */
	protected String country;
	/** 地区 */
	protected String area;
	/** 地区 */
	protected String region;
	/** 城市 */
	protected String city;
	/** 县 */
	protected String county;
	/** 供应商 */
	protected String isp;
	/** 国家id */
	protected String country_id;
	/** 地区id */
	protected String area_id;
	/** 地区id */
	protected String region_id;
	/** 城市id */
	protected String city_id;
	/** 县id */
	protected String count_id;
	/** 供应商id */
	protected String isp_id;
	/** 省 */
	protected String province;

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public String getCount_id() {
		return count_id;
	}

	public void setCount_id(String count_id) {
		this.count_id = count_id;
	}

	public String getIsp_id() {
		return isp_id;
	}

	public void setIsp_id(String isp_id) {
		this.isp_id = isp_id;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public static IpQueryVo _nil = new IpQueryVoBuilder().setArea("")
			.setCity("").setCountry("").setCounty("").setIp("").setIsp("")
			.setProvince("").setRegion("").build();

	public static class IpQueryVoBuilder {
		IpQueryVo vo = null;

		public IpQueryVoBuilder() {
			vo = new IpQueryVo();
		}

		public IpQueryVoBuilder(IpQueryVo vo) {
			this.vo = vo;
		}

		public IpQueryVoBuilder setCode(int code) {
			vo.code = code;
			return this;
		}

		public IpQueryVoBuilder setIp(String ip) {
			vo.ip = ip;
			return this;
		}

		public IpQueryVoBuilder setCountry(String country) {
			vo.country = country;
			return this;
		}

		public IpQueryVoBuilder setArea(String area) {
			vo.area = area;
			return this;
		}

		public IpQueryVoBuilder setRegion(String region) {
			vo.region = region;
			return this;
		}

		public IpQueryVoBuilder setCity(String city) {
			vo.city = city;
			return this;
		}

		public IpQueryVoBuilder setCounty(String county) {
			vo.county = county;
			return this;
		}

		public IpQueryVoBuilder setIsp(String isp) {
			vo.isp = isp;
			return this;
		}

		public IpQueryVoBuilder setCountryId(String countryId) {
			vo.country_id = countryId;
			return this;
		}

		public IpQueryVoBuilder setAreaId(String areaId) {
			vo.area_id = areaId;
			return this;
		}

		public IpQueryVoBuilder setIspId(String ispId) {
			vo.isp_id = ispId;
			return this;
		}

		public IpQueryVoBuilder setProvince(String province) {
			vo.province = province;
			return this;
		}

		public IpQueryVoBuilder setRegionId(String regionId) {
			vo.region_id = regionId;
			return this;
		}

		public IpQueryVoBuilder setCityId(String cityId) {
			vo.city_id = cityId;
			return this;
		}

		public IpQueryVoBuilder setCountId(String countId) {
			vo.count_id = countId;
			return this;
		}

		public IpQueryVo build() {
			return vo;
		}

	}

	public int getCode() {
		return code;
	}

	public String getIp() {
		return ip;
	}

	public String getCountry() {
		return country;
	}

	public String getArea() {
		return area;
	}

	public String getRegion() {
		return region;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}

	public String getIsp() {
		return isp;
	}

	public String getCountryId() {
		return country_id;
	}

	public String getAreaId() {
		return area_id;
	}

	public String getRegionId() {
		return region_id;
	}

	public String getCityId() {
		return city_id;
	}

	public String getCountId() {
		return count_id;
	}

	public String getIspId() {
		return isp_id;
	}

	public String getProvince() {
		return province;
	}

	public String toString() {
		return JsonUtils.toJSONString(this);
	}

}
