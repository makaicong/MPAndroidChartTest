package com.example.mpandroidcharttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username;
    private EditText userpass;
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.login_user_name);
        userpass = (EditText)findViewById(R.id.login_user_pass);
        login = (TextView)findViewById(R.id.login_textview);;


        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_textview:
                String name = username.getText().toString();
                String pass = userpass.getText().toString();
                //Log.e(  name,pass );
                sendRequestWithOKHttp(name,pass);
                break;
        }

    }
    private void sendRequestWithOKHttp(final String name,final String pass){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String aa = "http://219.231.12.209/login?user_number="+name+"&user_password="+pass;
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(aa)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                parseJSONWithJSONObject(response);
            }
        });
    }
     private void parseJSONWithJSONObject(String jsonData){

        try{
            JSONObject obj =new JSONObject(jsonData);
            String responsenum = obj.getString("resultCode");
            //Log.e("a     ",responsenum);
            Toast.makeText(this, responsenum, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
