package com.yc.bean;

public class MusicBean {	
     public int mid;            //音乐ID
     public String musicname;   //音乐名
     public String musicer;     //歌手名
     public String mphoto;      //歌手海报
	
     public MusicBean() {
    	 
     }

     public MusicBean(int mid, String musicname, String musicer, String mphoto) {
		super();
		this.mid = mid;
		this.musicname = musicname;
		this.musicer = musicer;
		this.mphoto = mphoto;
	}

	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMusicname() {
		return musicname;
	}
	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}
	public String getMusicer() {
		return musicer;
	}
	public void setMusicer(String musicer) {
		this.musicer = musicer;
	}
	public String getMphoto() {
		return mphoto;
	}
	public void setMphoto(String mphoto) {
		this.mphoto = mphoto;
	}



	@Override
	public String toString() {
		return "MusicBean [mid=" + mid + ", musicname=" + musicname + ", musicer=" + musicer + ", mphoto=" + mphoto
				+ "]";
	}
     
	
     
}
