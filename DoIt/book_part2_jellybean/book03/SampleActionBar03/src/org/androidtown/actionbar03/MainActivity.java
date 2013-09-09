package org.androidtown.actionbar03;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
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
 * �׼ǹٿ� ���� �����ϴ� ����� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	EditText edit01;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ���� ���̾ƿ��� �����ϴ� setContentView() �޼ҵ带 ������� �ʽ��ϴ�.
        

        // �׼ǹٸ� ���� �����Ͽ� ��带 �����մϴ�.
        ActionBar abar = getActionBar();
        abar.setNavigationMode( ActionBar.NAVIGATION_MODE_TABS );

		Tab tab01 = abar.newTab();
		tab01.setText("��ǰ #1");
		tab01.setTabListener(new ProductTabListener(this, Fragment01.class.getName()));
		abar.addTab(tab01);

		Tab tab02 = abar.newTab();
		tab02.setText("��ǰ #2");
		tab02.setTabListener(new ProductTabListener(this, Fragment02.class.getName()));
		abar.addTab(tab02);

		Tab tab03 = abar.newTab();
		tab03.setText("��ǰ #3");
		tab03.setTabListener(new ProductTabListener(this, Fragment03.class.getName()));
		abar.addTab(tab03);
		
    }

    /**
     * ���� �������� �� ó���� ������ ����
     */
	private class ProductTabListener implements ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mFragName;

		public ProductTabListener(Activity activity, String fragName) {
			mActivity = activity;
			mFragName = fragName;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction arg1) {
			
		}

		/**
		 * ���� ���õǾ��� ��
		 */
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			mFragment = Fragment.instantiate(mActivity, mFragName);
			ft.add(android.R.id.content, mFragment);
		}

		/**
		 * �� ������ �����Ǿ��� ��
		 */
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(mFragment);
			mFragment = null;
		}

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
	        	Toast.makeText(this, "���ΰ�ħ �޴��� �����߽��ϴ�.", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        case R.id.menu_search:  // �˻� �޴� ����
	        	Toast.makeText(this, "�˻� �޴��� �����߽��ϴ�.", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        case R.id.menu_settings:  // ���� �޴� ����
	        	Toast.makeText(this, "���� �޴��� �����߽��ϴ�.", Toast.LENGTH_SHORT).show();
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
