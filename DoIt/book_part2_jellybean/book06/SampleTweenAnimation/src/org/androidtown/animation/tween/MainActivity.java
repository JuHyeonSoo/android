package org.androidtown.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Ʈ�� �ִϸ��̼��� �����ϴ� ������ ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ù��° ��ư �̺�Ʈ ó��
        Button scaleBtn = (Button) findViewById(R.id.scaleBtn);
        scaleBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        		v.startAnimation(anim);
        	}
        });

        // �ι�° ��ư �̺�Ʈ ó��
        Button scale2Btn = (Button) findViewById(R.id.scale2Btn);
        scale2Btn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale2);
        		v.startAnimation(anim);
        	}
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
