package com.mokcy.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
	TextView tv1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutButton=(Button) findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener(){	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callAbout();
			}		
		});
        Button dataGetButton=(Button) findViewById(R.id.getdata_button);
        dataGetButton.setOnClickListener(new View.OnClickListener(){	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getData();
			}
		});
        Button viewTempButton=(Button) findViewById(R.id.viewTemp_button);
        viewTempButton.setOnClickListener(new View.OnClickListener(){	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callViewTemp();
			}
		});        
        Button ledButton=(Button) findViewById(R.id.led_button);
        ledButton.setOnClickListener(new View.OnClickListener(){	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callLedCon();
			}
		});               
    }



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {   
        return super.onCreateOptionsMenu(menu);
    }
    
    public void getData(){
    	Intent startGet=new Intent(MainActivity.this,GetData.class);
    	startActivity(startGet);
    }
    
    public void callAbout(){
    	Intent start=new Intent(MainActivity.this,About.class);
    	startActivity(start);
    } 

    public void callViewTemp(){
    	Intent start=new Intent(MainActivity.this,temViewActivity.class);
    	startActivity(start);
    } 	   
    protected void callLedCon() {
		Intent callled=new Intent(MainActivity.this,ledCon.class);
		startActivity(callled);
		
	}
}
