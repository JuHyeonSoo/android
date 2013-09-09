package org.androidtown.ui.orientation;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * �ܸ��� ������ �ٲ� �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ��ü ȭ�� ����
        final Window win = getWindow();
        win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * ������ �ٲ� �� ȣ���
     */
    public void onConfigurationChanged(Configuration newConfig) {
    	super.onConfigurationChanged(newConfig);
    	
    	Toast.makeText(this, "onConfigurationChanged() ȣ���", Toast.LENGTH_SHORT).show();
       
    	if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
    		Toast.makeText(this, "Orientation : ORIENTATION_LANDSCAPE", Toast.LENGTH_SHORT).show();
    	} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
    	  Toast.makeText(this, "Orientation : ORIENTATION_PORTRAIT", Toast.LENGTH_SHORT).show();
    	}

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
