package com.linhnguyen.learningenglish;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Linh Nguyen on 12/10/2016.
 */
public class ChuDe3Actitivy extends Activity {
	Animation Ani_1, Ani_Trans, Ani_big;
	Gallery gl; ImageView iv, iv_back;
	TextView tv, tv_stt;
	int pos;
	MediaPlayer mp;
	String[] Names ={"characteristic",
			"consequence",
			"consider",
			"cover",
			"expiration"};
	int[] Animals =
			{
					R.drawable.characteristic,
					R.drawable.consequence,
					R.drawable.consider,
					R.drawable.cover,
					R.drawable.expired,

	};

	int[] Sound ={
			R.raw.characteristic,
			R.raw.consequence,
			R.raw.consider,
			R.raw.cover,
			R.raw.expiration
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animal);
		gl = (Gallery) findViewById(R.id.gallery1);
		tv = (TextView) findViewById(R.id.textView1);
		iv =(ImageView) findViewById(R.id.imageView1);
		tv_stt = (TextView) findViewById(R.id.textView2);
		iv_back = (ImageView) findViewById(R.id.imageView2);
		Ani_1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
		Ani_Trans = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
		Ani_big = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_big);
		
		gl.setAdapter(new myadapter(this));
		gl.setSpacing(100);
		gl.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
									   int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				tv.setText(Names[arg2]);
				tv.startAnimation(Ani_1);
				pos = arg2;
				tv_stt.setText((arg2+1)+"/"+Animals.length);
				
				
				tv_stt.startAnimation(Ani_Trans);
				
				
				
				//gl.startAnimation(Ani_big);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp = MediaPlayer.create(getApplicationContext(), Sound[pos]);
				mp.start();
				iv.startAnimation(Ani_big);
			}
		});
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animal, menu);
		return true;
	}
	class myadapter extends BaseAdapter
	{
		Context context;
		public myadapter(Context c)
		{
			this.context=c;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Animals.length;
		}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return Animals[arg0];
		}
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView iv = new ImageView(this.context);
			iv.setImageResource(Animals[position]);
			iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
			
			
			return iv;
		}
	}

}
