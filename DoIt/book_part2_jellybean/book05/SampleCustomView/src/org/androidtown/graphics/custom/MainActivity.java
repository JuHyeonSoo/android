package org.androidtown.graphics.custom;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * �並 ����Ͽ� ���ο� �並 ����� ����� ���� �� �� �ֽ��ϴ�.
 * ���� ��Ƽ��Ƽ�� ȭ�鿡�� XML���̾ƿ����� ���� ���� �ƴ� ���� ���� �並 �����մϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // ���� ���� �� ȭ�鿡 �����ϱ�
        CustomView myView = new CustomView(this);
        setContentView(myView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
