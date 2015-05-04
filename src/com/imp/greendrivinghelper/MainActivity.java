package com.imp.greendrivinghelper;

import com.imp.data.DataActivity;
import com.imp.drivingEvaluating.DrivingEvaluatingActivity;
import com.imp.data.HomeActivity;
import com.imp.more.MoreActivity;
import com.imp.savinghelper.SavingHelperActivity;
import com.example.greendrivinghelper.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements OnClickListener {
	public static String TAB_TAG_HOME = "home";
	public static String TAB_TAG_CHANNEL = "channel";
	public static String TAB_TAG_ACCOUNT = "account";
	public static String TAB_TAG_SEARCH = "search";
	public static String TAB_TAB_MORE = "more";
	public static TabHost mTabHost;
	static final int COLOR1 = Color.parseColor("#787878");
	static final int COLOR2 = Color.parseColor("#ffffff");
	ImageView mBut1, mBut2, mBut3, mBut4, mBut5;
	TextView mCateText1, mCateText2, mCateText3, mCateText4, mCateText5;

	Intent mHomeItent, mChannelIntent, mSearchIntent, mAccountIntent,
			mMoreIntent;

	int mCurTabId = R.id.channel1;

	// Animation
	private Animation left_in, left_out;
	private Animation right_in, right_out;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prepareAnim();
		prepareIntent();
		setupIntent();
		prepareView();
	}

	private void prepareAnim() {
		left_in = AnimationUtils.loadAnimation(this, R.anim.left_in);
		left_out = AnimationUtils.loadAnimation(this, R.anim.left_out);

		right_in = AnimationUtils.loadAnimation(this, R.anim.right_in);
		right_out = AnimationUtils.loadAnimation(this, R.anim.right_out);
	}

	private void prepareView() {
		mBut1 = (ImageView) findViewById(R.id.imageView1);
		mBut2 = (ImageView) findViewById(R.id.imageView2);
		mBut3 = (ImageView) findViewById(R.id.imageView3);
		mBut4 = (ImageView) findViewById(R.id.imageView4);
		mBut5 = (ImageView) findViewById(R.id.imageView5);
		findViewById(R.id.channel1).setOnClickListener(this);
		findViewById(R.id.channel2).setOnClickListener(this);
		findViewById(R.id.channel3).setOnClickListener(this);
		findViewById(R.id.channel4).setOnClickListener(this);
		findViewById(R.id.channel5).setOnClickListener(this);
		mCateText1 = (TextView) findViewById(R.id.textView1);
		mCateText2 = (TextView) findViewById(R.id.textView2);
		mCateText3 = (TextView) findViewById(R.id.textView3);
		mCateText4 = (TextView) findViewById(R.id.textView4);
		mCateText5 = (TextView) findViewById(R.id.textView5);
	}

	private void prepareIntent() {
		mHomeItent = new Intent(this, HomeActivity.class);
		mChannelIntent = new Intent(this, DataActivity.class);
		mAccountIntent = new Intent(this, DrivingEvaluatingActivity.class);
		mSearchIntent = new Intent(this, SavingHelperActivity.class);
		mMoreIntent = new Intent(this, MoreActivity.class);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			mBut1.performClick();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void setupIntent() {
		mTabHost = getTabHost();
		mTabHost.addTab(buildTabSpec(TAB_TAG_HOME, R.string.category_home,
				R.drawable.icon_1_n, mHomeItent));
		mTabHost.addTab(buildTabSpec(TAB_TAG_CHANNEL,
				R.string.category_data, R.drawable.icon_2_n, mChannelIntent));
		mTabHost.addTab(buildTabSpec(TAB_TAG_SEARCH, R.string.category_saving,
				R.drawable.icon_3_n, mSearchIntent));
		mTabHost.addTab(buildTabSpec(TAB_TAG_ACCOUNT,
				R.string.category_driving, R.drawable.icon_4_n, mAccountIntent));
		mTabHost.addTab(buildTabSpec(TAB_TAB_MORE, R.string.category_more,
				R.drawable.icon_5_n, mMoreIntent));
	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return mTabHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}

	public static void setCurrentTabByTag(String tab) {
		mTabHost.setCurrentTabByTag(tab);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (mCurTabId == v.getId()) {
			return;
		}
		mBut1.setImageResource(R.drawable.icon_1_n);
		mBut2.setImageResource(R.drawable.icon_2_n);
		mBut3.setImageResource(R.drawable.icon_3_n);
		mBut4.setImageResource(R.drawable.icon_4_n);
		mBut5.setImageResource(R.drawable.icon_5_n);
		mCateText1.setTextColor(COLOR1);
		mCateText2.setTextColor(COLOR1);
		mCateText3.setTextColor(COLOR1);
		mCateText4.setTextColor(COLOR1);
		mCateText5.setTextColor(COLOR1);
		int checkedId = v.getId();
		final boolean o;
		if (mCurTabId < checkedId)
			o = true;
		else
			o = false;
		if (o)
			mTabHost.getCurrentView().startAnimation(left_out);
		else
			mTabHost.getCurrentView().startAnimation(right_out);
		switch (checkedId) {
		case R.id.channel1:
			mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
			mBut1.setImageResource(R.drawable.icon_1_c);
			mCateText1.setTextColor(COLOR2);
			break;
		case R.id.channel2:
			mTabHost.setCurrentTabByTag(TAB_TAG_CHANNEL);
			mBut2.setImageResource(R.drawable.icon_2_c);
			mCateText2.setTextColor(COLOR2);
			break;
		case R.id.channel3:
			mTabHost.setCurrentTabByTag(TAB_TAG_SEARCH);
			mBut3.setImageResource(R.drawable.icon_3_c);
			mCateText3.setTextColor(COLOR2);
			break;
		case R.id.channel4:
			mTabHost.setCurrentTabByTag(TAB_TAG_ACCOUNT);
			mBut4.setImageResource(R.drawable.icon_4_c);
			mCateText4.setTextColor(COLOR2);
			break;
		case R.id.channel5:
			mTabHost.setCurrentTabByTag(TAB_TAB_MORE);
			mBut5.setImageResource(R.drawable.icon_5_c);
			mCateText5.setTextColor(COLOR2);
			break;
		default:
			break;
		}

		if (o)
			mTabHost.getCurrentView().startAnimation(left_in);
		else
			mTabHost.getCurrentView().startAnimation(right_in);
		mCurTabId = checkedId;
	}

	/*----------------��ʽ�˵�----------------------------*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
	//���������Ҫ�Ĳ˵��ɡ�
		return super.onOptionsItemSelected(item);
	}
}
