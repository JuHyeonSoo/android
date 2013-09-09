package org.androidtown.lbs.proximity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ������ ������ ��ǥ�� �����ϰ� �� ������ ������ ���� �� �� �� �ִ� �����溸 ��� ��� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private LocationManager mLocationManager;
    private CoffeeIntentReceiver mIntentReceiver;

    ArrayList mPendingIntentList;

    String intentKey = "coffeeProximity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ��ġ ������ ��ü ����
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mPendingIntentList = new ArrayList();

        // ��ư �̺�Ʈ ó��
        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {

        		// register two targets
        		int countTargets = 2;
        		register(1001, 36.222222, 126.222222, 200, -1);
        		register(1002, 38.222222, 128.222222, 200, -1);

        		TextView textView01 = (TextView) findViewById(R.id.textView01);
        		textView01.setText("1001 : " + "36.222222, 126.222222");

        		TextView textView02 = (TextView) findViewById(R.id.textView02);
        		textView02.setText("1002 : " + "38.222222, 128.222222");

        		// ������ ��ü �����Ͽ� ���
                mIntentReceiver = new CoffeeIntentReceiver(intentKey);
                registerReceiver(mIntentReceiver, mIntentReceiver.getFilter());

        		Toast.makeText(getApplicationContext(), countTargets + "�� ������ ���� ���� ������ ���", 1000).show();
        	}
        });

        Button stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		unregister();
        		Toast.makeText(getApplicationContext(), "���� ������ ����", 1000).show();
        	}
        });

    }
 
    /**
     * register the proximity intent receiver
     */
    private void register(int id, double latitude, double longitude, float radius, long expiration) {
        Intent proximityIntent = new Intent(intentKey);
        proximityIntent.putExtra("id", id);
        proximityIntent.putExtra("latitude", latitude);
        proximityIntent.putExtra("longitude", longitude);
        PendingIntent intent = PendingIntent.getBroadcast(this, id, proximityIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        mLocationManager.addProximityAlert(latitude, longitude, radius, expiration, intent);

        mPendingIntentList.add(intent);
    }

    public void onStop() {
    	super.onStop();

    	unregister();
    }

    /**
     * ����� ���� ����
     */
    private void unregister() {
        if (mPendingIntentList != null) {
        	for (int i = 0; i < mPendingIntentList.size(); i++) {
        		PendingIntent curIntent = (PendingIntent) mPendingIntentList.get(i);
        		mLocationManager.removeProximityAlert(curIntent);
        		mPendingIntentList.remove(i);
        	}
        }

        if (mIntentReceiver != null) {
            unregisterReceiver(mIntentReceiver);
            mIntentReceiver = null;
        }
    }

    /**
     * ��ε�ĳ���� �޽����� �޾��� �� ó���� ������ ����
     */
    private class CoffeeIntentReceiver extends BroadcastReceiver {

        private String mExpectedAction;
        private Intent mLastReceivedIntent;

        public CoffeeIntentReceiver(String expectedAction) {
            mExpectedAction = expectedAction;
            mLastReceivedIntent = null;
        }

        public IntentFilter getFilter() {
            IntentFilter filter = new IntentFilter(mExpectedAction);
            return filter;
        }

        /**
         * �޾��� �� ȣ��Ǵ� �޼ҵ�
         * 
         * @param context
         * @param intent
         */
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                mLastReceivedIntent = intent;

                int id = intent.getIntExtra("id", 0);
                double latitude = intent.getDoubleExtra("latitude", 0.0D);
                double longitude = intent.getDoubleExtra("longitude", 0.0D);

                Toast.makeText(context, "������ Ŀ�Ǽ� : " + id + ", " + latitude + ", " + longitude, 2000).show();
            }
        }

        public Intent getLastReceivedIntent() {
            return mLastReceivedIntent;
        }

        public void clearReceivedIntents() {
            mLastReceivedIntent = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
