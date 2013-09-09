package org.androidtown.actionbar02;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �׼ǹ��� �޴��� �並 �����ϴ� ����� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	TextView text01;
	EditText edit01;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        // ���õ� �޴��� ǥ���� �ؽ�Ʈ��
        text01 = (TextView) findViewById(R.id.text01);
        
    }

    /**
     * �޴��� ������� �� �ֵ��� �ڵ����� ȣ��˴ϴ�.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// XML�� ������ �޴����� ���÷��̼��Ͽ� �ε��մϴ�.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        View v = menu.findItem(R.id.menu_search).getActionView();
        edit01 = (EditText) v.findViewById(R.id.edit01);
 
        if (edit01 != null) {
        	edit01.setOnEditorActionListener(onSearchListener);
        }
        
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
    
    /**
     * Ű �Է��� ������ �� �˻��մϴ�.
     */
    private TextView.OnEditorActionListener onSearchListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (event == null || event.getAction() == KeyEvent.ACTION_UP) {
                // �˻� �޼ҵ� ȣ��
            	search();
 
            	// Ű�е� �ݱ�
                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
 
            return (true);
        }
    };
    
    /**
     * �˻� �޼ҵ� : ���⿡���� �ܼ��� �޽����� �˻�� �����ݴϴ�.
     */
    private void search() {
    	String searchString = edit01.getEditableText().toString();
    	Toast.makeText(this, "�˻��� : " + searchString, Toast.LENGTH_SHORT).show();
    }
    
}
