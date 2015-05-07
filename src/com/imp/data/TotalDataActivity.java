package com.imp.data;

import com.example.greendrivinghelper.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TotalDataActivity extends Activity{
	Button bt_show;
	Button bt_hide;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.total_data);
		
		bt_show=(Button)findViewById(R.id.btn_showtotal);
		bt_show.setOnClickListener(new OnClickListener() {
				
	    @Override
		public void onClick(View v) {
		// TODO Auto-generated method stub

		}
		});
		
		bt_show=(Button)findViewById(R.id.btn_hidetotal);
		bt_show.setOnClickListener(new OnClickListener() {
				
	    @Override
		public void onClick(View v) {
		// TODO Auto-generated method stub

		}
		});
		
	}
}
