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
public class AnimalActivity extends Activity {
	Animation Ani_1, Ani_Trans, Ani_big;
	Gallery gl; ImageView iv, iv_back;
	TextView tv, tv_stt;
	int pos;
	MediaPlayer mp;
	int[] Animals ={
			R.drawable.animals_ant,
			R.drawable.animals_bee,
			R.drawable.animals_bird,
//			R.drawable.animals_butterfly,
//			R.drawable.animals_cat,
//			R.drawable.animals_cheetah,
//			R.drawable.animals_crab,
//			R.drawable.animals_deer,
//			R.drawable.animals_dolphin,
//			R.drawable.animals_dragonfly,
//			R.drawable.animals_duck,
//			R.drawable.animals_eagle,
//			R.drawable.animals_gorilla,
//			R.drawable.animals_frog,
//			R.drawable.animals_horse,
//			R.drawable.animals_humming,
//			R.drawable.animals_ladybug,
//			R.drawable.animals_penguins,
//			R.drawable.animals_pigeon,
//			R.drawable.animals_squirrel,
//			R.drawable.animals_starfish,
//			R.drawable.animals_swan,
//			R.drawable.animals_wild
			
			
	};
	String[] Names ={"Ant", "Bee", "Bird",
//			"Butterfly", "Cat", "Cheetah", "Crab", "Deer", "Dolphin", 
//			"Dragonfly", "Duck", "Eagle", "Gorilla", "Frog", "Horse", "Humming", "Ladybug",
//			"Penguins", "Pigeon","Squirrel", "Starfish", "Swan", "Swild"
			};
	int[] Sound ={
			R.raw.ant,
			R.raw.bee,
			R.raw.bird,
//			R.raw.butterfly,
//			R.raw.cat,
//			R.raw.chetah,
//			R.raw.crab,
//			R.raw.beer,
//			R.raw.dolphin,
//			R.raw.dragonfly,
//			R.raw.duck,
//			R.raw.eagle,
//			
//			R.raw.gorilla,
//			R.raw.frog,
//			R.raw.horse,
//			R.raw.humming,
//			R.raw.ladybug,
//			R.raw.penguins,
//			R.raw.pigeon,
//			R.raw.squirrel,
//			R.raw.starfish,
//			R.raw.swan,
//			R.raw.swild
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
