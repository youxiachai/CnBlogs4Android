package com.youxiachai.cnblogs.control.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;
import com.youxiachai.ajax.ICallback;
import com.youxiachai.cnblogs.R;
import com.youxiachai.cnblogs.app.api.Api;
import com.youxiachai.cnblogs.app.api.ApiRequest;
import com.youxiachai.cnblogs.model.api.BlogList;
import com.youxiachai.cnblogs.model.api.BlogListType;
import com.youxiachai.cnblogs.model.bean.Blog;
import com.youxiachai.onexlistview.XStickyListHeadersView;

/**
 * @author youxiachai
 * @date   2013-6-19
 */
public class BlogPhoneListAdapter extends AbsBlogListAdapter implements StickyListHeadersAdapter{
	
	XStickyListHeadersView mBlogPhoneLV;

	public BlogPhoneListAdapter(Activity act, XStickyListHeadersView listView){
		super(act);
		mBlogPhoneLV = listView;
		mBlogPhoneLV.setAdapter(this);
	}
	
	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView =  mInflater.inflate(R.layout.item_lv_bloglist_head, null);
		}
		
		AQuery headQy = mQuery.recycle(convertView);
		
		headQy.id(R.id.blogFace).image(blogList.get(position).GetAvator());
		headQy.id(R.id.blogAuthor).text(blogList.get(position).GetAuthor());
		//TODO 还有一个时间变量
		headQy.id(R.id.blogTime).text(blogList.get(position).GetUpdateTime());
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/* 不要吐槽不用holder 模式..性能提升其实就那么点,晚点在搞吧...
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.item_lv_bloglist, null);
		}
		
		AQuery aq = mQuery.recycle(convertView);
		aq.id(R.id.blogTitle).text(blogList.get(position).GetBlogTitle());
		aq.id(R.id.blogSummary).text(blogList.get(position).GetSummary());
		return convertView;
	}
	
	public void refresh(BlogListType blogType){
		Bundle reqBundle = new Bundle();
		reqBundle.putInt(Api.Key.PAGE, 1);
		reqBundle.putInt(Api.Key.COUNT, 20);
		reqBundle.putSerializable(Api.Key.BLOGLISTTYPE, blogType);
		ApiRequest.getBlogList(reqBundle, mQuery, new ICallback<BlogList>() {
			
			@Override
			public void onSuccess(BlogList result, Enum<?> type, AjaxStatus status) {
				blogList = result.blogList;
				notifyDataSetInvalidated();
				mBlogPhoneLV.stopRefresh("");
			}
			
			@Override
			public void onError(int code, String message) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void loadmore(int page, int count,BlogListType blogType){
		Bundle reqBundle = new Bundle();
		reqBundle.putInt(Api.Key.PAGE, page);
		reqBundle.putInt(Api.Key.COUNT, count);
		reqBundle.putSerializable(Api.Key.BLOGLISTTYPE, blogType);
		ApiRequest.getBlogList(reqBundle, mQuery, new ICallback<BlogList>() {
			
			@Override
			public void onSuccess(BlogList result, Enum<?> type, AjaxStatus status) {
		
				
				blogList.addAll(result.blogList);
				notifyDataSetChanged();
				mBlogPhoneLV.stopLoadMore();
			}
			
			@Override
			public void onError(int code, String message) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
