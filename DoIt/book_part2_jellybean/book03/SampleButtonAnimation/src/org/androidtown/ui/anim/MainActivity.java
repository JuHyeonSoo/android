package org.androidtown.ui.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �信 �ִϸ��̼��� �����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	Animation flowAnim;
	TextView text01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // anim ���� �ȿ� ���ǵǾ� �ִ� �ִϸ��̼� �׼� ������ �ε��մϴ�.
        flowAnim = AnimationUtils.loadAnimation(this, R.anim.flow);

    	text01 = (TextView) findViewById(R.id.text01);

    	// ��ư�� ������ �� �ִϸ��̼��� �����մϴ�.
        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		// �ִϸ��̼� ������ ���
        		flowAnim.setAnimationListener(new FlowAnimationListener());
        		// �ִϸ��̼� ����
        		text01.startAnimation(flowAnim);
        	}
        });

    }

    /**
     * �ִϸ��̼��� ������ ������ �˱� ���� �ִϸ��̼� �����ʸ� �����մϴ�.
     */
    private final class FlowAnimationListener implements Animation.AnimationListener {

    	/**
    	 * �ִϸ��̼��� ���� �� �ڵ� ȣ���
    	 */
		public void onAnimationEnd(Animation animation) {
			Toast.makeText(getApplicationContext(), "�ִϸ��̼� �����.", 1000).show();
		}

		/**
    	 * �ִϸ��̼��� �ݺ��� �� �ڵ� ȣ���
    	 */
		public void onAnimationRepeat(Animation animation) {
		}

		/**
    	 * �ִϸ��̼��� ������ �� �ڵ� ȣ���
    	 */
		public void onAnimationStart(Animation animation) {
		}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
