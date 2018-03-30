package com.RestAPInew;

import java.util.Date;

import javax.json.bind.annotation.JsonbProperty;



public class PointBean {
	
   private String po;

   public PointBean() {}

public PointBean(String po) {
	super();
	this.po = po;
}

@JsonbProperty("DATE")
public String getPo() {
	return po.replace("-", "");
}

public void setPo(String po) {
	this.po = po;
}
	
}
