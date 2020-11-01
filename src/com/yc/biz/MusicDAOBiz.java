package com.yc.biz;

import java.util.List;

import com.yc.bean.MusicBean;

public interface MusicDAOBiz {

	
	/**
	 * 显示所有歌单
	 */
	public List<MusicBean> findByTrem(MusicBean t);
	
	public List<MusicBean> findByTrem(int uid);
}
