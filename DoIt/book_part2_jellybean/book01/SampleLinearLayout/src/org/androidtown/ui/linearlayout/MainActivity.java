package org.androidtown.ui.linearlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * ���Ͼ� ���̾ƿ� ����ϱ�
 * 
 * - ���� ������ �� ���ϴ� ��ư�� �ϳ� �����ϸ� ���Ͼ� ���̾ƿ��� �Ӽ��� �̿��� ���� ȭ���� ���Դϴ�.
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

        // ù��° ��ư�� ������ �� normal.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.normal);
        	}
        });

        // �ι�° ��ư�� ������ �� padding.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button02 = (Button) findViewById(R.id.button02);
        button02.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.padding);
        	}
        });

        // ����° ��ư�� ������ �� gravity.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button03 = (Button) findViewById(R.id.button03);
        button03.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.gravity);
        	}
        });

        // �׹�° ��ư�� ������ �� weight.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button04 = (Button) findViewById(R.id.button04);
        button04.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.weight);
        	}
        });

        // �ټ���° ��ư�� ������ �� baseline.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button05 = (Button) findViewById(R.id.button05);
        button05.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.baseline);
        	}
        });

        // ������° ��ư�� ������ �� gravitytext01.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button06 = (Button) findViewById(R.id.button06);
        button06.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.gravitytext01);
        	}
        });

        // �ϰ���° ��ư�� ������ �� gravitytext02.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button07 = (Button) findViewById(R.id.button07);
        button07.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.gravitytext02);
        	}
        });

        // ������° ��ư�� ������ �� gravitytext03.xml �� ���ǵ� ȭ�� ���̾ƿ��� �����ݴϴ�.
        Button button08 = (Button) findViewById(R.id.button08);
        button08.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		setContentView(R.layout.gravitytext03);
        	}
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
