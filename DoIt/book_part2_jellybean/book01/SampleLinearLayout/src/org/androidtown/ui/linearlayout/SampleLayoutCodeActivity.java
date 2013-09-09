package org.androidtown.ui.linearlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * �ڵ忡�� ���� ���̾ƿ��� ������
 * 
 * @author Mike
 *
 */
public class SampleLayoutCodeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // ���� ���̾ƿ�
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            	LinearLayout.LayoutParams.MATCH_PARENT,
            	LinearLayout.LayoutParams.WRAP_CONTENT);
        
        // ��ư �߰��Ͽ� ����
        Button button01 = new Button(this);
        button01.setText("Button 01");
        button01.setLayoutParams(params);
        mainLayout.addView(button01);

        // ȭ�� ����
        setContentView(mainLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
