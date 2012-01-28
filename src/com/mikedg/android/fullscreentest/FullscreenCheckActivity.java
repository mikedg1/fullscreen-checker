/*
 *  Copyright (C) 2011 Michael DiGiovanni <mike@mikedg.com>
 *  This file is part of Android Fullscreen Check.
 *
 *  Android Fullscreen Check is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Android Fullscreen Check is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Android Fullscreen Check.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mikedg.android.fullscreentest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

public class FullscreenCheckActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.text).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
		                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
			}
		});
        startService(new Intent(this, SoftButtonService.class));
    }
}