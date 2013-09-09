package org.androidtown.intent.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ����Ʈ�� �̿��� ���ο� ��Ƽ��Ƽ�� ���� �ٽ� ���ƿ��� ����� ���� �� �� �ֽ��ϴ�.
 *
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * ��û �ڵ� ����
	 */
	public static final int REQUEST_CODE_ANOTHER = 1001;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showBtn = (Button) findViewById(R.id.showBtn);
		
		// ��ư�� ������ �� ���ο� ��Ƽ��Ƽ�� ����ݴϴ�.
		showBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {			
    			// ����Ʈ ��ü�� ����� ��� #1
				
				// ����Ʈ ��ü�� ����ϴ�.
    			Intent intent = new Intent(getBaseContext(), AnotherActivity.class);
    			
    			// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
   				startActivityForResult(intent, REQUEST_CODE_ANOTHER);
			
   				// ����Ʈ ��ü�� ����� ��� #2
        		//Intent intent = new Intent();
    			//ComponentName name = new ComponentName("org.androidtown.intent.basic", "org.androidtown.intent.basic.AnotherActivity");
    			//intent.setComponent(name);
    			
    			//startActivityForResult(intent, REQUEST_CODE_ANOTHER);
    			
			}
		});

    }

    /**
     * ���ο� ��Ƽ��Ƽ���� ���ƿ� �� �ڵ� ȣ��Ǵ� �޼ҵ�
     */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_ANOTHER) {
			Toast toast = Toast.makeText(getBaseContext(), "onActivityResult() �޼ҵ尡 ȣ���. ��û�ڵ� : " + requestCode + ", ����ڵ� : " + resultCode, Toast.LENGTH_LONG);
			toast.show();

			if (resultCode == RESULT_OK) {
				String name = data.getExtras().getString("name");
				toast = Toast.makeText(getBaseContext(), "�������� ���޵� name : " + name, Toast.LENGTH_LONG);
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
