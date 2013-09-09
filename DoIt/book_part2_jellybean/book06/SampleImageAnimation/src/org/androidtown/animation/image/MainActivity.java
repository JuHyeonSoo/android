package org.androidtown.animation.image;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

/**
 * ImageSwitcher �� �̿��� �̹��� �ִϸ��̼� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	ImageSwitcher switcher;
	Handler mHandler = new Handler();
	ImageThread thread;
	boolean running;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


		switcher = (ImageSwitcher) findViewById(R.id.switcher);
		switcher.setVisibility(View.INVISIBLE);

		switcher.setFactory(new ViewSwitcher.ViewFactory() {
			public View makeView() {
				ImageView imageView = new ImageView(getApplicationContext());
				imageView.setBackgroundColor(0xFF000000);
				imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				
				return imageView;
			}
		});

    }

    /**
     * �ִϸ��̼� ����
     */
	private void startAnimation() {
		switcher.setVisibility(View.VISIBLE);

		thread = new ImageThread();
		thread.start();
	}

	/**
	 * �ִϸ��̼� ����
	 */
	private void stopAnimation() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException ex) { }

		switcher.setVisibility(View.INVISIBLE);
	}

	/**
	 * �̹��� ó�� ������
	 * @author michael
	 *
	 */
	class ImageThread extends Thread {
		int duration = 250;
		final int imageId[] = { R.drawable.emo_im_crying,
								R.drawable.emo_im_happy,
								R.drawable.emo_im_laughing,
								R.drawable.emo_im_surprised };
		int currentIndex = 0;

		public void run() {
			running = true;
			while (running) {
				synchronized (this) {
					mHandler.post(new Runnable() {
						public void run() {
							switcher.setImageResource(imageId[currentIndex]);
						}
					});

					currentIndex++;
					if (currentIndex == imageId.length) {
						currentIndex = 0;
					}

					try {
						Thread.sleep(duration);
					} catch (InterruptedException ex) { }
				}
			}
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
