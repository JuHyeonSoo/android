package org.androidtown.basic.inflater;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ���̾ƿ� ���÷��̼� ������ ��ü�� ������ �� ������ �߻��ϴ� ���� Ȯ���� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class SampleInflationErrorActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
        
        setContentView(R.layout.activity_main);

    }

}
