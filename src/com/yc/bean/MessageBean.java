package com.yc.bean;

public class MessageBean {
	private int id;
	private int uid;
	private int mid;
	private String message;
	private String time;
	
	
	@Override
	public String toString() {
		return "MessageBean [id=" + id + ", uid=" + uid + ", mid=" + mid + ", message=" + message + ", time=" + time
				+ "]";
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


	public MessageBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MessageBean(int id, int uid, int mid, String message, String time) {
		super();
		this.id = id;
		this.uid = uid;
		this.mid = mid;
		this.message = message;
		this.time = time;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + mid;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		MessageBean other = (MessageBean) obj;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (mid != other.mid)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}
	
	
	
}
