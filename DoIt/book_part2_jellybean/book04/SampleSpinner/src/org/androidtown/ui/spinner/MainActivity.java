package org.androidtown.ui.spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * ���� �ڵ�� ���ǳʸ� ����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

	/**
	 * ���õ� ���� ǥ���� �ؽ�Ʈ��
	 */
	TextView text1;
	
	/**
	 * ���ǳʸ� ���� ������ ����
	 */
	String[] items = { "mike", "angel", "crow", "john", "ginnie", "sally", "cohen", "rice" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // �ؽ�Ʈ�� ��ü ����
        text1 = (TextView) findViewById(R.id.text1);
        
        // ���ǳ� ��ü ����
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        // ����� ��ü ����
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        		this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        // ����� ����
        spin.setAdapter(adapter);
    }

    /**
     * �������� ���õǾ��� �� ó��
     */
	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		text1.setText(items[position]);
	}

	/**
	 * �ƹ��͵� ���õ��� �ʾ��� �� ó��
	 */
	public void onNothingSelected(AdapterView<?> parent) {
		text1.setText("");
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
