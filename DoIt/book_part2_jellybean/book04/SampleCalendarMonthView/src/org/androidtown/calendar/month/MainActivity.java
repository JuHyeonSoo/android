package org.androidtown.calendar.month;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

/**
 * �׸���並 �̿��� ���� Ķ������ ����� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * ���� Ķ���� �� ��ü
	 */
	CalendarMonthView monthView;
	
	/**
	 * ���� Ķ���� �����
	 */
	CalendarMonthAdapter monthViewAdapter;

	/**
	 * ���� ǥ���ϴ� �ؽ�Ʈ��
	 */
	TextView monthText;

	/**
	 * ���� ����
	 */
	int curYear;
	
	/**
	 * ���� ��
	 */
	int curMonth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ���� Ķ���� �� ��ü ����
        monthView = (CalendarMonthView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // ������ ����
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v, int position, long id) {
				// ���� ������ ���� ���� ǥ��
				MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
				int day = curItem.getDay();

				Log.d("CalendarMonthViewActivity", "Selected : " + day);

			}
		});

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // ���� ���� �Ѿ�� �̺�Ʈ ó��
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		monthViewAdapter.setPreviousMonth();
        		monthViewAdapter.notifyDataSetChanged();

        		setMonthText();
        	}
        });

        // ���� ���� �Ѿ�� �̺�Ʈ ó��
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		monthViewAdapter.setNextMonth();
        		monthViewAdapter.notifyDataSetChanged();

        		setMonthText();
        	}
        });

    }

    /**
     * �� ǥ�� �ؽ�Ʈ ����
     */
    private void setMonthText() {
    	curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "�� " + (curMonth+1) + "��");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
