package com.youxiachai.cnblogs.app.api;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.youxiachai.ajax.ICallback;
import com.youxiachai.cnblogs.model.api.NewsList;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/**
 * @author youxiachai
 * @date   2013-6-19
 */
public class ApiService extends Service {
	
	AQuery req;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ICallback<NewsList> newsListCallback = new ICallback<NewsList>() {
		
		@Override
		public void onSuccess(NewsList result, Enum<?> type, AjaxStatus status) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onError(int code, String message) {
			// TODO Auto-generated method stub
			
		}
	};
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		if(intent != null){
			if(req == null){
				req = new AQuery(this);
			}
			Bundle reqBundle = intent.getExtras();
			
			if(reqBundle.getString(Api.Path.NEWSRECOMMEND) != null){
				ApiRequest.getNewsList(reqBundle, req, newsListCallback);
			}
			
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	public static void startService(Activity act, Bundle querymap){
		Intent i = new Intent();
		i.setClass(act, ApiService.class);
		i.putExtras(querymap);
		act.startService(i);
	}

}
