package com.mikedg.android.fullscreentest;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikedg.android.fullscreentest.FullscreenChecker.FullScreenChangeListener;

public class SoftButtonService extends Service {
	ImageView mImageView;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
//		addViewResizeTest();
		FullscreenChecker  fsc = new FullscreenChecker(this, (WindowManager) getSystemService("window"));
		fsc.addView();
		fsc.setFullScreenChangeListener(new FullScreenChangeListener() {
			
			@Override
			public void onScreenChange(boolean isFullscreen) {
				Toast.makeText(SoftButtonService.this, "" + isFullscreen, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void addViewResizeTest() {
		View tv = new View(this) {

			@Override
			protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
					int scrollY, int scrollRangeX, int scrollRangeY,
					int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
				// TODO Auto-generated method stub
				return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
						scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
			}

			@Override
			protected void onAttachedToWindow() {
				// TODO Auto-generated method stub
				super.onAttachedToWindow();
			}

			@Override
			protected void onConfigurationChanged(Configuration newConfig) {
				// TODO Auto-generated method stub
				super.onConfigurationChanged(newConfig);
			}

			@Override
			protected void onDetachedFromWindow() {
				// TODO Auto-generated method stub
				super.onDetachedFromWindow();
			}

			@Override
			protected void onDisplayHint(int hint) {
				// TODO Auto-generated method stub
				super.onDisplayHint(hint);
			}

			@Override
			protected void onLayout(boolean changed, int left, int top,
					int right, int bottom) {
				// TODO Auto-generated method stub
				Log.d("loc", "layout request size top - bottom: " + (top - bottom));
				
				int[] loc = new int[2];
				this.getLocationOnScreen(loc);

				Log.d("loc", "lay before: " + loc[0] + "    " + loc[1]);
				super.onLayout(changed, left, top, right, bottom);
				this.getLocationOnScreen(loc);
				Log.d("loc", "lay after: " + loc[0] + "    " + loc[1]);
				this.getHeight();Log.d("loc", "height:"+this.getHeight());
			}

			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				// TODO Auto-generated method stub
				Log.d("loc", "height spec: " + heightMeasureSpec);
				
				int[] loc = new int[2];
				this.getLocationOnScreen(loc);

				Log.d("loc", "before: " + loc[0] + "    " + loc[1]);
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
				this.getLocationOnScreen(loc);
				Log.d("loc", "after: " + loc[0] + "    " + loc[1]);
				
				this.getHeight();Log.d("loc", "height:"+this.getHeight());
				
			}

			@Override
			protected void onSizeChanged(int w, int h, int oldw, int oldh) {
				// TODO Auto-generated method stub
				super.onSizeChanged(w, h, oldw, oldh);
			}

			@Override
			protected void onVisibilityChanged(View changedView, int visibility) {
				// TODO Auto-generated method stub
				super.onVisibilityChanged(changedView, visibility);
			}

			@Override
			public void onWindowFocusChanged(boolean hasWindowFocus) {
				// TODO Auto-generated method stub
				super.onWindowFocusChanged(hasWindowFocus);
			}

			@Override
			protected void onWindowVisibilityChanged(int visibility) {
				// TODO Auto-generated method stub
				super.onWindowVisibilityChanged(visibility);
			}
			
		};
//		tv.setBackgroundColor(0xffff0000);
		
//		WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams(5,
//				5,
//				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, //Seems to stop getting once we switch apps
////				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY, //Does not appear to allow anything
////				WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG, //Permission denied for this set
////				WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, //shows but shortly goes to 0's
//
////				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
////				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | //NEWWWW blocks view from gettimng picked too, grrrr
// 				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//				WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR, //onmeasure does not get called multiple itmes with this :( and
////				WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, //onMeasure will get called on fullscreen change if this is set :/ maybe link 2 views for this? one to have location checked... other for notifier?
//				//Might only ever show same x,y
//				PixelFormat.TRANSLUCENT);
		//Try width/height checks?
		WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, //Seems to stop getting once we switch apps
//				WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY, //Does not appear to allow anything
//				WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG, //Permission denied for this set
//				WindowManager.LayoutParams.TYPE_SYSTEM_ERROR, //shows but shortly goes to 0's

//				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | //NEWWWW blocks view from gettimng picked too, grrrr
 				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//				WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR, //onmeasure does not get called multiple itmes with this :( and
				WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, //onMeasure will get called on fullscreen change if this is set :/ maybe link 2 views for this? one to have location checked... other for notifier?
				//Might only ever show same x,y
				PixelFormat.TRANSLUCENT);
//		
//		trip inScreen on a view to let another decor view view know to check its size?
//			we want the decor view to change size, hopefully that happens
		
		localLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
		
		WindowManager localWindowManager = (WindowManager) getSystemService("window");
		localWindowManager.addView(tv, localLayoutParams);
	}
}