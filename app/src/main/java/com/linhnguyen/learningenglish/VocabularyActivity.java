package com.linhnguyen.learningenglish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Linh Nguyen on 19/10/2016.
 */
public class VocabularyActivity extends Fragment {
	ListView lv;
	Animation ani;
    
    String[]ten={ "Contracts","Marketing",
					"Warranties ","Bussiness Planning",
					"Conferences","Computer and the Internet",
					"Office Technology","Office Procedures",
					"Electtronics","Correspondence",
					"Job Ads and recuitment","Apply and interviewing"
    
    };
    int []hinh={ R.drawable.contracts,R.drawable.marketing,
    	   	R.drawable.warranties,R.drawable.business_planning,
    	   	R.drawable.conferences,R.drawable.computers_and_the_internet,
    	   	
    	   	R.drawable.office_technology,R.drawable.office_procedures,
    	   	R.drawable.electronics,R.drawable.conferences,
    	   	R.drawable.job_ads_and_recruitment,R.drawable.apply_and_interviewing
        };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.activity_vocabulary, container, false);

		lv = (ListView) view.findViewById(R.id.listView1);
		lv.setAdapter(new myadapter(getActivity(), ten));
		lv.setDividerHeight(10);

		ani = AnimationUtils.loadAnimation(getActivity(), R.anim.scale2);

		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				Intent i ;
				switch (arg2) {
					case 0:
						i = new Intent(getActivity(), ChuDe1Actitivy.class);
						startActivity(i);
						break;
					case 1:
						i = new Intent(getActivity(), ChuDe2Actitivy.class);
						startActivity(i);
						break;
					case 2:
						i = new Intent(getActivity(), ChuDe3Actitivy.class);
						startActivity(i);
						break;
				}


			}
		});
		return view;
	}
//		@Override
//		public boolean onCreateOptionsMenu(Menu menu) {
//			// Inflate the menu; this adds items to the action bar if it is present.
//			getMenuInflater().inflate(R.menu.vocabulary, menu);
//			return true;
//		}
		class myadapter extends ArrayAdapter {
			Context context;


			public myadapter(Context context, String[] ten)
			{
				super(context,R.layout.listview_item,ten);
				this.context=context;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// dung layoutinflater lấy đọc cấu trúc và nội dung của từng hàng listview
				LayoutInflater inf=(LayoutInflater)
						context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View rowview=inf.inflate(R.layout.listview_item,parent, false);

				//ánh xạ từng hàng listview , cập nhật thông tin
				TextView textview=(TextView)rowview.findViewById(R.id.textView1);
				ImageView imageview=(ImageView)rowview.findViewById(R.id.imageView1);

				textview.setText(ten[position]);
				imageview.setImageResource(hinh[position]);

				if (position % 4 == 0){
					rowview.setBackgroundColor(Color.argb(200, 213, 7, 0));
				}
				else
				{
					if (position % 4 == 1){
						rowview.setBackgroundColor(Color.argb(200, 255, 131, 6));
					}
					else
					{
						if (position % 4 == 2){
							rowview.setBackgroundColor(Color.argb(200, 0, 198, 99));
						}
						else
							rowview.setBackgroundColor(Color.argb(200, 0, 206, 206));
					}
				}
				rowview.startAnimation(ani);

				return rowview;
			}

		}

	}







//
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_vocabulary);
//		iv = (ImageView) findViewById(R.id.imageView2);
//		lv=(ListView)findViewById(R.id.listView1);
//		lv.setAdapter(new myadapter(this,ten));
//		lv.setDividerHeight(10);
//
//		ani = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale2);
//
//		lv.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//									long arg3) {
//				switch(arg2){
//				case 0: Intent i = new Intent(getApplicationContext(), AnimalActivity.class);
//						startActivity(i);
//						break;
//				}
//
//			}
//        });
//		iv.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				finish();
//			}
//		});
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.vocabulary, menu);
//		return true;
//	}
//	class myadapter extends ArrayAdapter {
//		Context context;
//
//
//		public myadapter(Context context, String[] ten)
//		{
//			super(context,R.layout.listview_item,ten);
//			this.context=context;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			// dung layoutinflater lấy đọc cấu trúc và nội dung của từng hàng listview
//			LayoutInflater inf=(LayoutInflater)
//					context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			View rowview=inf.inflate(R.layout.listview_item,parent, false);
//
//			//ánh xạ từng hàng listview , cập nhật thông tin
//			TextView textview=(TextView)rowview.findViewById(R.id.textView1);
//			ImageView imageview=(ImageView)rowview.findViewById(R.id.imageView1);
//
//			textview.setText(ten[position]);
//			imageview.setImageResource(hinh[position]);
//
//			if (position % 4 == 0){
//				rowview.setBackgroundColor(Color.argb(200, 213, 7, 0));
//			}
//			else
//			{
//				if (position % 4 == 1){
//					rowview.setBackgroundColor(Color.argb(200, 255, 131, 6));
//				}
//				else
//				{
//					if (position % 4 == 2){
//						rowview.setBackgroundColor(Color.argb(200, 0, 198, 99));
//					}
//					else
//						rowview.setBackgroundColor(Color.argb(200, 0, 206, 206));
//				}
//			}
//			rowview.startAnimation(ani);
//
//			return rowview;
//		}
//
//	}
//
//}
