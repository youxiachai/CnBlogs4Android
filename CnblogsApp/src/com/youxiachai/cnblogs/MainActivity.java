package com.youxiachai.cnblogs;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.youxiachai.actiontitlebar.ActionTitleBar;

public class MainActivity extends FragmentActivity {
	ActionTitleBar actionbar;
	private SlidingMenu menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
		setActionBar();
		
		setMenu();
	}
	
	private void setActionBar(){
		actionbar = ActionTitleBar.getActionBar(this);

		actionbar.setNavigationMode(ActionTitleBar.NAVIGATION_MODE_STANDARD);
		
		actionbar.setHomeUpListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				menu.toggle();
			}
		});
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
		
		ListView lv = (ListView) menu.findViewById(R.id.listCategory);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
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
