package org.androidtown.menu.option;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * �޴��� ����ϴ� ���� �������� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    /**
     * XML�� ������ �޴� ������ �޸𸮿� �ε��Ͽ� ���� ȭ���� �ɼ� �޴��� �����մϴ�.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * �޴��� ������ �� �ڵ� ȣ��˴ϴ�.
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int curId = item.getItemId();
		switch(curId) {
			case R.id.menu_refresh:
				Toast.makeText(this, "���ΰ�ħ �޴��� ���õǾ����ϴ�.", Toast.LENGTH_SHORT).show();
				break;
			case R.id.menu_search:
				Toast.makeText(this, "�˻� �޴��� ���õǾ����ϴ�.", Toast.LENGTH_SHORT).show();
				break;
			case R.id.menu_settings:
				Toast.makeText(this, "���� �޴��� ���õǾ����ϴ�.", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
    
}
