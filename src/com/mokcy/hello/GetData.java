package com.mokcy.hello;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mokcy.hello.RestClient.RequestMethod;

public class GetData extends Activity implements Runnable {
	TextView vshow;
	String url = "http://api.phodal.com/api/v1/1/?format=json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getdata);

		Thread thread = new Thread(GetData.this);
		thread.start();

		vshow = (TextView) this.findViewById(R.id.get_data);
		vshow.setMovementMethod(ScrollingMovementMethod.getInstance());
		String tUrl = "http://api.phodal.com/api/v1/1/";
		RestClient client = new RestClient(tUrl);
		//client.addBasicAuthentication(username, password);
		try {
		    client.Execute(RequestMethod.GET);
		    if (client.getResponseCode() != 200) {
		        //return server error
		        vshow.setText(client.getErrorMessage());
		    }
		    //return valid data
		    JSONObject jObj = new JSONObject(client.getResponse());
		    vshow.setText(jObj.toString());
		    
		    GsonBuilder gsonb=new GsonBuilder();
		    Gson gson=gsonb.create();
            //Json中的日期表达方式没有办法直接转换成我们的Date类型, 因此需要单独注册一个Date的反序列化类.
            //DateDeserializer ds = new DateDeserializer();
            //给GsonBuilder方法单独指定Date类型的反序列化方法
			// gsonb.registerTypeAdapter(Date.class, ds);		    
		    typePhoData phoData=gson.fromJson(jObj.toString(),typePhoData.class);
		    ((TextView) findViewById(R.id.led)).setText(String.valueOf(phoData.led));
            ((TextView) findViewById(R.id.title)).setText(phoData.title);
            ((TextView) findViewById(R.id.temperature)).setText(String.valueOf(phoData.temperature));
            ((TextView) findViewById(R.id.more)).setText(phoData.more);
        	
            RestClient clientPost=new RestClient(tUrl);
            clientPost.AddParam("temperature","23.1");
            clientPost.AddParam("led","true");
            clientPost.AddParam("title","from android");
            clientPost.AddParam("more", "nEW tESET");
            try{
            	clientPost.Execute(RequestMethod.POST);
            	String response=client.getResponse();
            	vshow.setText(response.toString());
            }catch(Exception e){
            	vshow.setText(e.toString());
            }
		} catch(Exception e) {
		    vshow.setText(e.toString());
		}
	}
	public class typePhoData{
		public boolean led;
		public String title;
		public double temperature;
		public String more;
	}

	@Override
	public void run() {

	}
}