package org.androidtown.basic.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ���ο� ��Ƽ��Ƽ
 * 
 * @author Mike
 */
public class AnotherActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

        Button backBtn = (Button) findViewById(R.id.backBtn);
		
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
		
		Toast.makeText(this, "���ο� ��Ƽ��Ƽ�� onDestroy() ȣ���.", Toast.LENGTH_LONG).show();
	    
    }


    
    @Override
	protected void onDestroy() {
    	Toast.makeText(this, "���ο� ��Ƽ��Ƽ�� onDestroy() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		Toast.makeText(this, "���ο� ��Ƽ��Ƽ�� onPause() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onPause();
	}


	@Override
	protected void onRestart() {
		Toast.makeText(this, "���ο� ��Ƽ��Ƽ�� onRestart() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onRestart();
	}


	@Override
	protected void onResume() {
		Toast.makeText(this, "���ο� ��Ƽ��Ƽ�� onResume() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onResume();
	}


	@Override
	protected void onStart() {
		Toast.makeText(this, "���ο� ��Ƽ��Ƽ�� onStart() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onStart();
	}


	@Override
	protected void onStop() {
		Toast.makeText(this, "���ο� ��Ƽ��Ƽ�� onStop() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onStop();
	}

	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
