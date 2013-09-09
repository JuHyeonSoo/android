package org.androidtown.tutorial.graphic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * �ձ۾��� ���ų� ��ġ�� �׸��� �׸� �� �ִ� ����Ʈ���带 ����� ����� ���� �� �� �ֽ��ϴ�.
 * �ڵ�� �ܰ躰�� �����˴ϴ�. ���� ���� ��Ƽ��Ƽ�� �ܰ躰�� �����غ� �� �ִ� ��ư�� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_main);
        
        // 1�ܰ� ��ư ������ �� PaintBoard �����ֱ�
        Button step01Button = (Button) findViewById(R.id.step01Button);
        step01Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), PaintBoardActivity.class);
				startActivity(intent);
			}
        });
        
        // 2�ܰ� ��ư ������ �� GoodPaintBoard �����ֱ�
        Button step02Button = (Button) findViewById(R.id.step02Button);
        step02Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), GoodPaintBoardActivity.class);
				startActivity(intent);
			}
        });
        
        // 3�ܰ� ��ư ������ �� BestPaintBoard �����ֱ�
        Button step03Button = (Button) findViewById(R.id.step03Button);
        step03Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), BestPaintBoardActivity.class);
				startActivity(intent);
			}
        });
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
