package com.linhnguyen.learningenglish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class NotesActivity extends Fragment {
	Notes notes;
	ListView lv;
	ArrayList<Works> list = new ArrayList<Works>();
	ImageView iv, iv_back;
	long x;


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.activity_notes, container, false);

		lv = (ListView) view.findViewById(R.id.listView1);
		iv = (ImageView)  view.findViewById(R.id.imageView1);
		iv_back = (ImageView)  view.findViewById(R.id.imageView2);
		notes = new Notes(getActivity());
		Readdata();
		registerForContextMenu(lv);
		lv.setDividerHeight(10);

		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), AddnotesActivity.class);
				startActivityForResult(i, 999);
			}
		});
		return view;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.notes, menu);
//		return true;
//	}
	public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	switch (item.getItemId()) {
			case R.id.addNotes:
				Intent i=new Intent(getActivity(),AddnotesActivity.class);
				startActivityForResult(i, 999);
				break;

			default:
				break;
		}    	
    	return super.onOptionsItemSelected(item);
    }
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode==999 && resultCode==RESULT_OK)
    	{
    		String Title=data.getExtras().getString("Title");
    		String Description=data.getExtras().getString("Description");
    		Works c=new Works(Title, Description);
    		notes.AddNotes(c);
    		Readdata();
    	}
    	if(requestCode==555 && resultCode==RESULT_OK)
    	{
    		int id= Integer.parseInt(data.getExtras().getString("id"));
    		String noidung=data.getExtras().getString("Title");
    		String thoigian=data.getExtras().getString("Description");
    		Works c=new Works(id,noidung,thoigian);
    		notes.updateByWorks(c);
    		Readdata();
    	}
	}
	public void Readdata() {
		try{
        	list=notes.getNotes();    
        	lv.setAdapter(new MyAdapter(getActivity()));
        }catch(Exception e)
        {
        	lv.setAdapter(null);
        }
	}
	public static class View_Mot_O
    {
    	TextView id;
    	TextView Title;
    	TextView Description;
    }
	class MyAdapter extends BaseAdapter
    {
    	Context context;
    	
    	MyAdapter(Context c)
    	{
    		context=c;
    	}
    	
    	public int getCount() {
    		// TODO Auto-generated method stub
    		
    		return list.size();
    	}

    	public Object getItem(int arg0) {
    		// TODO Auto-generated method stub
    		return list.get(arg0);
    	}

    	public long getItemId(int arg0) {
    		// TODO Auto-generated method stub
    		return arg0;//ds_congviec.get(arg0).id;
    	}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View_Mot_O mot_o;
			LayoutInflater inf=((Activity)context).getLayoutInflater();
			if(convertView==null)
			{
				mot_o=new View_Mot_O();
				convertView=inf.inflate(R.layout.listview_item_notes, null);
				mot_o.id=(TextView)convertView.findViewById(R.id.textView1);
				mot_o.Title=(TextView)convertView.findViewById(R.id.textView2);
				mot_o.Description=(TextView)convertView.findViewById(R.id.textView3);
				convertView.setTag(mot_o);
				// Color Backround
				if (position % 4 == 0){
					convertView.setBackgroundColor(Color.argb(146,204,242, 200));
				}
				else
				{
					if (position % 4 == 1){
						convertView.setBackgroundColor(Color.argb(200, 255, 131, 6));
					}
					else
					{
						if (position % 4 == 2){
							convertView.setBackgroundColor(Color.argb(200, 0, 198, 99));
						}
						else
							convertView.setBackgroundColor(Color.argb(200, 0, 206, 206));
					}
				}
			}
			else
			{
				mot_o=(View_Mot_O)convertView.getTag();
			}
			mot_o.id.setText(list.get(position).id+"");
			mot_o.Title.setText(list.get(position).Title);
			mot_o.Description.setText(list.get(position).Description);
			
			return convertView;
		}
    }
	public void onCreateContextMenu(ContextMenu menu, View v,
									ContextMenuInfo menuInfo) {
    	// TODO Auto-generated method stub
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inf=getActivity().getMenuInflater();
    	inf.inflate(R.menu.notes_menu, menu);
	}
	public boolean onContextItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
    	long vitri=lv.getItemIdAtPosition(info.position);
    	
    	x = vitri;
    	
    	switch (item.getItemId()) {
		case R.id.delete:
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Delete");
			builder.setMessage("Do you want to delete it?");
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Delete_notes((int)x);
					
				}
			});
			builder.show();
			
			break;
		case R.id.edit:
			//Toast.makeText(getApplicationContext(), "cho sua tai" + vitri, Toast.LENGTH_SHORT).show();
			Edit((int)vitri);
			break;
		default:
			break;
		}
    	return super.onContextItemSelected(item);
    }
	 public void Delete_notes(int vitri)
	    {
	    	Works c= list.get(vitri);
	    	int idcv=c.id;
	    	notes.deleteById(idcv);
	    	Readdata();
	    }
	 public void Edit(int vitri)
	    {
	    	Works c=list.get(vitri);
	    	Intent i=new Intent(getActivity(),EditNotesActivity.class);
	    	i.putExtra("id", c.id+"");
	    	i.putExtra("Title", c.Title);
	    	i.putExtra("Description", c.Description);
	    	startActivityForResult(i, 555);
	    }


}
