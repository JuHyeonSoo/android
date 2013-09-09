package org.androidtown.graphics.custom.style;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * ���� ���� �� ���� �پ��� ��Ÿ���� �׷����� �׸��� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // ���� ���� �並 ȭ�鿡 ����
        CustomViewStyles myView = new CustomViewStyles(this);
        setContentView(myView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
