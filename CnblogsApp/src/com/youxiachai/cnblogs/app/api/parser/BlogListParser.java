package com.youxiachai.cnblogs.app.api.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.androidquery.util.XmlDom;
import com.youxiachai.cnblogs.model.api.BlogList;
import com.youxiachai.cnblogs.model.bean.Blog;

/**
 * @author youxiachai
 * @date   2013-6-19
 */
public class BlogListParser implements Transformer {
	final String ENTRY_TAG = "entry";// 主标记
	final String ENTRY_ID_TAG = "id";// 编号标记
	final String ENTRY_TITLE_TAG = "title";// 标题标记
	final String ENTRY_SUMMARY_TAG = "summary";// 简介标记
	final String ENTRY_PUBLISHED_TAG = "published";// 发表时间标记
	final String ENTRY_UPDATED_TAG = "updated";// 更新时间标记
	final String ENTRY_AUTHOR_NAME_TAG = "name";// 发表者名称
	final String ENTRY_USER_NAME_TAG="blogapp";//作者用户名
	final String ENTRY_AUTHOR_URL_TAG = "uri";// 发表者主页
	final String ENTRY_LINK_TAG = "link";// 实际链接地址
	final String ENTRY_DIGG_TAG = "diggs";// 推荐次数
	final String ENTRY_VIEW_TAG = "views";// 查看次数
	final String ENTRY_COMMENTS_TAG = "comments";// 评论次数
	final String ENTRY_AVATOR_TAG = "avatar";// 头像地址
	final String ENTRY_URL_TAG = "link";// 实际网址标签
	final String ENTRY_URL_ATTRIBUTE_TAG = "href";// 网址属性标签

	private ArrayList<Blog> listBlog;// 对象集合
	private Blog blogItem;// 单个对象
	/* (non-Javadoc)
	 * @see com.androidquery.callback.Transformer#transform(java.lang.String, java.lang.Class, java.lang.String, byte[], com.androidquery.callback.AjaxStatus)
	 */
	@Override
	public <T> T transform(String url, Class<T> type, String encoding,
			byte[] data, AjaxStatus status) {
		// TODO Auto-generated method stub
		
		listBlog = new ArrayList<Blog>();
		
		try {
			XmlDom blogEntries = new XmlDom(data);
			
			List<XmlDom> entries = blogEntries.tags(ENTRY_TAG);
			
			for (XmlDom xmlDom : entries) {
				blogItem = new Blog();
				
				blogItem.SetBlogId(Integer.parseInt(xmlDom.text(ENTRY_ID_TAG)));
				
				blogItem.SetBlogTitle(xmlDom.text(ENTRY_TITLE_TAG));
				
				blogItem.SetSummary(xmlDom.text(ENTRY_SUMMARY_TAG));
				
				//作者
				String autor = xmlDom.tag("author").text("name");
				blogItem.SetAuthor(autor);
				
				//头像
				String face = xmlDom.tag("author").text("avatar");
				blogItem.SetAvator(face);
				
				//发布时间
				String date = xmlDom.text(ENTRY_UPDATED_TAG);
				blogItem.SetUpdateTime(date);
				
				listBlog.add(blogItem);
				
			}
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BlogList blogList = new BlogList();
		blogList.blogList = listBlog;
		
		return (T) blogList;
	}

}
