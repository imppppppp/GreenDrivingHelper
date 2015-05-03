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
		mTitleView.setText("����");
		
		IV1=(ImageView)this.findViewById(R.id.real_time);
		IV1.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//��ת��ʵʱ״̬���ݽ���.
				Intent localIntent = new Intent();
				localIntent.setClass(DataActivity.this,RealTimeDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
		IV2=(ImageView)this.findViewById(R.id.driving_habits);
		IV2.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//��ת����ʻϰ�����ݽ���.
				Intent localIntent = new Intent();
				localIntent.setClass(DataActivity.this,DrivingHabitsDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
		IV3=(ImageView)this.findViewById(R.id.total_data);
		IV3.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//��ת������ͳ�����ݽ���.
				Intent localIntent = new Intent();
				localIntent.setClass(DataActivity.this,TotalDataActivity.class);
				startActivity(localIntent);				
			}
	    });
		
		IV4=(ImageView)this.findViewById(R.id.infor_error);
		IV4.setOnClickListener(new ImageView.OnClickListener(){
			public void onClick(View v) {
				//��ת���豸��Ϣ������������ݽ���.
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
