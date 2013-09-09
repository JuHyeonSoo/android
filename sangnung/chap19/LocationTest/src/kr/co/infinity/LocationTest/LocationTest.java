package kr.co.infinity.LocationTest;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;


public class LocationTest extends Activity {

	TextView status;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		status = (TextView) findViewById(R.id.status);

		// ��ġ �����ڿ� ���� �������� ���Ѵ�.
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// ��ġ�� ������Ʈ�Ǹ� ȣ��Ǵ� �����ʸ� �����Ѵ�.
		LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// ���ο� ��ġ�� �߰ߵǸ� ��ġ �����ڿ� ���Ͽ� ȣ��ȴ�.
				status.setText("����; " + location.getLatitude() + "\n�浵:"
						+ location.getLongitude() + "\n��:"
						+ location.getAltitude());
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		// ��ġ�� ������Ʈ �ޱ� ���Ͽ� �����ʸ� ��ġ �����ڿ� ����Ѵ�.
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, locationListener);

	}
}