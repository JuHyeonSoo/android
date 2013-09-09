package org.androidtown.ui.scrollview;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * ��ũ�Ѻ� ����ϱ�
 * 
 * - ���� �����ϸ� ȭ�麸�� ū �̹����� �հ��� ��ġ�� ���� �¿�/���� ��ũ�ѵ˴ϴ�.
 * - ���ι���� ���ι��� ��ũ���� ���鶧 ��ũ�Ѻ並 ��� ����ϴ��� XML ���̾ƿ��� ���캾�ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	ScrollView scrollView01;
	ImageView imageView01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView01 = (ScrollView) findViewById(R.id.scrollView01);
        imageView01 = (ImageView) findViewById(R.id.imageView01);
        Button button01 = (Button) findViewById(R.id.button01);

        // ���� ��ũ�Ѻ��� �޼ҵ� ȣ��
        scrollView01.setHorizontalScrollBarEnabled(true);

        // drawable ���ҽ��� �ִ� �̹����� �����ͼ� �̹����信 �����ϱ�
        Resources res = getResources();
        BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.system_architecture);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView01.setImageDrawable(bitmap);
        imageView01.getLayoutParams().width = bitmapWidth;
        imageView01.getLayoutParams().height = bitmapHeight;

        // ��ư�� ������ �� �̹��� �ٲپ� �����ֱ�
        button01.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		changeImage();
        	}
        });

    }

    /**
     * �ٸ� �̹����� �ٲپ� �����ֱ�
     */
    private void changeImage() {
    	Resources res = getResources();
        BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.activity_lifecycle);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView01.setImageDrawable(bitmap);
        imageView01.getLayoutParams().width = bitmapWidth;
        imageView01.getLayoutParams().height = bitmapHeight;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
