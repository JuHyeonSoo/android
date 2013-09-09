package org.androidtown.graphics.custom.title;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * ������ ǥ���ϴ� �並 ���� ����� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Ÿ��Ʋ �κп� �ؽ�Ʈ ����
        CustomViewTitle titleView = (CustomViewTitle) findViewById(R.id.titleView);
        titleView.setTitleText("���Ͽ� �ø��� ��");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
