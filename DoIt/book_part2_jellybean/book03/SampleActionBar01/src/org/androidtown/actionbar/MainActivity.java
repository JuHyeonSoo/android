package org.androidtown.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * �׼ǹٿ� �޴� ��ư���� �����ִ� ���� �⺻���� ����� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	TextView text01;
	ActionBar abar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �׼ǹ� ��ü�� ������ ���� getActionBar() �޼ҵ带 ����մϴ�.
        abar = getActionBar();
        
        // �����ְ� �ʹٸ� show() �޼ҵ带 ȣ���մϴ�.
        //abar.show();
        // ���߰� �ʹٸ� hide() �޼ҵ带 ȣ���մϴ�.
        //abar.hide();
        
        // Ÿ��Ʋ�� �������� �����մϴ�.
        abar.setSubtitle("�ɼǹ� ���캸��");
        
        
        // ���õ� �޴��� ǥ���� �ؽ�Ʈ��
        text01 = (TextView) findViewById(R.id.text01);
     
        // �׼ǹ��� �������� �ٲ� ��ư
        Button button01 = (Button) findViewById(R.id.button01);
        button01.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_HOME_AS_UP);
        	}
        });

        
    }

    /**
     * �޴��� ������� �� �ֵ��� �ڵ����� ȣ��˴ϴ�.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// XML�� ������ �޴����� ���÷��̼��Ͽ� �ε��մϴ�.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        return true;
    }
    
    /**
     * �޴��� ���õǾ��� �� �ڵ����� ȣ��˴ϴ�.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
	        case R.id.menu_refresh:  // ���ΰ�ħ �޴� ����
	            text01.setText("���ΰ�ħ �޴��� �����߽��ϴ�.");
	            return true;
	 
	        case R.id.menu_search:  // �˻� �޴� ����
	        	text01.setText("�˻� �޴��� �����߽��ϴ�.");
	            return true;
	 
	        case R.id.menu_settings:  // ���� �޴� ����
	        	text01.setText("���� �޴��� �����߽��ϴ�.");
	            return true;
        }
 
        return super.onOptionsItemSelected(item);
    }
    
}
