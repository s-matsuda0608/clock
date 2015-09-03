package com.example.clock;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ScheduledExecutorService service;
	private Handler handler = new Handler();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final TextView textView = new TextView(this);
		textView.setText(new Date().toString());
		setContentView(textView);
		service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						textView.setText(new Date().toString());
					}
				});
			}
		}, 0, 500, TimeUnit.MILLISECONDS);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		service.shutdown();
	}
}