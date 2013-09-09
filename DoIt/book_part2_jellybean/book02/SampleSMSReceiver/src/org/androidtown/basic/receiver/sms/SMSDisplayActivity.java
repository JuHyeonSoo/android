package org.androidtown.basic.receiver.sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * SMS�� �������� �� ȭ�鿡 �������� ��Ƽ��Ƽ�Դϴ�.
 * 
 * @author Mike
 */
public class SMSDisplayActivity extends Activity {

	Button titleButton;
	Button closeButton;
	TextView messageTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ÿ��Ʋ ������ �Ⱥ��̰� �մϴ�.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.sms_display);

        // ���̾ƿ��� ��ü���� �����մϴ�.
        titleButton = (Button) findViewById(R.id.titleButton);
        closeButton = (Button) findViewById(R.id.closeButton);
        messageTextView = (TextView) findViewById(R.id.messageTextView);

        // �ݱ� ��ư�� ������ ȭ���� �ݽ��ϴ�.
        closeButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        	}
        });
 
   	 	// ���޵� ����Ʈ�� ó���մϴ�.
   	 	Intent passedIntent = getIntent();
   	 	if (passedIntent != null) {
   	 		processIntent(passedIntent);
   	 	}
    }

    /**
     * �� ��Ƽ��Ƽ�� SINGLE_TOP �÷��׷� �Ǿ� ������ �� �޼ҵ�� ����Ʈ�� ���޵˴ϴ�.
     */
	protected void onNewIntent(Intent intent) {
		// ���޵� ����Ʈ�� ó���մϴ�.
		processIntent(intent);

		super.onNewIntent(intent);
	}

	/**
	 * ���޵� ����Ʈ�� ó���մϴ�.
	 * 
	 * @param intent
	 */
    private void processIntent(Intent intent) {
    	// �߽� ��ȣ
    	String number = intent.getStringExtra("number");
    	// ���� ����
    	String message = intent.getStringExtra("message");
    	
    	// �ð�
    	String timestamp = intent.getStringExtra("timestamp");

    	if (number != null) {
	    	titleButton.setText(number + " ���� ���� ����");
	    	messageTextView.setText("[" + timestamp + "] " + message);

	    	messageTextView.invalidate();
    	}
    }

}