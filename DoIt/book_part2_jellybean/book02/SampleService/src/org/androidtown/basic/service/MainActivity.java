package org.androidtown.basic.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ���� ��ü�� ����� �����ϴ� ����� �� �� �ֽ��ϴ�.
 * 
 * ���񽺴� ���ø����̼� ��������̹Ƿ� �Ŵ��佺Ʈ�� ����ϴ� ���� �������ƾ� �մϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button startButton = (Button) findViewById(R.id.startButton);
		
		// ��ư�� ������ �� ���� ����
        startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ����Ʈ ��ü�� ����ϴ�.
		        Intent myIntent = new Intent(getBaseContext(), MyService.class);
		        
		        // ���񽺸� �����մϴ�.
		        startService(myIntent);

			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
