package com.yc.util;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

public class FileUploadUtil {
	public static String uploadPath = "../uphoto";
	private static final String ALLOWEDLIST = "gif,jpg,png,jpeg";
	// private static final String DENIEDLIST = "ext,bat,jsp,html";
	private static final int MAXFILESIZE = 10*1024*1024;
	private static final int TOTALMAXFILESIZE = 100*1024*1024;


	@SuppressWarnings({ "unchecked", "deprecation" })
	public Map<String, String> uploads(PageContext pageContext) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		SmartUpload su = new SmartUpload();

		// 初始化上传组件
		su.initialize(pageContext);

		// 设置参数
		su.setMaxFileSize(MAXFILESIZE); // 单个文件最大大小
		su.setTotalMaxFileSize(TOTALMAXFILESIZE); // 一次上传的总文件大小
		su.setAllowedFilesList(ALLOWEDLIST); // 允许上传的文件格式后缀
		// su.setDeniedFilesList(DENIEDLIST); // 不允许上传的文件格式后缀
		su.setCharset("utf-8"); // 上传数据的编码集
		su.upload(); // 开始上传

		// 获取上传的参数信息
		Request rq = su.getRequest();

		// 获取参数
		Enumeration<String> enus = rq.getParameterNames();
		String name = null;
		while(enus.hasMoreElements()) {
			name = enus.nextElement();
			map.put(name, rq.getParameter(name));
		}

		Files files = su.getFiles();
		if (files != null && files.getCount() > 0) { // 说明用户确实上传了文件
			// 循环取出用户上传的文件
			Collection<File> fls = files.getCollection();

			String fileName = null;
			String path = null;
			String fieldName = null;
			String str = null;
			String temp = null;
			for (File fl : fls) {
				if (!fl.isMissing()) {
					temp = fl.getFieldName();
					if (!StringUtil.isNull(fieldName)) { // 如果这个变量不为空，说明这不是他第一次读取图片了
						if (!temp.equals(fieldName)) { // 说明是另外的文件域
							map.put(fieldName, str);
							str = null;
							fieldName = temp;
						}
					} else {
						fieldName = temp;
					}

					// 将这个文件存到files文件夹
					fileName = uploadPath+ "/" + new Date().getTime() + "_" + fl.getFileName();
					if (str == null) {
						str = fileName;
					} else {
						str += ";" + fileName;
					}

					// 指定文件的存储路径
					path = pageContext.getRequest().getRealPath("/") + fileName;
					// 保存
					fl.saveAs(path, SmartUpload.SAVE_PHYSICAL);
				}
			}
			map.put(fieldName, str);
		}
		return map;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Map<String, String> upload(PageContext pageContext) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		SmartUpload su = new SmartUpload();

		// 初始化上传组件
		su.initialize(pageContext);

		// 设置参数
		su.setMaxFileSize(MAXFILESIZE); // 单个文件最大大小
		su.setTotalMaxFileSize(TOTALMAXFILESIZE); // 一次上传的总文件大小
		su.setAllowedFilesList(ALLOWEDLIST); // 允许上传的文件格式后缀
		// su.setDeniedFilesList(DENIEDLIST); // 不允许上传的文件格式后缀
		su.setCharset("utf-8"); // 上传数据的编码集
		su.upload(); // 开始上传

		// 获取上传的文件
		Files files = su.getFiles();
		if (files != null && files.getCount() > 0) { // 说明用户确实上传了文件
			// 循环取出用户上传的文件
			Collection<File> fls = files.getCollection();

			String fileName = null;
			String path = null;
			String fieldName = null;
			String str = null;
			for (File fl : fls) {
				if (!fl.isMissing()) {
					fieldName = fl.getFieldName();
					// 将这个文件存到files文件夹
					str = uploadPath+ "/" + new Date().getTime() + "_" + fl.getFileName();

					// 指定文件的存储路径
					path = pageContext.getRequest().getRealPath("/") + str;
					// 保存
					fl.saveAs(path, SmartUpload.SAVE_PHYSICAL);
				}
			}
			map.put("fileName", fileName);
			map.put(fieldName, str);
		}
		return map;
	}
}
