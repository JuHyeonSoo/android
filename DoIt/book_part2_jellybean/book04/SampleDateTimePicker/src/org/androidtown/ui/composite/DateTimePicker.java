package org.androidtown.ui.composite;

import java.util.Calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

/**
 * �������� Ŭ���� ����
 * 
 * @author Mike
 *
 */
public class DateTimePicker extends LinearLayout {
	/**
	 * ��¥�� �ð��� �ٲ� �� ȣ��Ǵ� ������ ���� ����
	 */
	public interface OnDateTimeChangedListener {
		void onDateTimeChanged(DateTimePicker view, int year, int monthOfYear, int dayOfYear, int hourOfDay, int minute);
	}
	
	/**
	 * ������ ��ü
	 */
	private OnDateTimeChangedListener onDateTimeChangedListener;
	
	/**
	 * ��¥���� ����
	 */
	private final DatePicker datePicker;
	
	/**
	 * üũ�ڽ�
	 */
	private final CheckBox enableTimeCheckBox;
	
	/**
	 * �ð����� ����
	 */
	private final TimePicker timePicker;
	
	/**
	 * ������
	 * 
	 * @param context
	 */
	public DateTimePicker(final Context context){
		this(context, null);
	}
	
	/**
	 * ������
	 * 
	 * @param context
	 * @param attrs
	 */
	public DateTimePicker(Context context, AttributeSet attrs){
		super(context, attrs);
		
		// XML ���̾ƿ��� ���÷��̼���
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.datetimepicker, this, true);
		
		// �ð� ���� ����
		Calendar calendar = Calendar.getInstance();
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DataTimePicker);
		final int _currentYear = a.getInt(R.styleable.DataTimePicker_year, calendar.get(Calendar.YEAR));
		final int _currentMonth = a.getInt(R.styleable.DataTimePicker_month, calendar.get(Calendar.MONTH));
		final int _currentDay = a.getInt(R.styleable.DataTimePicker_day, calendar.get(Calendar.DAY_OF_MONTH));
		final int _currentHour = a.getInt(R.styleable.DataTimePicker_hour, calendar.get(Calendar.HOUR_OF_DAY));
		final int _currentMinute = a.getInt(R.styleable.DataTimePicker_minute, calendar.get(Calendar.MINUTE));
		
		// ��¥���� ���� �ʱ�ȭ
		datePicker = (DatePicker)findViewById(R.id.datePicker);
		datePicker.init(_currentYear, _currentMonth, _currentDay, new OnDateChangedListener() {
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// ���� ������ �����ʷ� �̺�Ʈ ����
				if(onDateTimeChangedListener != null){
					onDateTimeChangedListener.onDateTimeChanged(
							DateTimePicker.this, year, monthOfYear, dayOfMonth,
							timePicker.getCurrentHour(), timePicker.getCurrentMinute());
				}
			}
		});
		
		// üũ�ڽ� �̺�Ʈ ó��
		enableTimeCheckBox = (CheckBox)findViewById(R.id.enableTimeCheckBox);
		enableTimeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				timePicker.setEnabled(isChecked);
				timePicker.setVisibility((enableTimeCheckBox).isChecked()?View.VISIBLE:View.INVISIBLE);
			}
		});
		
		// �ð����� ���� �̺�Ʈ ó��
		timePicker = (TimePicker)findViewById(R.id.timePicker);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				if(onDateTimeChangedListener != null) {
					onDateTimeChangedListener.onDateTimeChanged(
							DateTimePicker.this, datePicker.getYear(), 
							datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
				}
			}
		});
		
		timePicker.setCurrentHour(_currentHour);
		timePicker.setCurrentMinute(_currentMinute);
		timePicker.setEnabled(enableTimeCheckBox.isChecked());
		timePicker.setVisibility((enableTimeCheckBox.isChecked()?View.VISIBLE:View.INVISIBLE));
	}
	
	public void setOnDateTimeChangedListener(OnDateTimeChangedListener onDateTimeChangedListener){
		this.onDateTimeChangedListener = onDateTimeChangedListener;
	}
	
	public void updateDateTime(int year, int monthOfYear, int dayOfMonth, int currentHour, int currentMinute){
		datePicker.updateDate(year, monthOfYear, dayOfMonth);
		timePicker.setCurrentHour(currentHour);
		timePicker.setCurrentMinute(currentMinute);
	}
	
	public void updateDate(int year, int monthOfYear, int dayOfMonth){
		datePicker.updateDate(year, monthOfYear, dayOfMonth);
	}
	
	public void setIs24HourView(final boolean is24HourView){
		timePicker.setIs24HourView(is24HourView);
	}
	
	public int getYear() {
		return datePicker.getYear();
	}
	
	public int getMonth() {
		return datePicker.getMonth();
	}
	
	public int getDayOfMonth() {
		return datePicker.getDayOfMonth();
	}
	
	public int getCurrentHour() {
		return timePicker.getCurrentHour();
	}
	
	public int getCurrentMinute() {
		return timePicker.getCurrentMinute();
	}
	
	public boolean enableTime() {
		return enableTimeCheckBox.isChecked();
	}
}
