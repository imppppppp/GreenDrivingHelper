package com.imp.savinghelper;



import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.example.greendrivinghelper.R;

public class SavingHelperActivity extends Activity{
	GridView mHotGridView, mHistoryGridView;
	TextView mTitleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saving_helper);
		prepareView();
		mTitleView.setText("Ω⁄”Õ÷˙ ÷");
	}

	private void prepareView() {

		mTitleView = (TextView) findViewById(R.id.title_text);
	}
}
