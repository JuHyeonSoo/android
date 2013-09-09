package org.androidtown.event.keyevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ��ư�� ������ �ٸ� ��Ƽ��Ƽ�� ����ְ� �� ��Ƽ��Ƽ���� BACK Ű�� ������ �� ���ƿ��� ����� �� �� �ִ� ������Ʈ
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	/**
	 * �ٸ� ��Ƽ��Ƽ�� ���� ���� ������ ��û �ڵ�
	 */
	public static final int REQUEST_CODE_ANOTHER = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ��ư Ŭ�� �̺�Ʈ ó��
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		// �ٸ� ��Ƽ��Ƽ ����
    			Intent intent = new Intent(getBaseContext(), AnotherActivity.class);
   				startActivityForResult(intent, REQUEST_CODE_ANOTHER);
        	}
        });

        // ��ư ������ ���������� ����
        startButton.setBackgroundColor(0xffff0000);
        
        // ��ư ���ڸ� �Ͼ������ ����
        startButton.setTextColor(0xffffffff);

    }


    /**
     * �ٸ� ��Ƽ��Ƽ���� ������ �޾��� �� ó��
     */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_ANOTHER) {
			Toast toast = Toast.makeText(getBaseContext(), "onActivityResult called with code : " + resultCode, Toast.LENGTH_LONG);
			toast.show();

			if (resultCode == Activity.RESULT_OK) {
				String name = data.getExtras().getString("name");
				toast = Toast.makeText(getBaseContext(), "�ٸ� ��Ƽ��Ƽ���� ���޹��� �̸� : " + name, Toast.LENGTH_LONG);
				toast.show();
			}

		}

	}

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
