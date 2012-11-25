package com.mokcy.hello;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GetData extends Activity implements Runnable{
	TextView vshow;
	String url="http://api.phodal.com/api/v1/1/?format=json";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getdata);
		
		Thread thread=new Thread(GetData.this);
		thread.start();
		
		vshow=(TextView) this.findViewById(R.id.get_data);
		
		try{
			callGetJson(url);
		}catch(Exception e){
			Log.v("Exception",e.getMessage());
		}
	}
	
	@Override
	public void run(){
		
	}
	
	@SuppressLint("ParserError")
	public void callGetJson(String resp) throws IllegalStateException,
			IOException,JSONException,NoSuchAlgorithmException {
		StringBuilder sb=new StringBuilder();
		Log.v("get",resp);
		JSONObject mResponseObject=new JSONObject(resp);
		JSONObject responObject=mResponseObject.getJSONObject("responseData");
		JSONArray array=responObject.getJSONArray("results");
		Log.v("gsearch","fsdfa"+array.length());
		for(int i=0;i<array.length();i++){
			Log.v("'ge","number of resultst:"+array.length());
			String title=array.getJSONObject(i).getString("title");
			String urllink=array.getJSONObject(i).getString("visibleUrl");
			
			sb.append(title);
			sb.append("\n");
			sb.append(urllink);
			sb.append("\n");
		}
		vshow.setText(sb.toString());
		
	}
}