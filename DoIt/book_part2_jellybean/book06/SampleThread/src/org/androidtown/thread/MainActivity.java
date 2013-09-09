package org.androidtown.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * �����带 �̿��� ���α׷����ٸ� �����ִ� ����� ���� �� �� �ֽ��ϴ�.
 * ������ ���� �����忡�� ���� �����带 ������ �� �ڵ鷯�� ����ؾ� �Ѵٴ� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	/**
	 * ���α׷�����
	 */
	ProgressBar bar;
	TextView textView01;
	boolean isRunning = false;
	
	/**
	 * ���� �������� UI�� �����ϱ� ���� �ڵ鷯
	 */
	ProgressHandler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar) findViewById(R.id.progress);
		textView01 = (TextView) findViewById(R.id.textView01);

		handler = new ProgressHandler();
    }


	public void onStart() {
		super.onStart();

		bar.setProgress(0);
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					for (int i = 0; i < 20 && isRunning; i++) {
						Thread.sleep(1000);

						Message msg = handler.obtainMessage();
						handler.sendMessage(msg);
					}
				} catch (Exception ex) {
					Log.e("SampleThreadActivity", "Exception in processing message.", ex);
				}
			}
		});

		isRunning = true;
		thread1.start();
	}

	public void onStop() {
		super.onStop();

		isRunning = false;
	}


	public class ProgressHandler extends Handler {

		public void handleMessage(Message msg) {

			bar.incrementProgressBy(5);

			if (bar.getProgress() == bar.getMax()) {
				textView01.setText("Done");
			} else {
				textView01.setText("Working ..." + bar.getProgress());
			}

		}

	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
