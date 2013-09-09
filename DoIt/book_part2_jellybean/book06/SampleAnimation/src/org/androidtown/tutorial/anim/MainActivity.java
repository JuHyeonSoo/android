package org.androidtown.tutorial.anim;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * �信 �ִϸ��̼��� �����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	View rootView;
	ImageView swingImage;
	ImageView waterImage;
	ImageView skyImage;

	Animation shakeAnimation;
	Animation dropAnimation;
	Animation flowAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // swing �̹����� �ִϸ��̼� ��ü ����
        swingImage = (ImageView) findViewById(R.id.swingImage);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        swingImage.setAnimation(shakeAnimation);

        // water �̹����� �ִϸ��̼� ��ü ����
        waterImage = (ImageView) findViewById(R.id.waterImage);
        dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
        waterImage.setAnimation(dropAnimation);

        // sky �̹����� �ִϸ��̼� ��ü ����
        skyImage = (ImageView) findViewById(R.id.skyImage);
        flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
        skyImage.setAnimation(flowAnimation);

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.sky_background);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        ViewGroup.LayoutParams params = skyImage.getLayoutParams();
        params.width = bitmapWidth;
        params.height = bitmapHeight;

        skyImage.setImageBitmap(bitmap);

        flowAnimation.setAnimationListener(new AnimationAdapter());

    }

    /**
     * ȭ�鿡 �������� ���� ȣ��Ǵ� �޼ҵ�
     */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		Toast.makeText(getApplicationContext(), "onWindowFocusChanged : " + hasFocus, 2000).show();

		if (hasFocus) {
			shakeAnimation.start();
			dropAnimation.start();
			flowAnimation.start();
		} else {
			shakeAnimation.reset();
			dropAnimation.reset();
			flowAnimation.reset();
		}

	}



    @Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();

		Toast.makeText(getApplicationContext(), "attached.", 2000).show();
	}



	@Override
	public void onDetachedFromWindow() {
		super.onDetachedFromWindow();

		Toast.makeText(getApplicationContext(), "detached.", 2000).show();
	}


	/**
	 * �ִϸ��̼��� ���۰� ���� ������ �˱� ���� ������
	 */
	private final class AnimationAdapter implements Animation.AnimationListener {

		public void onAnimationStart(Animation animation) {
			Toast.makeText(getApplicationContext(), "Animation started.", 2000).show();
		}

		public void onAnimationEnd(Animation animation) {
			Toast.makeText(getApplicationContext(), "Animation ended.", 2000).show();
		}

		public void onAnimationRepeat(Animation animation) {

		}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
