package com.linhnguyen.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	TextView tv;
	int x =5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		tv = (TextView) findViewById(R.id.textView1);
		Typeface Bl = Typeface.createFromAsset(getAssets(), "Blazed.ttf");
		tv.setTypeface(Bl);
		CountDownTimer cdt = new CountDownTimer(5000, 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				switch(x){
					case 5: tv.setText("Welcome"); x--; tv.setTextColor(Color.argb(200, 216, 32, 30)); break;
					case 4: tv.append(" To Learn");x--; tv.setTextColor(Color.argb(200, 255,
							131, 6)); break;
					case 3: tv.append(" English");x--; tv.setTextColor(Color.argb(200, 0,
							198, 99)); break;
					
					case 2:tv.setText("Let's Study Now!");
					tv.setTextColor(Color.argb(200,
							0, 206, 206));
					break;

				}
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
				finish();
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

}
