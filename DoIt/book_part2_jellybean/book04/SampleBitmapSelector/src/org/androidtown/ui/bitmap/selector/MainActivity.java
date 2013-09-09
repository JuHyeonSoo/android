package org.androidtown.ui.bitmap.selector;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * ��Ʈ�� Selector�� �̿��� ��Ʈ�� ��ư�� ����� ����� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ÿ��Ʋ �κ� ���ֱ�
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        // ��ư �̺�Ʈ ó��
        Button arrowLeftBtn = (Button)findViewById(R.id.arrowLeftBtn);
        arrowLeftBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "��ư�� ���Ⱦ��.", Toast.LENGTH_SHORT).show();
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
