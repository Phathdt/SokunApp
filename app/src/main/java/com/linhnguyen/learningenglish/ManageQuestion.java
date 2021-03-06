package com.linhnguyen.learningenglish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ManageQuestion extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.linhnguyen.learningenglish/databases/";
	private static String DB_NAME = "ToeicDB.sqlite";
	private static final int DATABASE_VERSION = 3;
	private static final String TABLE_NAME = "PART1";

	private static final String KEY_ID = "_id";
	private static final String KEY_Question = "Question";
	private static final String KEY_A = "cau_a";
	private static final String KEY_B = "cau_b";
	private static final String KEY_C = "cau_c";
	private static final String KEY_D = "cau_d";
	private static final String KEY_DA = "dapan";
	// them
	private static final String KEY_TL = "traloi";
	private SQLiteDatabase myDataBase;
	private final Context myContext;

	public ManageQuestion(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	public void openDataBase() throws SQLException {
		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// database chua ton tai
		}
		if (checkDB != null)
			checkDB.close();
		return checkDB != null ? true : false;
	}

	void copyDataBase() throws IOException {
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		// truyen du lieu tu inputfile sang outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Dong luon
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase(); // kiem tra db
		if (dbExist) {
			// khong lam gi ca, database da co roi
		} else {
			this.getReadableDatabase();
			try {
				copyDataBase(); // chep du lieu
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	public Cursor laytatcaQuestion() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor contro = db.rawQuery("select * from PART1", null);
		return contro;
	}

	public List<Question> layNcaungaunhien(int socau) {
		List<Question> ds_Question = new ArrayList<Question>();
		String limit = "0," + socau;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor contro=db.query(TABLE_NAME, null, null, null, null, null, "random()", limit);
		contro.moveToFirst();
		do {
			Question x = new Question();
			x._id = Integer.parseInt(contro.getString(0));
			x.image = contro.getBlob(1);
			x.audio = contro.getString(2);
			x.cau_a = contro.getString(3);
			x.cau_b = contro.getString(4);
			x.cau_c = contro.getString(5);
			x.cau_d = contro.getString(6);
			x.dapan = contro.getString(7);
			x.traloi = contro.getString(8);
			ds_Question.add(x);
		} while (contro.moveToNext());

		return ds_Question;

	}
	public void settraloi(int x, String traloi)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_TL, traloi);
		db.update(TABLE_NAME, values, "id="+x, null);
	}
	public void xoatraloi()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_TL, "");
		String sql="select * from "+TABLE_NAME;
		Cursor c=db.rawQuery(sql, null);
		c.moveToFirst();
		do
		{
			db.update(TABLE_NAME, values, null, null);
			
		}while(c.moveToNext());
		
		
		
	}
	public String Laycautraloi(Question c)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		String sql="select * from PART1 where id="+c._id;
		Cursor cs =db.rawQuery(sql, null);
		cs.moveToFirst();
		String Traloi = cs.getString(7);
		return Traloi;
	}
	public String Laycautraloi(int x)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		String sql="select * from PART1 where id="+x;
		Cursor cs =db.rawQuery(sql, null);
		cs.moveToFirst();
		String Traloi = cs.getString(8);
		return Traloi;
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
