package com.youxiachai.cnblogs.control.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.youxiachai.ajax.ICallback;
import com.youxiachai.cnblogs.R;
import com.youxiachai.cnblogs.app.api.Api;
import com.youxiachai.cnblogs.app.api.ApiRequest;
import com.youxiachai.cnblogs.model.api.NewsList;
import com.youxiachai.cnblogs.model.bean.News;
import com.youxiachai.onexlistview.XMultiColumnListView;

/**
 * @author youxiachai
 * @date   2013-6-19
 */
public class NewsListAdapter extends BaseAdapter {
	
	ArrayList<News> listNews = new ArrayList<News>();
	Activity mAct;
	LayoutInflater mInfater;
	AQuery mQuery;
	XMultiColumnListView mNewsListView;
	
	public NewsListAdapter(Activity act, XMultiColumnListView newsListView) {
		mAct = act;
		mInfater = act.getLayoutInflater();
		mNewsListView = newsListView;
		mQuery = new AQuery(act);
		
		mNewsListView.setAdapter(getListAdapter());
	}
	
	public void loadmore(int page, int count){
		Bundle reqBundle = new Bundle();
		reqBundle.putInt(Api.Key.PAGE, page);
		reqBundle.putInt(Api.Key.COUNT, count);
		ApiRequest.getNewsList(reqBundle, mQuery, new ICallback<NewsList>() {

			@Override
			public void onSuccess(NewsList result, Enum<?> type,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				listNews.addAll(result.listNews);
				notifyDataSetChanged();
				
				mNewsListView.stopLoadMore();
			}

			@Override
			public void onError(int code, String message) {
				// TODO Auto-generated method stub

			}
		});
		
	}
	
	private ListAdapter getListAdapter(){
		return this;
	}
	
	public void refresh(int page, int count){
		Bundle reqBundle = new Bundle();
		reqBundle.putInt(Api.Key.PAGE, page);
		reqBundle.putInt(Api.Key.COUNT, count);
		ApiRequest.getNewsList(reqBundle, mQuery, new ICallback<NewsList>() {

			@Override
			public void onSuccess(NewsList result, Enum<?> type,
					AjaxStatus status) {
				// TODO Auto-generated method stub
				listNews = result.listNews;
				notifyDataSetInvalidated();
				mNewsListView.stopRefresh("");
			}

			@Override
			public void onError(int code, String message) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listNews.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = mInfater.inflate(R.layout.item_lv_newslist, null);
		}
		
		AQuery aq = mQuery.recycle(convertView);
		
		aq.id(R.id.newsTitle).text(listNews.get(position).GetNewsTitle());
		aq.id(R.id.newsSummary).text(listNews.get(position).GetSummary());
		
		return convertView;
	}
	
	

}
