package com.youxiachai.cnblogs.app.api.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.androidquery.util.XmlDom;
import com.youxiachai.cnblogs.model.api.NewsList;
import com.youxiachai.cnblogs.model.bean.News;

public class NewsListParser implements Transformer {
	final String ENTRY_TAG = "entry";// 主标记
	final String ENTRY_ID_TAG = "id";// 编号标记
	final String ENTRY_TITLE_TAG = "title";// 标题标记
	final String ENTRY_SUMMARY_TAG = "summary";// 简介标记
	final String ENTRY_PUBLISHED_TAG = "published";// 发表时间标记
	final String ENTRY_LINK_TAG = "link";// 实际链接地址
	final String ENTRY_DIGG_TAG = "diggs";// 推荐次数
	final String ENTRY_VIEW_TAG = "views";// 查看次数
	final String ENTRY_COMMENTS_TAG = "comments";// 评论次数
	final String ENTRY_URL_TAG = "link";// 实际网址标签
	final String ENTRY_URL_ATTRIBUTE_TAG = "href";// 网址属性标签

	private ArrayList<News> listNews;// 对象集合
	private News newsItem;// 单个对象
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T transform(String url, Class<T> type, String encoding,
			byte[] data, AjaxStatus status) {
		// TODO 时间格式化
		try {
			listNews = new ArrayList<News>();
			XmlDom newsEntries = new XmlDom(data);
			
			List<XmlDom> entries = newsEntries.tags(ENTRY_TAG);
			for (XmlDom tempDom : entries) {
				newsItem = new News();
				//编号
				newsItem.SetNewsId(Integer.parseInt(tempDom.text(ENTRY_ID_TAG)));
				
				//标题
				newsItem.SetNewsTitle(tempDom.text(ENTRY_TITLE_TAG));
				
				//摘要
				newsItem.SetSummary(tempDom.text(ENTRY_SUMMARY_TAG));
			
				//发布时间
//				newsItem.SetAddTime(addTime)
				
				listNews.add(newsItem);
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		NewsList newsList = new NewsList();
		newsList.listNews = listNews;
		return (T) newsList;
	}

}
