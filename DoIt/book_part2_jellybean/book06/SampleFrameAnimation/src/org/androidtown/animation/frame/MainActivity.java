package org.androidtown.animation.frame;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * ������ ���� �ִϸ��̼� ���� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	AnimationDrawable animDrawable = null;
	ImageView imageView01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		imageView01 = (ImageView)findViewById(R.id.imageView01);

		// ���� ��ư �̺�Ʈ ó��
		Button startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startAnimation();
			}
		});

		// ���� ��ư �̺�Ʈ ó��
		Button stopBtn = (Button) findViewById(R.id.stopBtn);
		stopBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				stopAnimation();
			}
		});


		// �̹��� ��������
		Resources res = getResources();
	 	BitmapDrawable frame01 = (BitmapDrawable)res.getDrawable(R.drawable.emo_im_crying);
	 	BitmapDrawable frame02 = (BitmapDrawable)res.getDrawable(R.drawable.emo_im_happy);
	 	BitmapDrawable frame03 = (BitmapDrawable)res.getDrawable(R.drawable.emo_im_laughing);
	 	BitmapDrawable frame04 = (BitmapDrawable)res.getDrawable(R.drawable.emo_im_surprised);

	 	// ���������� �߰��ϱ�
	 	int duration = 250;
	 	animDrawable = new AnimationDrawable();
	 	animDrawable.setOneShot(false);
	 	animDrawable.addFrame(frame01, duration);
	 	animDrawable.addFrame(frame02, duration);
	 	animDrawable.addFrame(frame03, duration);
	 	animDrawable.addFrame(frame04, duration);

    }

    /**
     * �ִϸ��̼� ����
     */
	private void startAnimation() {
     	imageView01.setBackgroundDrawable(animDrawable);

     	animDrawable.setVisible(true, true);
     	animDrawable.start();
	}

	/**
	 * �ִϸ��̼� ����
	 */
	private void stopAnimation() {
		animDrawable.stop();
		animDrawable.setVisible(false, false);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
