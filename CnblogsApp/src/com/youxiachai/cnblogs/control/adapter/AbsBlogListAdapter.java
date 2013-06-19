package com.youxiachai.cnblogs.control.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.androidquery.AQuery;
import com.youxiachai.cnblogs.model.bean.Blog;

/**
 * @author youxiachai
 * @date   2013-6-19
 */
public abstract class AbsBlogListAdapter extends BaseAdapter {
	
	List<Blog> blogList = new ArrayList<Blog>();
	Activity mAct;
	LayoutInflater mInflater;
	AQuery mQuery;
	
	public AbsBlogListAdapter(Activity act){
		mAct = act;
		mInflater = act.getLayoutInflater();
		mQuery = new AQuery(act);
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return blogList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return blogList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	


	

}
