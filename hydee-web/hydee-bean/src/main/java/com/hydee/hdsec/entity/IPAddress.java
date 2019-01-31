package com.hydee.hdsec.entity;

import java.io.Serializable;

public class IPAddress implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ip;
	private String country;
	private String area;
	private String region;
	private String city;
	
	public IPAddress(){
		this.ip = "";
		this.country = "";
		this.area = "";
		this.region = "";
		this.city = "";
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return ip+":"+country+"-"+area+"-"+region+"-"+city;
	}
}
