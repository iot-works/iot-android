package com.phodal.android.athome;

/**
 * Created by fdhuang on 5/24/14.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetData extends Activity  {
    TextView vshow;
    String url = "http://b.phodal.com/athome/1/";

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdata);

        vshow = (TextView) this.findViewById(R.id.get_data);
        vshow.setMovementMethod(ScrollingMovementMethod.getInstance());
        RestClient client = new RestClient(url);
        try {
            client.Execute(RestClient.RequestMethod.GET);
            if (client.getResponseCode() != 200) {
                vshow.setText(R.string.getSuccess);
                vshow.setText(client.getErrorMessage());
            }

            JSONArray jArray = new JSONArray(client.getResponse());
            JSONObject jObj=jArray.getJSONObject(0);
            vshow.setText(jObj.toString());

            outputJSON(jObj);

        } catch (Exception e) {
            vshow.setText(R.string.connectError);
        }

    }

    public void outputJSON(JSONObject jObj) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        IOTDataModel phoData = gson.fromJson(jObj.toString(),
                IOTDataModel.class);

        ((TextView) findViewById(R.id.led1)).setText(String
                .valueOf(phoData.led1));
        ((TextView) findViewById(R.id.sensors1)).setText(String
                .valueOf(phoData.sensors1));
        ((TextView) findViewById(R.id.sensors2)).setText(String
                .valueOf(phoData.sensors2));
        ((TextView) findViewById(R.id.temperature)).setText(String
                .valueOf(phoData.temperature));
        ((TextView) findViewById(R.id.id1)).setText(String
                .valueOf(phoData.id));
    }

    public class IOTDataModel {
        public int led1;
        public double temperature;
        public double sensors1;
        public double sensors2;
        public int    id;
    }

}