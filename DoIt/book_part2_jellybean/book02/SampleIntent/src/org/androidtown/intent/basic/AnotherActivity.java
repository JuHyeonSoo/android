package org.androidtown.intent.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ���ο� ��Ƽ��Ƽ
 * 
 * @author Mike
 */
public class AnotherActivity extends Activity {
	Button backBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

        backBtn = (Button) findViewById(R.id.backBtn);
		
		// ��ư�� ������ �� ���� ��Ƽ��Ƽ�� ���ư��ϴ�.
		backBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
        		// ��ü�� ����ϴ�.
        		Intent resultIntent = new Intent();
        		resultIntent.putExtra("name", "mike");

                // ������ �����ϰ� �� ��Ƽ��Ƽ�� �����մϴ�.
        		setResult(RESULT_OK, resultIntent);
                finish();
			}
		});

    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
