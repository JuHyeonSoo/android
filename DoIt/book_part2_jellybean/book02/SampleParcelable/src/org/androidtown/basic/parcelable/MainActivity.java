package org.androidtown.basic.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ����Ʈ�� �̿��� ������ �� Parcelable ��ü�� ����� �����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 *
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * ��û �ڵ� ����
	 */
	public static final int REQUEST_CODE_ANOTHER = 1001;

	/**
	 * �ΰ� �������� Ű �� ����
	 */
	public static final String KEY_SIMPLE_DATA = "data";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showBtn = (Button) findViewById(R.id.showBtn);
		
		// ��ư�� ������ �� ���ο� ��Ƽ��Ƽ�� ����ݴϴ�.
		showBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {			
    			// ����Ʈ ��ü�� ����ϴ�.
    			Intent intent = new Intent(getBaseContext(), AnotherActivity.class);
    			
    			SimpleData data = new SimpleData(100, "Hello Android!");
        		intent.putExtra(KEY_SIMPLE_DATA, data);
        		
    			// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
   				startActivityForResult(intent, REQUEST_CODE_ANOTHER);
				
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
		}

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
