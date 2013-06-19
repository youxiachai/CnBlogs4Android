package com.youxiachai.cnblogs.view.news;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.huewu.pla.lib.internal.PLA_AdapterView;
import com.huewu.pla.lib.internal.PLA_AdapterView.OnItemClickListener;
import com.youxiachai.cnblogs.R;
import com.youxiachai.cnblogs.control.adapter.NewsListAdapter;
import com.youxiachai.onexlistview.XMultiColumnListView;

public class NewsListFgm extends Fragment {
	AQuery req;
	
	XMultiColumnListView mNewsListView;
	NewsListAdapter mNewsListAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View newsView = inflater.inflate(R.layout.fgm_newlist, null);
		
		mNewsListView = (XMultiColumnListView) newsView.findViewById(android.R.id.list);
		mNewsListView.setEmptyView(newsView.findViewById(android.R.id.empty));
		mNewsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(PLA_AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "+po" + position, Toast.LENGTH_SHORT).show();
			}
		});
		
	
		mNewsListView.setPullRefreshEnable(new IXListViewRefreshListener() {
			
			@Override
			public void onRefresh() {
				mNewsListAdapter.refresh(1, 10);
			}
		});
		
		mNewsListView.setPullLoadEnable(new IXListViewLoadMore() {
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				int page = mNewsListAdapter.getCount() / 10 + 1;
				mNewsListAdapter.loadmore(page, 10);
			}
		});
		
		mNewsListAdapter = new NewsListAdapter(getActivity(), mNewsListView);
		
		return newsView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		req = new AQuery(getActivity());
		mNewsListAdapter.refresh(1, 10);
	}
	

	
	@Override
	public void onResume() {
		super.onResume();
		
		
	}
}
