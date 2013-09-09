package org.androidtown.graphics.custom.drawables;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * ���� ���� �� ���� �پ��� �׷��� ��ҵ��� �׸��� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // ���� ���� �並 ȭ�鿡 ����
        CustomViewDrawables myView = new CustomViewDrawables(this);
        setContentView(myView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
