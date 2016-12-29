package com.linhnguyen.learningenglish;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment = null;
    //Bước 1: khai báo 1 FragmentManeger để quản lý fragment
    android.support.v4.app.FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadFragmentVocabulary();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vocabulary) {
            loadFragmentVocabulary();
            // Handle the camera action
        } else if (id == R.id.nav_listenning) {

            loadFragmentListenning();
        } else if (id == R.id.nav_read) {

            loadFragmentRead();

        } else if (id == R.id.nav_video) {
            accessYoutube();

        } else if (id == R.id.nav_notes) {
            loadFragmentNotes();
        } else if (id == R.id.nav_about) {
            loadFragmentAbout();

        } else if (id == R.id.nav_exit) {

            xuLyExit();
        } else if (id == R.id.nav_dictionary) {
            loadFragmentDictionary();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //    public void loadFragmentMenu() {
//        fragment = new MenuActivity();
//        //khai báo 1 fragmentTransaction để quản lý việc thêm, xóa, sửa, thay thế fragment
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.view1, fragment);
//        transaction.commit();
//    }
    private void loadFragmentVocabulary() {
        fragment = new VocabularyActivity();
        //khai báo 1 fragmentTransaction để quản lý việc thêm, xóa, sửa, thay thế fragment
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.view1, fragment);
        transaction.commit();
    }

    private void loadFragmentRead() {
        fragment = new ReadActivity();
        //khai báo 1 fragmentTransaction để quản lý việc thêm, xóa, sửa, thay thế fragment
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.view1, fragment);
        transaction.commit();
    }

    private void loadFragmentNotes() {
        fragment = new NotesActivity();
        //khai báo 1 fragmentTransaction để quản lý việc thêm, xóa, sửa, thay thế fragment
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.view1, fragment);
        transaction.commit();
    }

    private void loadFragmentAbout() {
        fragment = new AboutActivity();
        //khai báo 1 fragmentTransaction để quản lý việc thêm, xóa, sửa, thay thế fragment
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.view1, fragment);
        transaction.commit();
    }

    private void loadFragmentDictionary() {
        fragment = new DictionaryActivity();
        //khai báo 1 fragmentTransaction để quản lý việc thêm, xóa, sửa, thay thế fragment
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.view1, fragment);
        transaction.commit();
    }

    private void loadFragmentListenning() {
        fragment = new ListenActivity();
        //khai báo 1 fragmentTransaction để quản lý việc thêm, xóa, sửa, thay thế fragment
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.view1, fragment);
        transaction.commit();
    }

    private void xuLyExit() {
        AlertDialog.Builder bui = new AlertDialog.Builder(
                MainActivity.this);
        bui.setTitle("Exit");
        bui.setMessage("Do You Want To Exit?");
        bui.setNegativeButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        bui.setPositiveButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        bui.show();

    }

    private void accessYoutube() {
        Intent youtube = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/results?search_query=Hoc+tieng+anh+hieu+qua&oq=Hoc+tieng+anh+hieu+qua&gs_l=youtube.3..0l2j0i5l8.2070.5797.0.6484.28.19.3.6.6.0.158.1670.16j3.19.0...0.0...1ac.1.11.youtube.m7m2HTi6FjQ"));
        startActivity(youtube);

    }
}
