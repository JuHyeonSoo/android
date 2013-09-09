package org.androidtown.animation.graph;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ����׷��� ���ó�� ����� �ִϸ��̼��� �����ϴ� ���� ������ ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	LinearLayout mainLayout;
    Resources res;
    Animation growAnim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();
        growAnim = AnimationUtils.loadAnimation(this, R.anim.grow);
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);

        // ������ �߰�
        addItem("Apple", 80);
        addItem("Orange", 100);
        addItem("Kiwi", 40);

    }

    /**
     * ������ �߰� �޼ҵ�
     * 
     * @param name
     * @param value
     */
    private void addItem(String name, int value) {

        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);

        // �ؽ�Ʈ�� �߰�
        TextView textView = new TextView(this);
        textView.setText(name);
        params.width = 180;
        params.setMargins(0, 4, 0, 4);
        itemLayout.addView(textView, params);

        // ���α׷����� �߰�
        ProgressBar proBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        proBar.setIndeterminate(false);
        proBar.setMax(100);
        proBar.setProgress(100);
        proBar.setAnimation(growAnim);
        params2.height = 80;
        params2.width = value * 3;
        params2.gravity = Gravity.LEFT;
        itemLayout.addView(proBar, params2);

        mainLayout.addView(itemLayout, params3);

    }

    /**
     * ȭ�鿡 �������� ���� ȣ��Ǵ� �޼ҵ� 
     */
    public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		Toast.makeText(this, "onWindowFocusChanged : " + hasFocus, Toast.LENGTH_SHORT).show();

		if (hasFocus) {
	    	growAnim.start();
		} else {
			growAnim.reset();
		}
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
