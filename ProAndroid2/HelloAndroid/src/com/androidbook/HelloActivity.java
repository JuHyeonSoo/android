package com.androidbook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelloActivity extends Activity {
	/** ��Ƽ��Ƽ ���� ȣ�� �ÿ� ȣ��� */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** TextView�� �����ϰ� Hello World!�� ��� */
		TextView tv = new TextView(this);
		tv.setText("Hello World!");
		/** ����Ʈ �並 TextView�� ���� */
		setContentView(tv);
	}
}