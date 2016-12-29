package com.linhnguyen.learningenglish;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Linh Nguyen on 1/10/2016.
 */
public class AboutActivity extends Fragment {
TextView tv1, tv2, tv3, tv4, tv5;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.activity_about, container, false);
		tv1 = (TextView) view.findViewById(R.id.textView1);
		tv2= (TextView) view.findViewById(R.id.textView2);
		tv3 = (TextView) view.findViewById(R.id.textView3);
		tv4 = (TextView) view.findViewById(R.id.textView4);
		tv5 = (TextView) view.findViewById(R.id.textView5);
	return view;
	}
}
