package org.androidtown.basic.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ��Ƽ��Ƽ�� �����ֱ⿡ ���� �� �� �ֽ��ϴ�.
 *
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * ��û �ڵ� ����
	 */
	public static final int REQUEST_CODE_ANOTHER = 1001;
	
	public static final String PREF_ID = "Pref01";  
	public static final int actMode = Activity.MODE_PRIVATE;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showBtn = (Button) findViewById(R.id.showBtn);
		
		// ��ư�� ������ �� ���ο� ��Ƽ��Ƽ�� ����ݴϴ�.
		showBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {			
				// ����Ʈ ��ü�� ����ϴ�.
    			Intent intent = new Intent(getBaseContext(), AnotherActivity.class);
    			
    			// ��Ƽ��Ƽ�� ����ֵ��� startActivityForResult() �޼ҵ带 ȣ���մϴ�.
   				startActivityForResult(intent, REQUEST_CODE_ANOTHER);	
			}
		});

		Toast.makeText(getBaseContext(), "onCreate() ȣ���.", Toast.LENGTH_LONG).show();
    	
    }

    
    @Override
	protected void onDestroy() {
    	Toast.makeText(getBaseContext(), "onDestroy() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		Toast.makeText(getBaseContext(), "onPause() ȣ���.", Toast.LENGTH_LONG).show();
    	
		saveCurrentState();
		
		super.onPause();
	}


	@Override
	protected void onRestart() {
		Toast.makeText(getBaseContext(), "onRestart() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onRestart();
	}


	@Override
	protected void onResume() {
		Toast.makeText(getBaseContext(), "onResume() ȣ���.", Toast.LENGTH_LONG).show();
    	
		restoreFromSavedState();
		
		super.onResume();
	}


	@Override
	protected void onStart() {
		Toast.makeText(getBaseContext(), "onStart() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onStart();
	}


	@Override
	protected void onStop() {
		Toast.makeText(getBaseContext(), "onStop() ȣ���.", Toast.LENGTH_LONG).show();
    	
		super.onStop();
	}



	/**
     * ���ο� ��Ƽ��Ƽ���� ���ƿ� �� �ڵ� ȣ��Ǵ� �޼ҵ�
     */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_ANOTHER) {
			Toast toast = Toast.makeText(getBaseContext(), "onActivityResult() �޼ҵ尡 ȣ���. ��û�ڵ� : " + requestCode + ", ����ڵ� : " + resultCode, Toast.LENGTH_LONG);
			toast.show();
		}

	}

	protected void restoreFromSavedState() {
		SharedPreferences myPrefs = getSharedPreferences(PREF_ID, actMode);
		if ((myPrefs != null) && (myPrefs.contains("txtMsg")) ) {
			String myData = myPrefs.getString("txtMsg", "");
			Toast.makeText(this, "Restored : " + myData, Toast.LENGTH_SHORT).show();
		}
	}
 
	protected void saveCurrentState() {
		SharedPreferences myPrefs = getSharedPreferences(PREF_ID, actMode);
		SharedPreferences.Editor myEditor = myPrefs.edit();
		myEditor.putString( "txtMsg", "My name is mike." );
		myEditor.commit();
	}
 
	protected void clearMyPrefs() {
		SharedPreferences myPrefs = getSharedPreferences(PREF_ID, actMode);
		SharedPreferences.Editor myEditor = myPrefs.edit();
		myEditor.clear();
		myEditor.commit();
	}

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
