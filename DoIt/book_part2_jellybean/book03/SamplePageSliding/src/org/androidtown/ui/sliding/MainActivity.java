package org.androidtown.ui.sliding;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * �� ���� ���̾ƿ��� ��ø���� �� �� ��ư�� ������ ������ �ʴ� ȭ���� �ִϸ��̼ǵǸ鼭 ���̴� ������ �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * �������� ���� �ִ��� �˱� ���� �÷���
	 */
	boolean isPageOpen = false;

	/**
	 * �ִϸ��̼� ��ü
	 */
	Animation translateLeftAnim;
	Animation translateRightAnim;

	/**
	 * �����̵����� �������� ������ ���̾ƿ�
	 */
	LinearLayout slidingPage01;
	
	/**
	 * ��ư
	 */
	Button openBtn01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �����̵����� ������ ���̾ƿ� ��ü ����
        slidingPage01 = (LinearLayout) findViewById(R.id.slidingPage01);

        // �ִϸ��̼� ��ü �ε�
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        // �ִϸ��̼� ��ü�� ������ ����
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);


        // ��ư �̺�Ʈ ó��
        openBtn01 = (Button) findViewById(R.id.openBtn01);
        openBtn01.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		// �ִϸ��̼� ����
        		if (isPageOpen) {
        			slidingPage01.startAnimation(translateRightAnim);
        		} else {
        			slidingPage01.setVisibility(View.VISIBLE);
        			slidingPage01.startAnimation(translateLeftAnim);
        		}
        	}
        });

    }
    
    /**
     * �ִϸ��̼� ������ ����
     */
    private class SlidingPageAnimationListener implements AnimationListener {
    	/**
    	 * �ִϸ��̼��� ���� �� ȣ��Ǵ� �޼ҵ�
    	 */
		public void onAnimationEnd(Animation animation) {
			if (isPageOpen) {
				slidingPage01.setVisibility(View.INVISIBLE);

				openBtn01.setText("Open");
				isPageOpen = false;
			} else {
				openBtn01.setText("Close");
				isPageOpen = true;
			}
		}

		public void onAnimationRepeat(Animation animation) {

		}

		public void onAnimationStart(Animation animation) {

		}

    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
