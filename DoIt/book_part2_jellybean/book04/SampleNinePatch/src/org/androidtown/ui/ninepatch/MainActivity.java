package org.androidtown.ui.ninepatch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * �Ϲ� �̹����� ũ�Ⱑ �ڵ� ����Ǿ��� ���� ������ġ �̹����� ũ�Ⱑ �ڵ� ����Ǿ��� ���� ���غ� �� �ֽ��ϴ�.
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
