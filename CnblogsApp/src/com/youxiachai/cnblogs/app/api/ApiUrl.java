package com.youxiachai.cnblogs.app.api;

import android.os.Bundle;

import com.youxiachai.api.ApiCommon;
import com.youxiachai.cnblogs.model.api.BlogListType;


/**
 * @author youxiachai
 * @date   2013-6-18
 */
public final class ApiUrl {
	
	
	
	/**根据blog 类型获取blog 列表
	 * @param dataMap
	 * @param blogType
	 * @return
	 */
	public static String getBlogList(Bundle dataMap, BlogListType blogType){
		switch (blogType) {
		case normal:
			
			return getBlogNormal(dataMap);
		case d10:
			
			return getBlogTopD10(dataMap);
		case h48:

			return getBlogTopH48(dataMap);
		}
		
		return null;
	}
	
		
	/** 推荐新闻
	 * @param page
	 * @param count
	 * @return
	 */
	public static String getNewsRecommend(Bundle dataMap){
		int page = dataMap.getInt(Api.Key.PAGE);
		int count = dataMap.getInt(Api.Key.COUNT);
		return ApiCommon.getApiHost() + Api.Path.NEWSRECOMMEND  + page + "/" + count;
	}
	
	/**新闻内容
	 * @param id
	 * @return
	 */
	public static String getNewsBody(Bundle dataMap){
		int id =  dataMap.getInt(Api.Key.ID);
		return ApiCommon.getApiHost() + Api.Path.NEWBODY + id;
	}
	

	
	/** 48小时阅读排行
	 * @param count
	 * @return
	 */
	private static String getBlogTopH48(Bundle dataMap){
		int count = dataMap.getInt(Api.Key.COUNT);
		return ApiCommon.getApiHost() + Api.Path.TOPVIEW + count;
	}
	
	/**网页文章列表
	 * @param page
	 * @param count
	 * @return
	 */
	private static String getBlogNormal(Bundle dataMap){
		int page = dataMap.getInt(Api.Key.PAGE);
		int count = dataMap.getInt(Api.Key.COUNT);
		return ApiCommon.getApiHost() + Api.Path.BLOGINDEX + page + "/" + count;
	}
	
	/**10 天内推荐排行
	 * @param count
	 * @return
	 */
	private static String getBlogTopD10(Bundle dataMap){
		int count = dataMap.getInt(Api.Key.COUNT);
		return ApiCommon.getApiHost() + Api.Path.TOPRECOMMEND + count;
	}
	
	
	/**博客文章内容
	 * @param id
	 * @return
	 */
	public static String getBlogBody(Bundle dataMap){
		int id =  dataMap.getInt(Api.Key.ID);
		return ApiCommon.getApiHost() + Api.Path.BLOGBODY + id;
	}
	


	
}
