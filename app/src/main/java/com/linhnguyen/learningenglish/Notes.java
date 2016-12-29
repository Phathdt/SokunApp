package com.linhnguyen.learningenglish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Linh Nguyen on 12/10/2016.
 */
public class Notes extends SQLiteOpenHelper {

	public Notes(Context context){
		super(context, "table_notes", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="create table table_notes " +
				"(" +
				"_id integer primary key autoincrement, " +
				"Title text, " +
				"Description text" +
				")";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table table_notes if exists");
		onCreate(db);
		
	}
	public void AddNotes(Works c)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues content=new ContentValues();
		content.put("Title", c.Title);
		content.put("Description", c.Description);
		String chonull=null;
		db.insert("table_notes", chonull,content);
	}
	public ArrayList<Works> getNotes()
	{
		ArrayList<Works> ds=new ArrayList<Works>();
		SQLiteDatabase db=this.getReadableDatabase();
		String sql="select * from table_notes";
		Cursor c=db.rawQuery(sql, null);
		c.moveToFirst();
		do
		{
			int id=c.getInt(0);
			String Title=c.getString(1).toString();
			String Description=c.getString(2).toString();
			Works cv=new Works(id, Title, Description);
			ds.add(cv);
		}while(c.moveToNext());
		
		return ds;
	}
	public void deleteById(int x)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		String[] ds=new String[]{x+""};
		db.delete("table_notes", "_id=?", ds);
		
	}
	public void updateByWorks(Works c)
	{
		int id=c.id;
		String Title=c.Title;
		String Description=c.Description;
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues content=new ContentValues();
		content.put("Title", Title);
		content.put("Description", Description);
		
		db.update("table_notes", content, "_id="+id, null);
		
		
		
	}
	
	

}
