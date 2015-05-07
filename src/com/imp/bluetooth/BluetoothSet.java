package com.imp.bluetooth;

import java.util.ArrayList;

import com.imp.home.HomeActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class BluetoothSet {
	//private BluetoothAdapter mBtAdapter = null;
	//change
	public static BluetoothAdapter mBtAdapter = null;
	public static int mState;//��������״̬
	private Boolean isBusy;//�����Ƿ�æµ
	private ProgressDialog impDialog = null;//������
	private Context impContext;//������
	private boolean is_bonded;
//	private static final int ENABLE_BLUETOOTH = 1;
	private String dStarted = BluetoothAdapter.ACTION_DISCOVERY_STARTED;
	private String dFinished = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
	public static final int MESSAGE_READ = 1;//2
	
	private String[]s_temp = null;
	private ArrayList<BluetoothDevice> deviceList = new ArrayList<BluetoothDevice>();
	public static BluetoothDevice device = null;
	public BluetoothSet(Context impContext,BluetoothAdapter bluetooth) {
		// TODO Auto-generated constructor stub
		this.impContext = impContext;
		BluetoothSet.mBtAdapter = bluetooth;
	}
	
	/*�ж��Ƿ�֧�ֱ��������豸*/
	public Boolean isSupported(){
		if (mBtAdapter == null)
			return false;		
		else		
			return true;
	}
	
	/*�ж������Ƿ�������----------------------------------------*/
//	public Boolean isConnected(){
//		if(mBtService.getState() == BluetoothService.STATE_CONNECTED){
//			return true;
//		}
//		else{
//			return false;
//		}
//	}
	
	/*������������״̬*/
	private synchronized void setState(int state) {
        mState = state;
    }

	
	/*���������Ƿ�ע��------------------------------------------*/
	
	/* ��ȡ��������״̬��trueΪæµ*/
	public synchronized boolean getIsBusy(){
		return isBusy;
	}
	
	/*���ô�������״̬��trueΪæµ*/
	public synchronized void setIsBusy(boolean status){
		this.isBusy = status;
	}
	
	/*�򿪱�������*/
	public void openBluetooth(){
		//��û��
		if (!mBtAdapter.isEnabled()){	
			//iimpDialog = ProgressDialog.show(impContext, "��ʾ", "��������...",false);
		    Log.v("MY","����δ����");
		    //iimpDialog.cancel();
		    //impContext.startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
		    mBtAdapter.enable();
			Log.v("MY","���������ɹ�");
		    Toast.makeText(impContext, "����������", Toast.LENGTH_SHORT).show();
//		    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), ENABLE_BLUETOOTH);
//			new OpenBluetoothThread().start();
		}
		//���Ѵ�
		else
		{
			//��ʾ�򿪱�������ʧ�ܡ�ע�����
//			registerService();
			Toast.makeText(impContext, "�����ѿ���", Toast.LENGTH_SHORT).show();
		}
	}
	
	
    //������Χ�����豸��
	public void findBluetooth(){
//		if (!mBtAdapter.isEnabled()){
//			mBtAdapter.enable();
//		}
		//��ʼ��ѯ
		mBtAdapter.startDiscovery();
		impDialog = new ProgressDialog(impContext);  
        impDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//���÷��ΪԲ�ν�����  
        impDialog.setTitle("��ʾ");//���ñ���  
        impDialog.setMessage("���������ܱ������豸...");  
        impDialog.setIndeterminate(false);//���ý������Ƿ�Ϊ����ȷ  
        impDialog.setCancelable(true);//���ý������Ƿ���԰��˻ؼ�ȡ��  
        impDialog.show();     
		impContext.registerReceiver(discoveryMonitor, new IntentFilter(dStarted));
		impContext.registerReceiver(discoveryMonitor, new IntentFilter(dFinished));
		impContext.registerReceiver(discoveryResult, 
				new IntentFilter(BluetoothDevice.ACTION_FOUND));
	}
	
    //������
	public boolean bindBluetooth(){
		Log.i("MY","toGetRemote");
		device = mBtAdapter.getRemoteDevice(DeviceListActivity.getMacAddress());
		Log.i("MY","GetRemote");
		try{
<<<<<<< HEAD

			//��δ���
			if(device.getBondState()!=BluetoothDevice.BOND_BONDED){
				is_bonded = ClsUtils.createBond(device.getClass(), device);
				Log.i("MY","�����Ƿ��Ѱ�:"+mBtAdapter.getState()+is_bonded);
			}
			//�����
		    else{
				//ClsUtils.removeBond(device.getClass(), device);
		    	is_bonded = ClsUtils.createBond(device.getClass(), device);
		    	is_bonded = ClsUtils.createBond(device.getClass(), device);
		    	Log.i("MY","�����Ƿ��Ѱ�:"+mBtAdapter.getState()+!is_bonded);
		    	is_bonded = !is_bonded;
			}
			
=======
//			if(mBtAdapter.getState() == BluetoothAdapter.STATE_CONNECTED){
//				flag = true;
//			}
//			else{
//				//ClsUtils.removeBond(device.getClass(), device);
				flag = ClsUtils.createBond(device.getClass(), device);
//			}
			Log.i("MY","flag's state:"+mBtAdapter.getState());
>>>>>>> eb6c9e95a56e9e2fd53008537f2a3d78523b1ef4
		}catch(Exception e){
			Log.i("MY","getRemoteException");
			e.printStackTrace();
		}
		return is_bonded;
	}
	
	/*���������豸*/
	public void setConnect(){				
				Log.v("MY","get RemoteSocket");
				try {
//					mBluetoothSocket mbt = new mBluetoothSocket(Tools.btAdapter,device);
					new mBluetootServer();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.v("MY",e.getMessage());
					e.printStackTrace();
				}
			
	}
	
	
	
	/////////////////////////////
	BroadcastReceiver discoveryMonitor = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(dStarted.equals(intent.getAction()) ){
				Log.v("MY","Discovery Started");
			}
			else if(dFinished.equals(intent.getAction())){
				deviceList.clear();
				Log.v("MY","Discovery Completed");
				DeviceListActivity.setdata(s_temp);
				Log.v("MY","s.toString == "+s_temp.toString());
				impDialog.cancel();
				Intent intent1 = new Intent(impContext,DeviceListActivity.class);
				impContext.startActivity(intent1);
			}
		}
	};
	
BroadcastReceiver discoveryResult = new BroadcastReceiver(){
		
		public void onReceive(Context context, Intent intent) {
			
			String remoteDeviceName = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
			BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			if(!deviceList.contains(remoteDevice)){
				deviceList.add(remoteDevice);
			}
			StringBuilder str = new StringBuilder();
			try{
			for (BluetoothDevice reD : deviceList) {
				str.append(reD.getName()+","+reD.getAddress()+"\r\n");
			}
			s_temp = str.toString().split("\r\n");
			}catch(Exception e){}
			
		}
		
	};
	
	//////////////////
	
}
