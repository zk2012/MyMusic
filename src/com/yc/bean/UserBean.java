package com.yc.bean;

public class UserBean {
	public int uuid;   //用户ID
	public String uname; //用户名
	public String upwd; //用户密码
	public String uphoto; //用户图片
	public int states; //用户状态

	
	public UserBean(){
		
	}
	
	
	
	public UserBean(int uuid, String uname, String upwd, String uphoto, int states) {
		super();
		this.uuid = uuid;
		this.uname = uname;
		this.upwd = upwd;
		this.uphoto = uphoto;
		this.states = states;
	}



	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUphoto() {
		return uphoto;
	}
	public void setUphoto(String uphoto) {
		this.uphoto = uphoto;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	@Override
	public String toString() {
		return "UserBean [uuid=" + uuid + ", uname=" + uname + ", upwd=" + upwd + ", uphoto=" + uphoto + ", states="
				+ states + "]";
	}



}
