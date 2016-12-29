package com.linhnguyen.learningenglish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends Fragment {
    GridView gv;
    ArrayList<Logo> ds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_menu, container, false);

        gv = (GridView) view.findViewById(R.id.gridView1);
        ds = new ArrayList<Logo>();
        String[] ten = {"Vocabulary", "Listenning", "Reading", "Videos", "Notes",
                "About", "Exit", "Dictionary"};

        int[] hinh = {R.drawable.apple, R.drawable.phone, R.drawable.facebook,
                R.drawable.menu_youtube, R.drawable.note, R.drawable.facebook,
                R.drawable.exit, R.drawable.dictionary};

        for (int i = 0; i < ten.length; i++)
            ds.add(new Logo(ten[i], hinh[i]));

        MyAdapter adapter = new MyAdapter(getActivity());
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    case 0:
                        Intent i = new Intent(getActivity(),
                                VocabularyActivity.class);
                        startActivity(i);

                        break;
                    case 1:
                        Intent Listen = new Intent(getActivity(),
                                ListenActivity.class);
                        startActivity(Listen);

                        break;
                    case 2:
                        Intent read = new Intent(getActivity(),
                                ReadActivity.class);
                        startActivity(read);

                        break;
                    case 3:
                        Intent youtube = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://www.youtube.com/results?search_query=Hoc+tieng+anh+hieu+qua&oq=Hoc+tieng+anh+hieu+qua&gs_l=youtube.3..0l2j0i5l8.2070.5797.0.6484.28.19.3.6.6.0.158.1670.16j3.19.0...0.0...1ac.1.11.youtube.m7m2HTi6FjQ"));
                        startActivity(youtube);
                        break;
                    case 4:
                        Intent Notes = new Intent(getActivity(),
                                NotesActivity.class);
                        startActivity(Notes);
                        break;
                    case 5:
                        Intent About = new Intent(getActivity(), AboutActivity.class);
                        startActivity(About);
                        break;
                    case 6:
                        AlertDialog.Builder bui = new AlertDialog.Builder(
                                getActivity());
                        bui.setTitle("Exit");
                        bui.setMessage("Do You Want To Exit?");
                        bui.setNegativeButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                getActivity().finish();
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

                        break;
                    case 7:
                        Intent Dictionary = new Intent(getActivity(), DictionaryActivity.class);
                        startActivity(Dictionary);
                        break;

                    default:
                        break;
                }


            }
        });
        return view;

    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getActivity().getMenuInflater().inflate(R.menu.menu, menu);
//		return true;
//	}

    public static class View_Mot_O {
        public ImageView imageview;
        public TextView textview;
    }

    class MyAdapter extends BaseAdapter {
        Context c;

        MyAdapter(Context c) {
            this.c = c;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return ds.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return ds.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View_Mot_O mot_o;
            LayoutInflater inf = ((Activity) c).getLayoutInflater();
            if (convertView == null) {
                convertView = inf.inflate(R.layout.gridview_mot_o, null);
                mot_o = new View_Mot_O();
                mot_o.imageview = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                mot_o.textview = (TextView) convertView
                        .findViewById(R.id.textView1);
                convertView.setTag(mot_o);

                if (position % 6 == 0) {
                    convertView
                            .setBackgroundColor(Color.argb(200, 216, 32, 30));
                } else {
                    if (position % 6 == 1) {
                        convertView.setBackgroundColor(Color.argb(200, 255,
                                131, 6));
                    } else {
                        if (position % 6 == 2) {
                            convertView.setBackgroundColor(Color.argb(200, 0,
                                    198, 99));
                        } else {
                            if (position % 6 == 3)
                                convertView.setBackgroundColor(Color.argb(200,
                                        0, 206, 206));
                            else {
                                if (position % 6 == 4)
                                    convertView.setBackgroundColor(Color.argb(
                                            200, 26, 156, 139));
                                else
                                    convertView.setBackgroundColor(Color.argb(
                                            200, 128, 0, 128));
                            }
                        }
                    }
                }
            } else {
                mot_o = (View_Mot_O) convertView.getTag();
            }

            mot_o.imageview.setImageResource(ds.get(position).hinh);
            mot_o.textview.setText(ds.get(position).ten);

            return convertView;
        }
    }
}