package org.androidtown.basic.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * ���� Ŭ���� ����
 * 
 * @author Mike
 */
public class MyService extends Service implements Runnable {
	/**
	 * ������� ���� �±�
	 */
	public static final String TAG = "MyService";
	
	/**
	 * �ݺ� Ƚ��
	 */
	private int count = 0;
	
	/**
	 * ���� ��ü ���� �� �ڵ� ȣ��˴ϴ�.
	 */
	public void onCreate() {
		super.onCreate();
		
		// �����带 �̿��� �ݺ��Ͽ� �α׸� ����մϴ�.
		Thread myThread = new Thread(this);
		myThread.start();
	}

	/**
	 * �������� ���� �κ�
	 */
	public void run() {
		while(true) {
			try {
				Log.i(TAG, "my service called #" + count);
				count++;
				
				Thread.sleep(5000);
			} catch(Exception ex) {
				Log.e(TAG, ex.toString());
			}
		}
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
