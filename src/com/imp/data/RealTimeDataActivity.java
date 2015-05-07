package com.imp.data;



import android.app.Activity;
import android.os.Bundle;
<<<<<<< HEAD
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.greendrivinghelper.R;
import com.imp.bluetooth.MyServerThread;
import com.imp.bluetooth.getDataThread;
import com.imp.bluetooth.mBluetoothSocket;

public class RealTimeDataActivity extends Activity{
	private Button bt_show;
	private Button bt_hide;
	//车辆实时数据
	private TextView txvBAT;
	private TextView txvRPM;
	private TextView txvVSS;
	private TextView txvTP;
	private TextView txvLOD;
	private TextView txvECT;
	private TextView txvMPG;
	private TextView txvAVM;
	private TextView txvFLI;
	private TextView txvDTN;
	
	private getDataThread mgetDataThread;
	
=======
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.greendrivinghelper.R;
import com.imp.bluetooth.MessageProcessing;

public class RealTimeDataActivity extends Activity{
	private Button showData;
>>>>>>> eb6c9e95a56e9e2fd53008537f2a3d78523b1ef4
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.real_time_data);
<<<<<<< HEAD
		initData();
		bt_show=(Button)findViewById(R.id.btn_showrealdata);
		bt_show.setOnClickListener(new OnClickListener() {
			@Override
				public void onClick(View v) {
				// TODO Auto-generated method stub
	//			new mBluetoothSend(mBluetoothSocket.transferSocket,"ATCDI\r\n").start();
	//			Log.i("MY","显示实时数据！发送ATCDI！---------");
	//			getDataThread.setCommData("");
				
				mgetDataThread = new getDataThread(mHandler);
				mgetDataThread.start();
				getDataThread.setcmdUser("ATHBT\r\n");
				new MyServerThread(mBluetoothSocket.transferSocket,true,mHandler).start();
			}
		});
    
    bt_hide=(Button)findViewById(R.id.btn_hiderealdata);
    bt_hide.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
    
=======
		showData = (Button) findViewById(R.id.btn_showrealtimedata);
		showData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MessageProcessing.setHandler("ATHBT\r\n");
			}
		});
>>>>>>> eb6c9e95a56e9e2fd53008537f2a3d78523b1ef4
	}

	private final Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle bundle = msg.getData();
			Log.i("MY","bundle.getString():"+bundle.getString("RPS_RT"));//--------------
			String strArray[] = null;
			switch (msg.what){
			
			case getDataThread.RESPONSE_OBD_RT:
				    Log.i("MY","进入switch！");
					String sRT = bundle.getString("RPS_RT");
					strArray = sRT.split(",");
					Log.i("MY","进行分割！");
					txvBAT.setText(getDataThread.cutString(strArray[1]));
					txvRPM.setText(getDataThread.cutString(strArray[2]));
					txvVSS.setText(getDataThread.cutString(strArray[3]));
					txvTP.setText(getDataThread.cutString(strArray[4]));
					txvLOD.setText(getDataThread.cutString(strArray[5]));
					txvECT.setText(getDataThread.cutString(strArray[6]));
					txvMPG.setText(getDataThread.cutString(strArray[7]));
					txvAVM.setText(getDataThread.cutString(strArray[8]));
					txvFLI.setText(getDataThread.cutString(strArray[9]));
					txvDTN.setText(getDataThread.cutString(strArray[10]));
				break;
			
			default:
				Log.i("MY","msg.what:"+msg.what);
				break;
			}
		}		
	};
	
	private void initData(){
		
		//车辆实时数据
		txvBAT = (TextView)findViewById(R.id.txv_bat_value);
		txvRPM = (TextView)findViewById(R.id.txv_rpm_value);
		txvVSS = (TextView)findViewById(R.id.txv_vss_value);
		txvTP = (TextView)findViewById(R.id.txv_tp_value);
		txvLOD = (TextView)findViewById(R.id.txv_lod_value);
		txvECT = (TextView)findViewById(R.id.txv_ect_value);
		txvMPG = (TextView)findViewById(R.id.txv_mpg_value);
		txvAVM = (TextView)findViewById(R.id.txv_avm_value);
		txvFLI = (TextView)findViewById(R.id.txv_fli_value);
		txvDTN = (TextView)findViewById(R.id.txv_dtn_value);
	}
	
}
