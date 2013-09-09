package org.androidtown.tutorial.ui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �α��ΰ� �޴� ����� ������ �ִ� ȭ�� 
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	/**
	 * �ٸ� ��Ƽ��Ƽ�� ���� ���� ��û�ڵ�
	 */
	public static final int REQUEST_CODE_ANOTHER = 1001;
	
	/**
	 * About ��Ƽ��Ƽ�� ���� ���� ��û�ڵ�
	 */
	public static final int REQUEST_CODE_ABOUT = 1002;
	
	/**
	 * ���� ��Ƽ��Ƽ�� ���� ���� ��û�ڵ�
	 */
	public static final int REQUEST_CODE_SETTINGS = 1003;
	
	/**
	 * Ÿ��Ʋ
	 */
	TextView titleLabel;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �� ��ü ����
        final EditText usernameEntry = (EditText) findViewById(R.id.usernameEntry);
        final EditText passwordEntry = (EditText) findViewById(R.id.passwordEntry);
        titleLabel = (TextView) findViewById(R.id.titleLabel);
        
        // �α��� ��ư �̺�Ʈ ó��
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		String username = usernameEntry.getText().toString();
        		String password = passwordEntry.getText().toString();
        		
        		// �α��� �޼ҵ� ȣ��
        		boolean isLogged = checkLogin(username, password);
        		if (isLogged) {
        			// �α��� �����̸� �ٸ� ��Ƽ��Ƽ ����
	    			Intent intent = new Intent(getBaseContext(), AnotherActivity.class);
	   				startActivityForResult(intent, REQUEST_CODE_ANOTHER);
        		}
        		
        	}
        });
        
    }


    /**
     * ������ �α��� ó���� �ϴ� �޼ҵ�
     * 
     * @param username
     * @param password
     */
    private boolean checkLogin(String username, String password) {
    	// do something for login
    	Toast toast = Toast.makeText(getBaseContext(), "checkLogin() �޼ҵ� ȣ���. \nusername : " + username + ", password : " + password, Toast.LENGTH_LONG);
		toast.show();
    	
    	return true;
    }
    
    
    /**
     * �ٸ� ��Ƽ��Ƽ���� ���ƿ� ��
     */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == REQUEST_CODE_ANOTHER) {
			Toast toast = Toast.makeText(getBaseContext(), "onActivityResult() ȣ���. ���� �ڵ� : " + resultCode, Toast.LENGTH_LONG);
			toast.show();
			
			if (resultCode == Activity.RESULT_OK) {
				int color = data.getExtras().getInt("color");
				toast = Toast.makeText(getBaseContext(), "result color : " + color, Toast.LENGTH_LONG);
				toast.show();
				
				// �ؽ�Ʈ���� ���ڻ� �ٲٱ�
				titleLabel.setTextColor(color);
			}
			
		}
		
	}
	    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// �ڵ忡�� ���� �ɼ� �޴� �߰��ϱ�
    	super.onCreateOptionsMenu(menu);
    	addOptionMenuItems(menu);
    	
        return true;
    }
    

	/**
	 * �ɼ� �޴��� �����۵� �߰�
	 * 
	 * @param menu
	 */
    private void addOptionMenuItems(Menu menu) {
    	int base = Menu.FIRST;

    	MenuItem item01 = menu.add(base, base, Menu.NONE,"Settings");
    	MenuItem item02 = menu.add(base, base+1, Menu.NONE,"About");
    	
    	item01.setIcon(R.drawable.settings_icon);
    	item02.setIcon(R.drawable.about_icon);
    }
    	
    /**
     * �ɼ� �޴��� ���õǾ��� �� ȣ���
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId() == 1) {
    		// ���� ȭ�� ����ֱ�
			Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
			startActivityForResult(intent, REQUEST_CODE_SETTINGS);
    	} else if (item.getItemId() == 2) {
    		// About ȭ�� ����ֱ�
			Intent intent = new Intent(getBaseContext(), AboutDialog.class);
			startActivityForResult(intent, REQUEST_CODE_ABOUT);
    	}
    	
    	return true;
    }	
	
    
}
