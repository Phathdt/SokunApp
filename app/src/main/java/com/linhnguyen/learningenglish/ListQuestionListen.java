package com.linhnguyen.learningenglish;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Linh Nguyen on 12/11/2016.
 */

public class ListQuestionListen extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/com.example.learnenglish/databases/";
    private static String DB_NAME = "listen.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "listen";

    private static final String KEY_ID = "id";
    private static final String KEY_QUESTIONIMAGE = "question_image";
    private static final String KEY_QUESTIONMP3 = "question_mp3";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_SUB ="sub";
    private SQLiteDatabase db;
    private final Context myContext;

    public ListQuestionListen(Context myContext) {
        super(myContext, DB_NAME, null, DATABASE_VERSION);
        this.myContext = myContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDataBase() throws SQLException {
        // Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        // TODO Auto-generated method stub
        if (db != null)
            db.close();
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
}
