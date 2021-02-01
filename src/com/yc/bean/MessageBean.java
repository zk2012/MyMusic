package com.yc.bean;

import java.sql.Date;

public class MessageBean {
	private int id; //内容id
	private int uid; //用户id
	private int mid; // 音乐id
	private String message; // 发布内容
	private String time;  //发布时间
	private String uname;  // 用户名
	private String uphoto;  // 用户图片
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUphoto() {
		return uphoto;
	}
	public void setUphoto(String uphoto) {
		this.uphoto = uphoto;
	}
	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", uid=" + uid + ", mid=" + mid + ", message=" + message + ", time=" + time
				+ ", uname=" + uname + ", uphoto=" + uphoto + "]";
	}
	
	
	
	
}
