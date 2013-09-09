package org.androidtown.tutorial.ui;

import android.R.style;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

/**
 * About ȭ��
 * 
 * @author Mike
 */
public class AboutDialog extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Ÿ��Ʋ �κ��� �Ⱥ��̵��� ��
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // XML ���̾ƿ� ����
        setContentView(R.layout.about);
       
        // �̹����� ���̴� ��Ʈ�� ��ư�� Ŭ�� �̺�Ʈ ó��
        BitmapButton btn = (BitmapButton) findViewById(R.id.confirmBtn);
        btn.setBitmapId(R.drawable.confirm_btn_normal, R.drawable.confirm_btn_clicked);
        btn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        	}
        });
        
    }
    	
    /**
     * Theme Style ����
     */
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);

        theme.applyStyle(style.Theme_Panel, true);
    }
	
}
