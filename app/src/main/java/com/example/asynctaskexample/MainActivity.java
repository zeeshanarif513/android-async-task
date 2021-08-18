package com.example.asynctaskexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnTime;
    EditText edtTime;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTime = (Button) findViewById(R.id.btntime);
        edtTime = (EditText) findViewById(R.id.edttime);
        tvResult = (TextView) findViewById(R.id.tvresult);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTaskExample = new AsyncTaskExample();
                String time = edtTime.getText().toString();
                asyncTaskExample.execute(time);
            }
        });
    }

    public class AsyncTaskExample extends AsyncTask<String, String, String>{
         String res;
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this,"ProgressDialog","Wait for "+edtTime.getText().toString()+" seconds.");
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                int time  = Integer.valueOf(params[0]) * 1000;
                Thread.sleep(time);
                res = "Slept for "+ params[0]+ " seconds";
                return res;
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           progressDialog.dismiss();
            tvResult.setText(s);
        }
    }
}
