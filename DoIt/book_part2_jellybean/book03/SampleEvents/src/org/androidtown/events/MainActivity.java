package org.androidtown.events;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * ��ġ �̺�Ʈ�� ����ó�� �ν��Ͽ� ó���ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	TextView TextView01;
	private GestureDetector mGestures = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		TextView01 = (TextView) findViewById(R.id.TextView01);
		
		// �ؽ�Ʈ�並 ������ �ִ� ���̾ƿ�
		View rootLayout = findViewById(R.id.rootLayout);
		
		// ��ġ ��尡 �ٲ�� ��� �� �� �ֵ��� ǥ���մϴ�.
		ViewTreeObserver observer = rootLayout.getViewTreeObserver();
		observer.addOnTouchModeChangeListener(new ViewTreeObserver.OnTouchModeChangeListener() {
			public void onTouchModeChanged(boolean isTouchMode) {
				TextView01.append("\n��ġ ��尡 �ٲ�����ϴ�. : " + isTouchMode);
			}
		});

		// ��� ������ �� �� �� �ֵ��� �մϴ�.
		TextView01.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				TextView01.append("\nonLongClick: " + v.toString());
				return true;
			}
		});

		// ����ó�� �ν��ϸ� ������ �̺�Ʈ ó���� �� �� �����ϰ� �� �� �ֽ��ϴ�.
		mGestures = new GestureDetector(this,
			new GestureDetector.SimpleOnGestureListener() {
				// fling �̺�Ʈ�� �߻��� �� ó���մϴ�.
				public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
					TextView01.append("\nonFling \n\tvelocityX = " + velocityX + "\n\tvelocityY=" + velocityY);

					return super.onFling(e1, e2, velocityX, velocityY);
				}

				// scroll �̺�Ʈ�� �߻��� �� ó���մϴ�.
				public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
					TextView01.append("\nonScroll \n\tdistanceX = " + distanceX + "\n\tdistanceY = " + distanceY);

					return super.onScroll(e1, e2, distanceX, distanceY);
				}
			});

		// ��Ŀ���� �ٲ���� �� �� �� �ֵ��� �����ʸ� �����մϴ�.
		TextView01.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			// focus�� �ٲ���� �� ȣ��˴ϴ�.
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                	TextView01.append("\nonFocusChange, hasFocus : " + hasFocus);
                } else {
                	TextView01.append("\nonFocusChange, hasFocus : " + hasFocus);
                }
            }

        });

    }

    /**
     * ��ġ �̺�Ʈ�� ����ó�� �ν��� �� �ֵ��� �մϴ�.
     */
	public boolean onTouchEvent(MotionEvent event) {
		if (mGestures != null) {
			return mGestures.onTouchEvent(event);
		} else {
			return super.onTouchEvent(event);
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
