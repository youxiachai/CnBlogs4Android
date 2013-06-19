package com.youxiachai.cnblogs.app.api.parser;

import org.xml.sax.SAXException;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.androidquery.util.XmlDom;
import com.youxiachai.cnblogs.model.bean.News;

/**
 * @author youxiachai
 * @date   2013-6-19
 */
public class NewsParser implements Transformer {
	
	final String ENTRY_TITLE_TAG = "Title";// 标题标记
	final String ENTRY_SOURCENAME_TAG = "SoruceName";
	final String ENTRY_CONTENT_TAG = "Content";// 主标记
	final String ENTRY_IMAGE_URL = "ImageUrl";// 图片标记
	final String ENTRY_PREVNEWS = "PrevNews";
	final String ENTRY_NEXTNEWS = "NextNews";
	final String ENTRY_COMMENTCOUNT = "CommentCount";

	@Override
	public <T> T transform(String url, Class<T> type, String encoding,
			byte[] data, AjaxStatus status) {
		// TODO Auto-generated method stub
		News news = new News();
		try {
			XmlDom bodyDom = new XmlDom(data);
			news.SetNewsTitle(bodyDom.text(ENTRY_TITLE_TAG));
			news.setSourceName(bodyDom.text(ENTRY_SOURCENAME_TAG));
			news.SetNewsContent(bodyDom.text(ENTRY_CONTENT_TAG));
			news.SetNewsId(Integer.valueOf(bodyDom.text(ENTRY_PREVNEWS)) + 1);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (T) news;
	}

}
