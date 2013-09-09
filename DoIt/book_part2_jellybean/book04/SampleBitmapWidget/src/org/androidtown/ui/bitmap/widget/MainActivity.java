package org.androidtown.ui.bitmap.widget;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

/**
 * ��Ʈ���� �ö� ������ ���� �����ϴ� ����� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Ÿ��Ʋ�� �Ⱥ��̵��� ��
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        // ���� ȭ��ǥ�� �����ִ� ���� ��ü ����
        TitleBitmapButton arrowLeftBtn = (TitleBitmapButton)findViewById(R.id.arrowLeftBtn);

        // ���ҽ��� �̹����� ���� �����ͼ� �����ϴ� ���
		Resources res = getResources();
		BitmapDrawable curDrawable = (BitmapDrawable) res.getDrawable(R.drawable.arrow_left);
		Bitmap iconBitmap = curDrawable.getBitmap();
		BitmapDrawable curClickedDrawable = (BitmapDrawable) res.getDrawable(R.drawable.arrow_left_clicked);
		Bitmap iconClickedBitmap = curClickedDrawable.getBitmap();
		arrowLeftBtn.setIconBitmap(iconBitmap, iconClickedBitmap);

		// �̺�Ʈ ó��
		arrowLeftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ȭ���� ����
				finish();
			}
		});

		String titleText = "��Ʈ�� Title";

		// Ÿ��Ʋ�� ���� ��ư�� �ؽ�Ʈ ����
        TitleButton titleBtn = (TitleButton)findViewById(R.id.titleBtn);
        titleBtn.setTitleText(titleText);
        titleBtn.setDefaultSize(32F);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
