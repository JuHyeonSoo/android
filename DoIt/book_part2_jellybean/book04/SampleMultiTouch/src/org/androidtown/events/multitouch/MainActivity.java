package org.androidtown.events.multitouch;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

/**
 * �� �հ����� �̿��� ��Ƽ��ġ�� �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� ����� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	/**
	 * �̹����� ������ �並 ������� ���̾ƿ� ��ü
	 */
	LinearLayout viewerContainer;
	
	/**
	 * �̹����� ������ ��
	 */
	ImageDisplayView displayView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * �ʱ�ȭ
     */
    private void init() {
    	viewerContainer = (LinearLayout) findViewById(R.id.viewerContainer);
       	Bitmap sourceBitmap = loadImage();
		if (sourceBitmap != null) {
	        displayView = new ImageDisplayView(this);

	        displayView.setImageData(sourceBitmap);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        			LinearLayout.LayoutParams.MATCH_PARENT,
        			LinearLayout.LayoutParams.MATCH_PARENT);

	        viewerContainer.addView(displayView, params);
		}
    }

    /**
     * ���ҽ��� �̹����� ��Ʈ�� ��ü�� �ε�
     * 
     * @return
     */
	private Bitmap loadImage() {
		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.beach);

		return bitmap;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
