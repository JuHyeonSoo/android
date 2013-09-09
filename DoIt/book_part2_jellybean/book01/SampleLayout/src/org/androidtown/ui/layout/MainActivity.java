package org.androidtown.ui.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ���̾ƿ� �����
 * 
 * - ���� ������ �� ���ϴ� ��ư�� �ϳ� �����ϸ� �� ���̾ƿ��� ����� ȭ���� ���Դϴ�.
 * - ȭ���� ��� ������������� res/layout ���� ���� �ش� XML ���̾ƿ� ������ ���ø� �˴ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ù��° ��ư�� ������ �� textview.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.linear_layout);
        	}
        });

        // �ι�° ��ư�� ������ �� button.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.table_layout);
        	}
        });

        // ����° ��ư�� ������ �� edittext.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button03 = (Button) findViewById(R.id.button03);
        button03.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.table_layout2);
        	}
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}