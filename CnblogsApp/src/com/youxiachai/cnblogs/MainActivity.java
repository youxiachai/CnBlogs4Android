package com.youxiachai.cnblogs;

import android.app.v4.ActionBar.Tab;
import android.app.v4.ActionBar.TabListener;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.youxiachai.actiontitlebar.ActionTitleBar;
import com.youxiachai.cnblogs.model.api.BlogListType;
import com.youxiachai.cnblogs.view.blog.BlogPhoneListFgm;
import com.youxiachai.cnblogs.view.blog.BlogTabletListFgm;
import com.youxiachai.cnblogs.view.news.NewsListFgm;

public class MainActivity extends FragmentActivity {
	ActionTitleBar actionbar;
	private SlidingMenu menu;
	
	public final static int NEWSRECOM = 0;
	public final static int D10 = 1;
	public final static int H48 = 2;
	public final static int SITEBLOG = 3;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBar();
		setContentFgm(new NewsListFgm());
	}
	
	private void setContentFgm(Fragment fgm){
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.replace(android.R.id.content, fgm);
		ft.commit();
	}
	
	private void setActionBar(){
		actionbar = ActionTitleBar.getActionBar(this);
		
		if(getResources().getBoolean(R.bool.isPhone)){
			actionbar.setNavigationMode(ActionTitleBar.NAVIGATION_MODE_STANDARD);
			setMenu();
			actionbar.setHomeUpListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					menu.toggle();
				}
			});
		}else{
			actionbar.setDisplayShowTitleEnabled(false);
			actionbar.setNavigationMode(ActionTitleBar.NAVIGATION_MODE_TABS);
			
			Tab tab1 = actionbar.newTab().setText("推荐新闻").setTabListener(new TabListener() {
				
				@Override
				public void onTabUnselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onTabSelected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					setContentFgm(new NewsListFgm());
				}
				
				@Override
				public void onTabReselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					
				}
			});
			Tab tab2 = actionbar.newTab().setText("10天内阅读排行").setTabListener(new TabListener() {
				
				@Override
				public void onTabUnselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
				
				}
				
				@Override
				public void onTabSelected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					setContentFgm(BlogTabletListFgm.newInstance(BlogListType.d10));
				}
				
				@Override
				public void onTabReselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					
				}
			});
			Tab tab3 = actionbar.newTab().setText("48小时阅读排行").setTabListener(new TabListener() {
				
				@Override
				public void onTabUnselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onTabSelected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					setContentFgm(BlogTabletListFgm.newInstance(BlogListType.h48));
				}
				
				@Override
				public void onTabReselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					
				}
			});
			Tab tab4 = actionbar.newTab().setText("首页博客").setTabListener(new TabListener() {
				
				@Override
				public void onTabUnselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onTabSelected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					setContentFgm(BlogTabletListFgm.newInstance(BlogListType.normal));
				}
				
				@Override
				public void onTabReselected(Tab tab, FragmentTransaction ft) {
					// TODO Auto-generated method stub
					
				}
			});
			
			actionbar.addTab(tab1);
			actionbar.addTab(tab2);
			actionbar.addTab(tab3);
			actionbar.addTab(tab4);
			
		}
		
		
	}
	
	private String getResString(int position){
		return getResources().getStringArray(R.array.menuList)[position];
	}
	
	/**
	 * 设置右侧的slide menu
	 */
	private void setMenu() {
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		
		menu.setBehindCanvasTransformer(new CanvasTransformer() {
			
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				// TODO Auto-generated method stub
				float scale = (float) (percentOpen*0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
			}
		});
		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		menu.setMenu(R.layout.left_menu);
		actionbar.setTitle(getResString(NEWSRECOM));
		ListView lv = (ListView) menu.findViewById(R.id.listCategory);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case NEWSRECOM:
					setContentFgm(new NewsListFgm());
					actionbar.setTitle(getResString(NEWSRECOM));
					break;
				case D10:
					setContentFgm(BlogPhoneListFgm.newInstance(BlogListType.d10));
					actionbar.setTitle(getResString(D10));
					break;
				case H48:
					setContentFgm(BlogPhoneListFgm.newInstance(BlogListType.h48));
					actionbar.setTitle(getResString(H48));
					break;
				case SITEBLOG:
					setContentFgm(BlogPhoneListFgm.newInstance(BlogListType.normal));
					actionbar.setTitle(getResString(SITEBLOG));
					break;
				default:
					break;
				}
				menu.toggle();
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
