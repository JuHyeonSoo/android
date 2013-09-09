package org.androidtown.thread.java;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * �ڹٿ��� ����ϴ� �Ϲ����� ������ ����� �״�� ����� �� �ִٴ� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	private int value = 0;
	private boolean running = false;
	
	TextView textView01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView01 = (TextView) findViewById(R.id.textView01);
        
        // ��ư �̺�Ʈ ó��
        Button showBtn = (Button) findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		textView01.setText("�����忡�� ���� �� : " + value);
        	}
        });
    }

    /**
     * ȭ�� ���� �� ������ ����
     */
	protected void onResume() {
		super.onResume();

		running = true;
		Thread thread1 = new BackgroundThread();
        thread1.start();
	}

	/**
	 * ȭ�� �Ⱥ��� �� ������ ����
	 */
	protected void onPause() {
		super.onPause();

		running = false;
		value = 0;
	}

	/**
	 * ������ ����
	 */
	class BackgroundThread extends Thread {
    	public void run() {
    		while(running) {
    			try {
    				// 1�ʸ��� �ѹ��� ���� ������Ŵ
    				Thread.sleep(1000);
    				value++;
    				
    			} catch(InterruptedException ex) {
    				Log.e("SampleJavaThread", "Exception in thread.", ex);
    			}
    		}
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
