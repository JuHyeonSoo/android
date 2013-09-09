package org.androidtown.tutorial.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * �̹����� ���̴� ��Ʈ�� ��ư ����
 * 
 * @author Mike
 */
public class BitmapButton extends Button {

	int normalBitmapId;
	int clickedBitmapId;
	
	public BitmapButton(Context context) {
		super(context);
	}

	public BitmapButton(Context context, AttributeSet atts) {
		super(context, atts);

	}

	public void setBitmapId(int normalId, int clickedId) {
		normalBitmapId = normalId;
		clickedBitmapId = clickedId;
		
		setBackgroundResource(normalBitmapId);
	}
	
	
	/**
	 * ��ġ �̺�Ʈ ó��
	 */
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		
		int action = event.getAction();

		switch (action) {
			case MotionEvent.ACTION_UP:
				setBackgroundResource(normalBitmapId);
				
				break;
	
			case MotionEvent.ACTION_DOWN:
				setBackgroundResource(clickedBitmapId);
				
				break;

		}

		// �ٽ� �׸���
		invalidate();

		return true;
	}	
	
}
