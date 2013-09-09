package org.androidtown.intent.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ����Ʈ�� �̿��� ���ο� ��Ƽ��Ƽ�� ��� �� �÷��׸� ����ϴ� ����� ȿ���� ���� �� �� �ֽ��ϴ�.
 *
 * @author Mike
 */
public class MainActivity extends Activity {

	Button showBtn;
	TextView txtMsg;
	String msg;

	/**
	 * ��û �ڵ� ����
	 */
	public static final int REQUEST_CODE_ANOTHER = 1001;
	
	/**
	 * ���� Ƚ��
	 */
	public static int startCount = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		txtMsg = (TextView) findViewById(R.id.txtMsg);
		showBtn = (Button) findViewById(R.id.showBtn);
		
		// ��ư�� ������ �� ���ο� ��Ƽ��Ƽ�� ����ݴϴ�.
		showBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
   				startCount++;
   				
    			// ����Ʈ ��ü�� ����ϴ�.
    			Intent intent = new Intent(getBaseContext(), AnotherActivity.class);
    			
    			// startCount ���� �־��ݴϴ�.
    			intent.putExtra("startCount", startCount);
    			
    			// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
   				startActivityForResult(intent, REQUEST_CODE_ANOTHER);
			}
		});

		// ���޹��� ����Ʈ�� ó���մϴ�.
		processIntent();	
    }

    @Override
	protected void onNewIntent(Intent intent) {
    	// ���޹��� ����Ʈ�� ó���մϴ�.
    	processIntent();
		
		super.onNewIntent(intent);
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

    /**
     * ���ο� ��Ƽ��Ƽ���� ���ƿ� �� �ڵ� ȣ��Ǵ� �޼ҵ�
     */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_ANOTHER) {
			Toast toast = Toast.makeText(getBaseContext(), "onActivityResult() �޼ҵ尡 ȣ���. ��û�ڵ� : " + requestCode + ", ����ڵ� : " + resultCode, Toast.LENGTH_LONG);
			toast.show();
		}

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
