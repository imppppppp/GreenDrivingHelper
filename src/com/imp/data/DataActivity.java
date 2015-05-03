package com.imp.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greendrivinghelper.R;

public class DataActivity extends Activity{
	TextView mTitleView;
	ImageView IV1,IV2,IV3,IV4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_activity);
		prepareView();
		mTitleView.setText("数据");
		
		IV1=(ImageView)this.findViewById(R.id.real_time);
		IV1.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//跳转到实时状态数据界面.
				Intent localIntent = new Intent();
				localIntent.setClass(DataActivity.this,RealTimeDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
		IV2=(ImageView)this.findViewById(R.id.driving_habits);
		IV2.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//跳转到驾驶习惯数据界面.
				Intent localIntent = new Intent();
				localIntent.setClass(DataActivity.this,DrivingHabitsDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
		IV3=(ImageView)this.findViewById(R.id.total_data);
		IV3.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//跳转到汇总统计数据界面.
				Intent localIntent = new Intent();
				localIntent.setClass(DataActivity.this,TotalDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
		IV4=(ImageView)this.findViewById(R.id.infor_error);
		IV4.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//跳转到设备信息及故障诊断数据界面.
				Intent localIntent = new Intent();
				localIntent.setClass(DataActivity.this,InforErrorDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
	}
	private void prepareView() {
		mTitleView = (TextView) findViewById(R.id.title_text);
	}
}
