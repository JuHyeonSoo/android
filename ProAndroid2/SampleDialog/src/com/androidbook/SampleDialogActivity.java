package com.androidbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SampleDialogActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    Menu myMenu = null;

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		  MenuInflater inflater = getMenuInflater(); // ��Ƽ��Ƽ�κ���
		  inflater.inflate(R.menu.my_menu, menu); 

		  // �ݵ�� true�� ��ȯ�ؾ߸� �޴��� ���δ�.
		  return true;
	}

	@Override 
	public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_simple_alert) 
        {
        	//String reply = 
        	Alerts.showAlert("������ ���� ���â", this);
        }
        // �޴� �׸� ó���� �Ϸ�Ǹ� �ݵ�� true�� ��ȯ�ؾ� �Ѵ�.
		return true;
	}

}
