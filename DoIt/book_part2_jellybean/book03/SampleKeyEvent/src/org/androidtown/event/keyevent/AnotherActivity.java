package org.androidtown.event.keyevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * BACK Ű�� ������ �� �߻��ϴ� Ű �̺�Ʈ�� ó���ϴ� ����� �� �� �ִ� ��Ƽ��Ƽ
 * 
 * @author Mike
 *
 */
public class AnotherActivity extends Activity {

	/**
	 * �ٸ� ��Ƽ��Ƽ�� ���� ���� ������ ��û �ڵ�
	 */
	public static final int REQUEST_CODE_ANOTHER = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

        // ��ư�� ������ �� ���� ��Ƽ��Ƽ�� ���ư� �� �ֵ��� �޼ҵ� ȣ��
        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		close();
        	}
        });
   
    }

    /**
     * BACK Ű�� ������ �� �� ��Ƽ��Ƽ�� �ݰ� ���ư� �� �ֵ��� �޼ҵ� ȣ��
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode == KeyEvent.KEYCODE_BACK) {
        	close();
        	
			return true;
		}
		
        return false;
    }
    
    /**
     * ���� ��Ƽ��Ƽ�� ���ư����� �ϴ� �޼ҵ� ����
     */
    private void close() {
		// ������� �����ϱ� ���� ����Ʈ ��ü �����
		Intent resultIntent = new Intent();        		
        resultIntent.putExtra("name", "mike");

        // ����� ����
        setResult(Activity.RESULT_OK, resultIntent);
        
        // �� ��Ƽ��Ƽ ���ֱ�
        finish();
    }
    
}
