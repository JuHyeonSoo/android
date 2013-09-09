package org.androidtown.graphics.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * �並 ����Ͽ� ���� ���� ��
 * 
 * @author Mike
 *
 */
public class CustomView extends View {
	
	/**
	 * �׸����� �� ����ϴ� �Ӽ��� ��� �ִ� ����Ʈ ��ü
	 */
	private Paint paint;

	/**
	 * ������
	 * 
	 * @param context
	 */
	public CustomView(Context context) {
		super(context);

		paint = new Paint();
		paint.setColor(Color.RED);
	}

	/**
	 * ȭ�鿡 �׸���
	 */
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawRect(100, 100, 200, 200, paint);
	}
	
	/**
	 * ��ġ �̺�Ʈ ó��
	 * ��ġ�� ������ ��ġ�� ��ġ�� �佺Ʈ �޽����� ǥ��
	 */
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Toast.makeText(super.getContext(), "MotionEvent.ACTION_DOWN : " + event.getX() + ", " + event.getY(), 1000).show();
		}
		
		return super.onTouchEvent(event);
	}

}
