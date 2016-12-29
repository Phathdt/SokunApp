package com.linhnguyen.learningenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Linh Nguyen on 10/11/2016.
 */
public class EditNotesActivity extends Activity {
	TextView tv_id;
	EditText et_Title, et_Description;
	Button ok;
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_notes);
		et_Title = (EditText) findViewById(R.id.editText1);
		et_Description = (EditText) findViewById(R.id.editText2);
		ok = (Button) findViewById(R.id.button1);
		tv_id = (TextView) findViewById(R.id.textView41);
		iv =(ImageView) findViewById(R.id.imageView1);
		
		tv_id.setText(getIntent().getExtras().getString("id"));
		et_Title.setText(getIntent().getExtras().getString("Title"));
		et_Description.setText(getIntent().getExtras().getString("Description"));
		et_Title.setSelection(et_Title.getText().length());
		
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id=tv_id.getText().toString();
				String Title=et_Title.getText().toString();
				String Description=et_Description.getText().toString();
				Intent trave=new Intent();
				trave.putExtra("id", id);
				trave.putExtra("Title", Title);
				trave.putExtra("Description", Description);
				setResult(RESULT_OK, trave);
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
		getMenuInflater().inflate(R.menu.edit_notes, menu);
		return true;
	}

}
