package org.androidtown.thread.looper;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * ���۸� ����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	TextView textView01, textView02;
	EditText editText01, editText02;

	/**
	 * ���� �������� �ڵ鷯
	 */
	MainHandler mainHandler;
	
	/**
	 * ���� ���� ������
	 */
	ProcessThread thread1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new MainHandler();
		thread1 = new ProcessThread();

        textView01 = (TextView) findViewById(R.id.textView01);
        textView02 = (TextView) findViewById(R.id.textView02);
        editText01 = (EditText) findViewById(R.id.editText01);
        editText02 = (EditText) findViewById(R.id.editText02);

        // ��ư �̺�Ʈ ó��
        Button processBtn = (Button) findViewById(R.id.processBtn);
        processBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		String inStr = editText01.getText().toString();
        		Message msgToSend = Message.obtain();
        		msgToSend.obj = inStr;

        		thread1.handler.sendMessage(msgToSend);
        	}
        });

        thread1.start();
    }

    /**
     * ���� ������ ������
     */
    class ProcessThread extends Thread {
    	// ���ο� �����带 ���� �ڵ鷯
    	ProcessHandler handler;

    	public ProcessThread() {
    		handler = new ProcessHandler();
    	}

    	public void run() {
    		// ���� ���
    		Looper.prepare();
    		Looper.loop();
    	}

    }

    class ProcessHandler extends Handler {
    	public void handleMessage(Message msg) {
    		Message resultMsg = Message.obtain();
    		resultMsg.obj = msg.obj + " Mike!!!";

    		mainHandler.sendMessage(resultMsg);
    	}
    }

    class MainHandler extends Handler {
    	public void handleMessage(Message msg) {
    		String str = (String) msg.obj;
    		editText02.setText(str);
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
