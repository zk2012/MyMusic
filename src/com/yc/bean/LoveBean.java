package com.yc.bean;

/**
 *  一个用户可以有多个我喜欢的音乐
 *  一个音乐也可以被多个用户所喜欢
 * @author Administrator
 *
 */
public class LoveBean {
	private int id;   // 我喜欢id
	private int uid;  // 用户id
	private int mid;  // 音乐id
	
	
	@Override
	public String toString() {
		return "LoveBean [id=" + id + ", uid=" + uid + ", mid=" + mid + "]";
	}


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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + mid;
		result = prime * result + uid;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoveBean other = (LoveBean) obj;
		if (id != other.id)
			return false;
		if (mid != other.mid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}


	public LoveBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LoveBean(int id, int uid, int mid) {
		super();
		this.id = id;
		this.uid = uid;
		this.mid = mid;
	}
	
	
	

}
