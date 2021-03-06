package com.linhnguyen.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Linh Nguyen on 1/10/2016.
 */
public class AddnotesActivity extends Activity {
	EditText et_Title, et_Description;
	Button ok;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addnotes);
		et_Title = (EditText) findViewById(R.id.editText1);
		et_Description = (EditText) findViewById(R.id.editText2);
		ok = (Button) findViewById(R.id.button1);
		iv = (ImageView) findViewById(R.id.imageView1);
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String Title = et_Title.getText().toString();
				String Description = et_Description.getText().toString();
				Intent i =new Intent();
				i.putExtra("Title", Title);
				i.putExtra("Description", Description);
				setResult(RESULT_OK, i);
				finish();
				
			}
		});
		iv.setOnClickListener(new View.OnClickListener() {
			
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
		getMenuInflater().inflate(R.menu.addnotes, menu);
		return true;
	}

}
