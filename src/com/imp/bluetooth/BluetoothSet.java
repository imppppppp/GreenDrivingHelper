package com.imp.bluetooth;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BluetoothSet {
	private BluetoothAdapter mBtAdapter = null;
	private Boolean isBusy;//�����Ƿ�æµ
	private ProgressDialog impDialog = null;//������
	private Context impContext;//������
	private static final int ENABLE_BLUETOOTH = 1;
	public BluetoothSet(Context impContext,BluetoothAdapter bluetooth) {
		// TODO Auto-generated constructor stub
		this.impContext = impContext;
		this.mBtAdapter = bluetooth;
	}
	
	/*�ж��Ƿ�֧�ֱ��������豸*/
	public Boolean isSupported(){
		if (mBtAdapter == null)
			return false;		
		else		
			return true;
	}
	
	/*�ж������Ƿ�������----------------------------------------*/
	public Boolean isConnected(){
        return false;
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
			//impDialog = ProgressDialog.show(impContext, "��ʾ", "��������...",false);
			//Log.v("MY","����Progress");
		    Log.v("MY","����bluetooth");
		    //impDialog.cancel();
		    impContext.startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
		    mBtAdapter.enable();
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
	
}
