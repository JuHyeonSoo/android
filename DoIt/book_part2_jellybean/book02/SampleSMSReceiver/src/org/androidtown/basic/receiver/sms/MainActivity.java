package org.androidtown.basic.receiver.sms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * SMS�� �����ϸ� ȭ�鿡 �� ������ �����ִ� ����� �� �� �ֽ��ϴ�.
 * ��ε�ĳ��Ʈ �����ڸ� �̿��ϸ鼭 ���� SMS�� ������ ȭ�鿡 �����ִ� ����� �� �� �ֽ��ϴ�.
 * 
 * �� ���� ��Ƽ��Ƽ�� ȭ�鿡 �����ֱ� ���� ���� �ƴմϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
