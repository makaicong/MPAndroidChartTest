package com.example.mpandroidcharttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class GetActivity extends AppCompatActivity implements View.OnClickListener{

    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        Button sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send_request){
            //sendRequestWithHttpURLConnection();
            sendRequestWithOKHttp();
        }
    }
    private void sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://219.231.12.209/login?user_number=100001&user_password=123456")
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

    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                String  name = "100001";
                String pass = "123456" ;
                String aa = "http://219.231.12.209/login?user_number="+name+"&user_password="+pass;
                try{

                    //URL url = new URL("http://219.231.12.94/login?user_number=100001&user_password=123456");
                    URL url = new URL(aa);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    InputStream in = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try{
                            reader.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);

            }
        });
    }


}
