package org.androidtown.sensor;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

/**
 * �ܸ��� ���� ������ �˾Ƴ��� ���� ���� Ȯ���ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends ListActivity {
	public static final String TAG = "MainActivity";

	SensorManager manager = null;
	List<Sensor> sensors = null;

	SensorListAdapter adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ���� �Ŵ��� ��ü ����
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        
        // ��� ���� ��ü ��������
		sensors = manager.getSensorList(Sensor.TYPE_ALL);

		// ����Ʈ�� ����� ����
        adapter = new SensorListAdapter(this, R.layout.listitem, sensors);
        setListAdapter(adapter);
    }

    /**
     * ����Ʈ�� �� �������� Ŭ���Ǿ��� �� ȣ��Ǵ� �޼ҵ�
     */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Sensor sensor = sensors.get(position);
		String sensorName = sensor.getName();
		Log.d(TAG, "���õ� ���� : " + sensorName);

		Intent intent = new Intent(this, SensorDataActivity.class);
		intent.putExtra(SensorDataActivity.SENSOR_INDEX, position);
		startActivity(intent);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
