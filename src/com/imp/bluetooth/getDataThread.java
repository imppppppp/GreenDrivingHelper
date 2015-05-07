package com.imp.bluetooth;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class getDataThread extends Thread{

	public static final int RESPONSE_OK = 0x00;
	public static final int RESPONSE_OBD_RT = 0x01;
	public static final int RESPONSE_OBD_AMT = 0x02;
	public static final int RESPONSE_OBD_HBT = 0x03;
	public static final int RESPONSE_OBD_DVC = 0x04;
	public static final int RESPONSE_OBD_DTC = 0x05;
	public static final int RESPONSE_OBD_RTC = 0x06;
	public static final int RESPONSE_OBD_RTC_SET_OK = 0x07;
	public static final int RESPONSE_OBD_HIS_RECORD = 0x08;
	public static final int RESPONSE_OBD_HIS_RECORD_SEND_OK = 0x09;
		
	private static String commData = ""; //��est�ش���ָ��
	private static String cmdUser = "";//�û�����ָ��
	
	private Handler mHandler;
	
	private boolean isStop;
	
	public getDataThread(Handler handler)
	{
		this.mHandler = handler;
		isStop = false;
	}
	public synchronized static String getcmdUser(){
		return cmdUser;
	}
	public synchronized static void setcmdUser(String cmd){
		cmdUser = cmd;
	}
	
	@Override
	public void run() {
		Looper.prepare();
		// TODO Auto-generated method stub	
		isStop = true;
		while(true){
			if (!isStop) break;
			try {
				sleep(20L);
				
				if ((getCommData().trim() == "") || (getCommData() == null)) continue;
				
				String ReceiveBuffer = getCommData();	
				String sResponseType;
				String strArray[] = getCommData().split(",");
				Message msg = null;
								
				//����ʵʱ����
				if ((strArray[0].equals("$OBD-RT")) && (strArray.length == 11))
				{
					msg = mHandler.obtainMessage(RESPONSE_OBD_RT);
					sResponseType = "RPS_RT";
				}
				//����ͳ�����ݣ�Ĭ�ϴ򿪣��ر�ָ��"ATOFF"
				else if((strArray[0].equals("$OBD-AMT")) && (strArray.length == 8))
				{
					msg = mHandler.obtainMessage(RESPONSE_OBD_AMT);
					sResponseType = "RPS_AMT";
				}
				//��ʻϰ�����ݣ�����"ATHBT"ָ��
				else if((strArray[0].equals("$OBD-HBT")) && (strArray.length == 10))
				{
					msg = mHandler.obtainMessage(RESPONSE_OBD_HBT);
					sResponseType = "RPS_HBT";
				}
				//�豸��Ϣ�����ݣ����͡�ATI��ָ��
				else if ( (strArray[0].equals("$iEST527")) && (strArray.length == 6))
				{
					msg = mHandler.obtainMessage(RESPONSE_OBD_DVC);
					sResponseType = "RPS_DVC";
				}
				//����������ݣ����͡�ATDTC��ָ��
				else if ( (strArray[0].equals("$OBD-DTC")) && (strArray.length == 3))
				{
					msg = mHandler.obtainMessage(RESPONSE_OBD_DTC);
					sResponseType = "RPS_DTC";
				}
				//��ȡ��ǰʱ�䣬���͡�ATNOW��ָ��
				else if ( (strArray[0].equals("$OBD-RTC")) && (strArray.length == 3))
				{						
					msg = mHandler.obtainMessage(RESPONSE_OBD_RTC);
					sResponseType = "RPS_RTC";
				}
				//����RTCʱ��
				else if ((strArray[0].equals("$iEST527")) && (strArray.length == 2) )
				{
					if((strArray[1].contains("SET DATE OK")) || (strArray[1].contains("SET TIME OK")) )
					{
						msg = mHandler.obtainMessage(RESPONSE_OBD_RTC_SET_OK);
						sResponseType = "RTC_SET_OK";
					}
					else
					{
						msg = mHandler.obtainMessage(RESPONSE_OK);
						sResponseType = "RPS_OK";
					}
				}
				//�յ���ʷ��¼��Ϣ
				else if ( (strArray[0].equals("$OBD-HIS")) && (strArray.length == 15))
				{							
					msg = mHandler.obtainMessage(RESPONSE_OBD_HIS_RECORD);
					//Log.d("getDataThread", ReceiveBuffer);
					sResponseType = "RPS_HIS_RECORD";
				}
				//��ʷ��¼��Ϣ�������
				else if ( (strArray[0].equals("$iEST527")) && (strArray.length == 3))
				{											
					msg = mHandler.obtainMessage(RESPONSE_OBD_HIS_RECORD_SEND_OK);
					//Log.d("getDataThread", ReceiveBuffer);
					sResponseType = "RPS_HIS_SENDOK";					
				}
				else {
					msg = mHandler.obtainMessage(RESPONSE_OK);
					sResponseType = "RPS_OK";
				}
				
				//������Ϣ������UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				msg.sendToTarget();
				
				//��ջ���
				setCommData("");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Looper.loop();
	}
	
	public boolean getCurState()
	{
		return isStop;
	}
	
	//ֹͣ�߳�
	public void cancel() {		
		isStop = false;
		setCommData("");
	}
	
	/**
	 * ��ȡ��������
	 */
	public synchronized static String getCommData(){
		return commData;
	}
	
	/**
	 * ���ý��մ�������
	 * @param value
	 */
	public synchronized static void setCommData(String value){
		commData = value;
	}
	
	public static String cutString(String src){
		if ((src.trim() == "") || (src == null)) return "";
		
		return src.substring(src.indexOf("=") + 1);
	}
}
