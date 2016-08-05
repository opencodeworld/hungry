package com.minisea.cookbook.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.minisea.cookbook.util.InjectView;
import com.minisea.cookbook.util.Injector;
import com.minisea.cookbook.R;
import com.zdp.aseo.content.AseoZdpAseo;

public class WelcomeActivity extends Activity {
	@InjectView(R.id.welcome_start_order)
	private TextView start;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		Injector.get(this).inject();//init views
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (WelcomeActivity.this,HomePageActivity.class);	
				AseoZdpAseo.initFinalTimer(WelcomeActivity.this, AseoZdpAseo.INSERT_TYPE);
				startActivity(intent);			
			}
		});

	}


}
