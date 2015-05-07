package com.imp.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.util.Log;

public class mBluetoothSocket {
	
	private BluetoothAdapter bluetooth;
	private boolean listening = false;
	//public static BluetoothServerSocket transferSocket;
	private BluetoothSocket transferSocket_client;
    private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String name = "OBDDTC";
	public mBluetoothSocket(BluetoothAdapter bluetooth,BluetoothDevice device) {
		// TODO Auto-generated constructor stub
		startServerSocket(bluetooth);
		Log.v("MY","UUID:"+uuid.toString());
		connectToServerSocket(device, uuid);
	}
	
	private void startServerSocket(final BluetoothAdapter bluetooth) {

	      try {
	    	  Log.v("MY","startServerSocket1");
	        final BluetoothServerSocket btserver = 
	          bluetooth.listenUsingRfcommWithServiceRecord(name, uuid);
	        //transferSocket=btserver;
	        Log.v("MY","startServerSocket2");
	        
	        new Thread(new Runnable() {
	          public void run() {
	            try {
	              // Block until client connection established.
	              Log.v("MY","开始监听客户端请求");
	              BluetoothSocket serverSocket = btserver.accept();
	              Log.v("MY", "Service is opening");
	              // Start listening for messages.
//	              StringBuilder incoming = new StringBuilder();
	              listenForMessages(serverSocket);
	              // Add a reference to the socket used to send messages.
//	              transferSocket = serverSocket;
	            } catch (IOException e) {
	              Log.e("MY", "Server connection IO Exception", e);
	            }
	          }
	        }).start();
	     
	      } catch (IOException e) {
	        Log.e("MY", "Socket listener IO Exception", e);
	      }
	    }

	private void connectToServerSocket(final BluetoothDevice device,final UUID uuid) {
	    new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
			    	  Log.v("MY","connectServerSocket1");
			    	  int sdk = Build.VERSION.SDK_INT;
		          	Log.v("MY","SDK"+String.valueOf(sdk));
		          	BluetoothSocket clientSocket;
		          	if(sdk >= 10){
		          		 clientSocket 
		          		  = device.createInsecureRfcommSocketToServiceRecord(uuid);
	            	}else {
	            		 clientSocket 
				          = device.createRfcommSocketToServiceRecord(uuid);
					}     
			        
			        Log.v("MY","clientSocket"+clientSocket.toString());
			        Log.v("MY","connectServerSocket2");
			        // Block until server connection accepted.
			        clientSocket.connect();
			        Log.v("MY", "Client is asking for connection");
			        // Start listening for messages.
			      
//			        	StringBuilder incoming = new StringBuilder();
			        	listenForMessages(clientSocket);
//			        	Log.v("MY","receving:"+incoming.toString());
			        // Add a reference to the socket used to send messages.
//			        transferSocket_client = clientSocket;

			      } catch (IOException e) {
			        Log.e("MY", "Blueooth client I/O Exception", e);
			      }
			}
		}).start(); 
		
	    }
	

	private void listenForMessages(final BluetoothSocket socket) {
		listening = true;
		String result = "";
		new MyServerThread(socket,listening).start();
	}
}

class MyServerThread extends Thread{
//	private StringBuilder incoming;
//	private String receiveBuffer = "";
	private static boolean firstTime = true;
	final int bufferSize = 1024;
	private BluetoothSocket socket = null;
	private boolean listening = false;
	private InputStream instream;
	public MyServerThread(BluetoothSocket socket,boolean listening){
//		this.incoming = incoming;
		this.socket = socket;
		this.listening = listening;
		if(firstTime){
			new mBluetoothSend(socket,"ATSOFF\r\n").start();
			Log.v("MY","关闭AMT！");
			//new mBluetoothSend(socket,"ATHBT\r\n").start();
		//	new mBluetoothSend(socket).sendCommond("ATHBT\r\n");
			firstTime = false;
		}else{
			new mBluetoothSend(socket,"ATHBT\r\n").start();
		}
		
	}
	
	public void run(){
		
		try {
			instream = socket.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int bytesRead = -1;
		byte[] buffer_temp = new byte[bufferSize];
		String result_temp = "";
		while(listening){
		try {
			while (listening) {
				bytesRead = instream.read(buffer_temp);
				Log.v("MY", "收到串口数据: " + (new String(buffer_temp,0,bytesRead)) + "(" + String.valueOf(bytesRead) + ")");
				String tempString = new String(buffer_temp,0,bytesRead);
        		result_temp += tempString;
        		if (result_temp.endsWith("\r\n")){
        			String strArray[] = result_temp.split("\r\n");
        			for(String stemp:strArray){			        				
        				//发送显示
        				Log.v("MY","接收数据为："+stemp);
	    				sleep(10L);//这个方法存在于Thread类中。。所以，你如果
	    				//要用的话，可以在这里创建一个线程。
        			}
        			//初始化接收缓存数据
//        			receiveBuffer = "";
        			result_temp = "";
        		}
        		if (instream.available() == 0) break;	

				}	
				//socket.close();
//			Log.v("MY","receive:"+incoming.toString());
			} catch (IOException e) {
//				Log.e("MY", "disconnected.Message received failed.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	}
}
