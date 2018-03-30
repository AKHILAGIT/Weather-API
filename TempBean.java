package com.RestAPInew;

import java.util.Date;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class TempBean {
	public TempBean(){
		
		
	}

	public TempBean(String dt, String tmax, String tmin) {
		super();
		this.dt = dt;
		Tmax = tmax;
		Tmin = tmin;
	}
	private String dt;
	private String Tmax;
	private String Tmin;
	@JsonbProperty("DATE")
	public String getDt() {
		return dt.replace("-", "");
	}
	@JsonbProperty("DATE")
	public void setDt(String dt) {
		this.dt = dt;
	}
	@JsonbProperty("TMAX")
	public String getTmax() {
		return Tmax;
	}
	@JsonbProperty("TMAX")
	public void setTmax(String tmax) {
		Tmax = tmax;
	}
	@JsonbProperty("TMIN")
	public String getTmin() {
		return Tmin;
	}
	@JsonbProperty("TMIN")
	public void setTmin(String tmin) {
		Tmin = tmin;
	}
	
	
	
}
