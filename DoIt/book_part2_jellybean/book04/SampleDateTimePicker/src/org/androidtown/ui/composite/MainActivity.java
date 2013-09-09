package org.androidtown.ui.composite;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.androidtown.ui.composite.DateTimePicker.OnDateTimeChangedListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

/**
 * ��¥�� �ð��� �Ѳ����� ������ �� �ִ� ���������� ����� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy�� MM�� dd�� HH�� mm��");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView text01 = (TextView)findViewById(R.id.text01);
        final DateTimePicker dateTimePicker = (DateTimePicker)findViewById(R.id.dateTimePicker);

        // �̺�Ʈ ó��
        dateTimePicker.setOnDateTimeChangedListener(new OnDateTimeChangedListener() {
			public void onDateTimeChanged(DateTimePicker view, int year,
					int monthOfYear, int dayOfYear, int hourOfDay, int minute) {
				Calendar calendar = Calendar.getInstance();
				calendar.set(year, monthOfYear, dayOfYear, hourOfDay, minute);
				
				// �ٲ� �ð� �ؽ�Ʈ�信 ǥ��
				text01.setText(dateFormat.format(calendar.getTime()));
			}
		});

        // ���� �ð� �ؽ�Ʈ�信 ǥ��
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateTimePicker.getYear(), dateTimePicker.getMonth(), dateTimePicker.getDayOfMonth(), dateTimePicker.getCurrentHour(), dateTimePicker.getCurrentMinute());
        text01.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
