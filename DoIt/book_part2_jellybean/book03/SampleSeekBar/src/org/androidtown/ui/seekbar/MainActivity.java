package org.androidtown.ui.seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * ��ũ�ٸ� �̿��� ���� �����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * ��ũ�ٸ� ��� �ִ� ���̾ƿ� ��ü
	 */
    private View panel;
    
    /**
     * ��ũ�� ��ü
     */
    private SeekBar seekbar;
    
    /**
     * �ؽ�Ʈ��
     */
    private TextView text01;
    
    /**
     * ȭ���� ��
     */
    private int brightness = 50;
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ���̾ƿ��� ���ǵ� ��ü ����
        panel = findViewById(R.id.panel01);
        text01 = (TextView) findViewById(R.id.text01);
        seekbar = (SeekBar) findViewById(R.id.seekbar01);

        // ��ư �̺�Ʈ ó��
        Button showBtn = (Button) findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showPanel();
            }
        });

        // ��ũ�ٿ� ������ ����
        seekbar.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());
        
    }

    
    /**
     * ��ũ�ٸ� ��� �ִ� ���̾ƿ��� �����ִ� �޼ҵ�
     */
    private void showPanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        seekbar.setProgress(this.brightness);
        panel.setVisibility(View.VISIBLE);
        panel.startAnimation(animation);
    }

    /**
     * ȭ�� ��� ����
     */
    private void setBrightness(int value) {
        if (value < 10) {
            value = 10;
        } else if (value > 100) {
            value = 100;
        }
        
        brightness = value;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float) value / 100;        
        getWindow().setAttributes(params);
    }

    /**
     * ��ũ�ٸ� ��� �ִ� ���̾ƿ��� ������ �ʵ��� ��
     */
    private void hidePanel() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        panel.startAnimation(animation);
        panel.setVisibility(View.GONE);

    }
    
    /**
     * ��ũ�ٿ� ������ ������
     */
    class MyOnSeekBarChangeListener implements OnSeekBarChangeListener {
    	/**
    	 * ��ũ���� ���� �ٲ� �� �ڵ� ȣ���
    	 * 
    	 * @param seekBar
    	 * @param progress
    	 * @param fromUser
    	 */
    	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setBrightness(progress);
            text01.setText("��� ���� : " + progress);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            hidePanel();
        }
    }
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
