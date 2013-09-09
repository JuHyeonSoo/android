package org.androidtown.lbs.geocoding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * �ּҷ� ��ġ�� ã�ų� ��ġ ��ǥ�� �̿��� �ּҸ� ã�� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {
	private static String TAG = "MainActivity";
	
	TextView contentsText;
	Geocoder gc;
	
	EditText edit01;
	EditText edit02;
	EditText edit03;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		edit01 = (EditText) findViewById(R.id.edit01);
		edit02 = (EditText) findViewById(R.id.edit02);
		edit03 = (EditText) findViewById(R.id.edit03);
		contentsText = (TextView) findViewById(R.id.contentsText);

		// ��ư �̺�Ʈ ó��
		Button show_btn = (Button) findViewById(R.id.show_btn);
		show_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ����ڰ� �Է��� �ּ� ���� Ȯ��
				String searchStr = edit01.getText().toString();
				
				// �ּ� ������ �̿��� ��ġ ��ǥ ã�� �޼ҵ� ȣ��
				searchLocation(searchStr);
			}
		});

		// ��ư �̺�Ʈ ó��
		Button show_btn2 = (Button) findViewById(R.id.show_btn2);
		show_btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ����ڰ� �Է��� ��ġ ��ǥ Ȯ��
				String LatStr = edit02.getText().toString();
				String LonStr = edit03.getText().toString();
				double latitude = 0.0D;
				double longitude = 0.0D;

				try {
					latitude = Double.parseDouble(LatStr);
					longitude = Double.parseDouble(LonStr);
				} catch(NumberFormatException ex) {
					Log.d(TAG, "���� : " + ex.toString());
				}

				// ��ġ ��ǥ�� �̿��� �ּҸ� �˻��ϴ� �޼ҵ� ȣ��
				searchLocation(latitude, longitude);
			}
		});

		// �����ڴ� ��ü ����
		gc = new Geocoder(this, Locale.KOREAN);

    }

    /**
     * �ּҸ� �̿��� ��ġ ��ǥ�� ã�� �޼ҵ� ����
     */
    private void searchLocation(String searchStr) {
    	// ������� �� ����Ʈ ����
    	List<Address> addressList = null;

    	try {
    		addressList = gc.getFromLocationName(searchStr, 3);

    		if (addressList != null) {
    			contentsText.append("\nCount of Addresses for [" + searchStr + "] : " + addressList.size());
    			for (int i = 0; i < addressList.size(); i++) {
    				Address outAddr = addressList.get(i);
    				int addrCount = outAddr.getMaxAddressLineIndex() + 1;
    				StringBuffer outAddrStr = new StringBuffer();
    				for (int k = 0; k < addrCount; k++) {
    					outAddrStr.append(outAddr.getAddressLine(k));
    				}
    				outAddrStr.append("\n\tLatitude : " + outAddr.getLatitude());
    				outAddrStr.append("\n\tLongitude : " + outAddr.getLongitude());

    				contentsText.append("\n\tAddress #" + i + " : " + outAddrStr.toString());
    			}
    		}

    	} catch(IOException ex) {
    		Log.d(TAG, "���� : " + ex.toString());
    	}

    }

    /**
     * ��ġ ��ǥ�� �̿��� �ּҸ� �˻��ϴ� �޼ҵ� ����
     */
    private void searchLocation(double latitude, double longitude) {
    	List<Address> addressList = null;

    	try {
    		addressList = gc.getFromLocation(latitude, longitude, 3);

    		if (addressList != null) {
    			contentsText.append("\nCount of Addresses for [" + latitude + ", " + longitude + "] : " + addressList.size());
    			for (int i = 0; i < addressList.size(); i++) {
    				Address outAddr = addressList.get(i);
    				int addrCount = outAddr.getMaxAddressLineIndex() + 1;
    				StringBuffer outAddrStr = new StringBuffer();
    				for (int k = 0; k < addrCount; k++) {
    					outAddrStr.append(outAddr.getAddressLine(k));
    				}
    				outAddrStr.append("\n\tLatitude : " + outAddr.getLatitude());
    				outAddrStr.append("\n\tLongitude : " + outAddr.getLongitude());

    				contentsText.append("\n\tAddress #" + i + " : " + outAddrStr.toString());
    			}
    		}

    	} catch(IOException ex) {
    		Log.d(TAG, "���� : " + ex.toString());
    	}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
