package org.androidtown.thread.delayed;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * �ڵ鷯�� �̿��� ���� �ð��� ������Ű�� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	TextView textView01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView01 = (TextView) findViewById(R.id.textView01);
        
        // ��ư �̺�Ʈ ó��
        Button requestBtn = (Button) findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		request();
        	}
        });

    }

    /**
     * ��û �޼ҵ�
     */
    private void request() {
    	String title = "���� ��û";
    	String message = "�����͸� ��û�Ͻðڽ��ϱ�?";
    	String titleButtonYes = "��";
    	String titleButtonNo = "�ƴϿ�";

    	AlertDialog dialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo);
    	dialog.show();

    	textView01.setText("���� ������ ��û �� ...");
    }

    /**
     * ��û ��ȭ���� �����
     * 
     * @param title
     * @param message
     * @param titleButtonYes
     * @param titleButtonNo
     * @return
     */
    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message,
            CharSequence titleButtonYes, CharSequence titleButtonNo) {

		AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
		requestDialog.setTitle(title);
		requestDialog.setMessage(message);
		requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int i) {
				RequestHandler handler = new RequestHandler();
        		handler.sendEmptyMessageDelayed(0, 20);
			}
		});

		requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int i) {}
		});

		return requestDialog.show();
    }

    /**
     * ��û ������
     * @author michael
     *
     */
    class RequestHandler extends Handler {
    	public void handleMessage(Message msg) {
    		for (int k = 0; k < 10; k++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {}
			}
			textView01.setText("���� ������ ��û �Ϸ�.");
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
