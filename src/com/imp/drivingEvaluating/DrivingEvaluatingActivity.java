package com.imp.drivingEvaluating;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.greendrivinghelper.R;

public class DrivingEvaluatingActivity extends Activity{
	TextView mTitleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driving_evaluating);
		prepareView();
		mTitleView.setText("¼ÝÊ»ÆÀ²â");
	}

	private void prepareView() {
		mTitleView = (TextView) findViewById(R.id.title_text);
	}
}
