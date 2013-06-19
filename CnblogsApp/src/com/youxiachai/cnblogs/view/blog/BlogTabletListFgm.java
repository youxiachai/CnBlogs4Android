package com.youxiachai.cnblogs.view.blog;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youxiachai.cnblogs.R;
import com.youxiachai.cnblogs.app.api.Api;
import com.youxiachai.cnblogs.control.adapter.BlogTableListAdpater;
import com.youxiachai.cnblogs.model.api.BlogListType;
import com.youxiachai.onexlistview.XMultiColumnListView;

public class BlogTabletListFgm extends Fragment {
	
	XMultiColumnListView mBlogLV;
	BlogTableListAdpater mBlogLVAdapter;

	BlogListType nowBlogType = BlogListType.normal;

	public static BlogTabletListFgm newInstance(BlogListType type) {
		BlogTabletListFgm blogListFgm = new BlogTabletListFgm();

		Bundle b = new Bundle();
		b.putSerializable(Api.Key.BLOGLISTTYPE, type);

		blogListFgm.setArguments(b);

		return blogListFgm;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fgm_bloglist, null);
		mBlogLV =  (XMultiColumnListView) v.findViewById(android.R.id.list);
		
		mBlogLV.setEmptyView(v.findViewById(android.R.id.empty));
		
		mBlogLVAdapter = new BlogTableListAdpater(getActivity(), mBlogLV);
		
		mBlogLV.setPullRefreshEnable(new IXListViewRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				mBlogLVAdapter.refresh(nowBlogType);
			}
		});
		
		
		mBlogLV.setPullLoadEnable(new IXListViewLoadMore() {
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				
				int page = mBlogLVAdapter.getCount() / 20 + 1;
				
				mBlogLVAdapter.loadmore(page, 20, nowBlogType);
			}
		});
		
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		nowBlogType = (BlogListType) getArguments().getSerializable(
				Api.Key.BLOGLISTTYPE);
		mBlogLVAdapter.refresh(nowBlogType);
	}
}
