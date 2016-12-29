package com.linhnguyen.learningenglish;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Linh Nguyen on 12/10/2016.
 */

public class DictionaryActivity extends Fragment {
    private TextView resultText;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dictionary, container, false);
        final EditText editText_Search = (EditText) view.findViewById(R.id.editText_Search);
        Button buttonEN = (Button) view.findViewById(R.id.button_EN);
        Button buttonVN = (Button) view.findViewById(R.id.button_VN);
        resultText = (TextView) view.findViewById(R.id.resultText);

        buttonEN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String WordSearch = editText_Search.getText().toString();
                new PareseURL().execute(WordSearch);
            }
        });
        buttonVN.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                String WordSearch = editText_Search.getText().toString();
                new PareseURL().execute(WordSearch);
            }
        });

        return view;
    }
    private  class PareseURL extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String text = strings[0];
            Document doc;
            try {
                doc = Jsoup.connect("http://dict.laban.vn/find?type=2&query="+text).get();
                Elements inputElements = doc.getElementsByTag("meta");
                String temp = inputElements.get(1).attr("content");
                String[] t = temp.split("\\. ");
                String txtTextArea;
                if (t.length == 1)
                {
                    txtTextArea = ("Khong tim thay");
                }
                else
                {
                    txtTextArea = ("");
                    for (int i = 1; i < t.length; i++)
                    {
                        txtTextArea += (t[i]+"\n");
                    }
                }
                System.out.println(txtTextArea);
                return txtTextArea;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgessDialog();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideProgressDialog();
            if (s != null){
                resultText.setText(s);
            } else {
                resultText.setText("Error ?");
                Toast.makeText(getActivity(), "Chưa kết nối wifi hoặc 3G", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void showProgessDialog(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("waiting...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }
    private void hideProgressDialog(){
        if (progressDialog!= null || progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
