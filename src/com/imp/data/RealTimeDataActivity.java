package com.imp.data;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.greendrivinghelper.R;
import com.imp.bluetooth.MessageProcessing;

public class RealTimeDataActivity extends Activity{
	private Button showData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.real_time_data);
		showData = (Button) findViewById(R.id.btn_showrealtimedata);
		showData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MessageProcessing.setHandler("ATHBT\r\n");
			}
		});
	}

}
