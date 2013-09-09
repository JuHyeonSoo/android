package org.androidtown.lbs.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

/**
 * ���� ��ġ�� ������ �����ִ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * Google APIs ���� �ϳ��� �÷������� �����ؾ� �մϴ�.
 * ���ͳ� ������ �־�� �մϴ�.
 * uses-library�� ����ؾ� �մϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends MapActivity {

	MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �ʺ� ��ü ����
   	 	mapView = (MapView) findViewById (R.id.mapview);
   	 	mapView.setBuiltInZoomControls(true);

   	 	// ���� ��ġ Ȯ���� ���� ������ �޼ҵ� ȣ��
   	 	startLocationService();
    }

    protected boolean isRouteDisplayed() {
		return false;
	}

    /**
     * ���� ��ġ Ȯ���� ���� ������ �޼ҵ�
     */
    private void startLocationService() {
    	// ��ġ ������ ��ü ����
    	LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// ������ ��ü ����
    	GPSListener gpsListener = new GPSListener();
		long minTime = 10000;
		float minDistance = 0;

		// ��ġ ��û
		manager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,
					minTime,
					minDistance,
					gpsListener);

		Toast.makeText(getApplicationContext(), "��ġ Ȯ�� ������. �α׸� Ȯ���ϼ���.", Toast.LENGTH_SHORT).show();
    }

    /**
     * ������ ����
     */
	private class GPSListener implements LocationListener {
		/**
		 * ��ġ ������ Ȯ�εǾ��� �� ȣ��Ǵ� �޼ҵ�
		 */
	    public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
			Log.i("GPSLocationService", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

			// ���� ��ġ�� ������ �����ֱ� ���� ������ �޼ҵ� ȣ��
			showCurrentLocation(latitude, longitude);
		}

	    public void onProviderDisabled(String provider) {
	    }

	    public void onProviderEnabled(String provider) {
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }

	}

	/**
	 * ���� ��ġ�� ������ �����ֱ� ���� ������ �޼ҵ�
	 * 
	 * @param latitude
	 * @param longitude
	 */
	private void showCurrentLocation(Double latitude, Double longitude) {
		double intLatitude = latitude.doubleValue() * 1000000;
		double intLongitude = longitude.doubleValue() * 1000000;

		// ���� ��ġ ��ǥ�� �̿��� GeoPoint ��ü ����
		GeoPoint geoPt = new GeoPoint((int) intLatitude, (int) intLongitude);

		// ���� ��ġ�� �̵��ϱ� ���� ��Ʈ�ѷ� ��ü ����
		MapController controller = mapView.getController();
		controller.animateTo(geoPt);

		int maxZoomlevel = mapView.getMaxZoomLevel();
		int zoomLevel = (int) ((maxZoomlevel + 1)/1.15);
		controller.setZoom(zoomLevel);
		controller.setCenter(geoPt);

		mapView.setSatellite(true);
		mapView.setTraffic(false);

	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
