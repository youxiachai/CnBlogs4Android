package com.youxiachai.cnblogs.app.api;

import android.os.Bundle;

import com.androidquery.AQuery;
import com.androidquery.util.XmlDom;
import com.youxiachai.ajax.ICallback;
import com.youxiachai.ajax.NetCallback;
import com.youxiachai.ajax.NetOption;
import com.youxiachai.cnblogs.app.api.parser.BlogListParser;
import com.youxiachai.cnblogs.app.api.parser.NewsListParser;
import com.youxiachai.cnblogs.app.api.parser.NewsParser;
import com.youxiachai.cnblogs.model.api.BlogList;
import com.youxiachai.cnblogs.model.api.BlogListType;
import com.youxiachai.cnblogs.model.api.NewsList;
import com.youxiachai.cnblogs.model.bean.News;

/**
 * @author youxiachai
 * @date 2013-6-19
 */
public class ApiRequest {

	public static void getNewsList(Bundle dataMap, AQuery req,
			ICallback<NewsList> callback) {

		// 类型转换
		NewsListParser newsListParser = new NewsListParser();

		// 设置类
		NetOption no = new NetOption(ApiUrl.getNewsRecommend(dataMap), null);

		// 缓存一个小时
		no.expire = 60 * 60 * 1000;

		NetCallback<NewsList> ajaxCallback = new NetCallback<NewsList>(
				NewsList.class, no, callback);

		req.transformer(newsListParser).ajax(ajaxCallback);

	}
	
	

	public static void getBlogBody(Bundle dataMap, AQuery req,
			ICallback<XmlDom> callback) {
		// 设置类
		NetOption no = new NetOption(ApiUrl.getBlogBody(dataMap), null);
		// 缓存一个小时
		no.expire = 60 * 60 * 1000;

		NetCallback<XmlDom> ajaxCallback = new NetCallback<XmlDom>(
				XmlDom.class, no, callback);

		req.ajax(ajaxCallback);
	}

	public static void getNewsBody(Bundle dataMap, AQuery req,
			ICallback<News> callback) {
		// 设置类
		NetOption no = new NetOption(ApiUrl.getNewsBody(dataMap), null);
		// 缓存一个小时
		no.expire = 60 * 60 * 1000;

		NetCallback<News> ajaxCallback = new NetCallback<News>(
				News.class, no, callback);
		NewsParser newsParser = new NewsParser();
		req.transformer(newsParser).ajax(ajaxCallback);
	}

	public static void getBlogList(Bundle dataMap, AQuery req,
			ICallback<BlogList> callback) {
		BlogListType blogType = (BlogListType) dataMap
				.getSerializable(Api.Key.BOLGLISTTYPE);
		BlogListParser blogListParser = new BlogListParser();
		// 设置类
		NetOption no = new NetOption(ApiUrl.getBlogList(dataMap, blogType), blogType);
		// 缓存一个小时
		no.expire = 60 * 60 * 1000;

		NetCallback<BlogList> ajaxCallback = new NetCallback<BlogList>(
				BlogList.class, no, callback);

		req.transformer(blogListParser).ajax(ajaxCallback);

	}

}
