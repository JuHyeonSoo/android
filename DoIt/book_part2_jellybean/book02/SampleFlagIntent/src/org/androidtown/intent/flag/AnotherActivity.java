package org.androidtown.intent.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * ���ο� ��Ƽ��Ƽ
 * 
 * @author Mike
 */
public class AnotherActivity extends Activity {
	Button backBtn;
	TextView txtMsg;
	String msg;
	
	int startCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

		txtMsg = (TextView) findViewById(R.id.txtMsg);
		backBtn = (Button) findViewById(R.id.backBtn);
		
		// ��ư�� ������ �� ���� ��Ƽ��Ƽ�� �ٽ� �� �� ����ݴϴ�.
		backBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
        		// ��ü�� ����� ���� ��Ƽ��Ƽ�� �����մϴ�.
        		Intent newIntent = new Intent(getBaseContext(), MainActivity.class);
        		newIntent.putExtra("startCount", startCount);
        		newIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // ���� ��Ƽ��Ƽ�� �ٽ� ���ϴ�.
                startActivity(newIntent);
			}
		});

		// ���޹��� ����Ʈ�� ó���մϴ�.
		processIntent();
    }

    /**
     * ���޹��� ����Ʈ�� ó���ϴ� �޼ҵ� ����
     */
    private void processIntent() {
		// ����Ʈ ��ü�� Ȯ���մϴ�.
	    Intent receivedIntent = getIntent();
	    startCount = receivedIntent.getIntExtra("startCount", 0);
	
	    // �ؽ�Ʈ�信 startCount ���� �����ݴϴ�.
	    msg = "���޵� startCount : " + startCount;
	    txtMsg.setText(msg);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
