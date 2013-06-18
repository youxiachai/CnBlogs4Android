package com.youxiachai.cnblogs.app;

import com.youxiachai.api.ApiCommon;

import android.app.Application;

public class CnblogsApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		//set api host string 
		ApiCommon.setApiHost("http://wcf.open.cnblogs.com");
		
	}
}
