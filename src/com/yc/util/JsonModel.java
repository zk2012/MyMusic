package com.yc.util;

import java.io.Serializable;

/**
 * 返回网页信息
 * @author 
 *
 */
public class JsonModel implements Serializable{

	private static final long serialVersionUID = 3097158528049817734L;
	
	private Integer code; // 1:成功	0:失败  错误码
	private Object obj;	  // 返回的对象
	private String errorMsg;  //返回的提示信息
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", obj=" + obj + ", errorMsg=" + errorMsg + "]";
	}
	
}
