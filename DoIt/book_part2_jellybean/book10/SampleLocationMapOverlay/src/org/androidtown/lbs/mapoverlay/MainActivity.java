package org.androidtown.lbs.mapoverlay;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

/**
 * ���� ��ġ�� ������ �����ְ� �� ���� �������̸� �߰��ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * Google APIs ���� �ϳ��� �÷������� �����ؾ� �մϴ�.
 * ���ͳ� ������ �־�� �մϴ�.
 * uses-library�� ����ؾ� �մϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends MapActivity {

	MapView mapView;

	private GeoPoint geoPt;
	private List<Overlay> mapOverlays;
	private Drawable bankMarker;
	BankItemOverlay overlay;
	private MyLocationOverlay me;

	private CompassView mCompassView;
    private SensorManager mSensorManager;
    private boolean mCompassEnabled;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ���� ���̾ƿ� ��ü ����
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainlayout);

        // �ʺ� ��ü ����
   	 	mapView = (MapView) findViewById (R.id.mapview);
   	 	mapView.setBuiltInZoomControls(true);
   	 	mapOverlays = mapView.getOverlays();

   	 	// ���� ������ ��ü ����
   	 	mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

   	 	// ���� ������ �������� ��ü �����Ͽ� �߰�
   	 	me = new MyLocationOverlay(this, mapView);
		mapOverlays.add(me);

		// ��ħ���� ǥ���� �� ����
		boolean sideBottom = true;
	   	mCompassView = new CompassView(this, sideBottom);
	    mCompassView.setVisibility(View.VISIBLE);

	    final RelativeLayout.LayoutParams compassParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	    compassParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	    compassParams.addRule(sideBottom ? RelativeLayout.ALIGN_PARENT_BOTTOM : RelativeLayout.ALIGN_PARENT_TOP);
	    mainLayout.addView(mCompassView, compassParams);

	    mCompassEnabled = true;
	    
	    // ��ġ Ȯ���� ���� ������ �޼ҵ� ȣ��
   	 	startLocationService();
    }

    @Override
	public void onResume() {
		super.onResume();

		// �� ��ġ �ڵ� ǥ�� enable
		me.enableMyLocation();
		if(mCompassEnabled) {
            mSensorManager.registerListener(mListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
		}
	}

	@Override
	public void onPause() {
		super.onPause();

		// �� ��ġ �ڵ� ǥ�� disable
		me.disableMyLocation();
		if(mCompassEnabled) {
			mSensorManager.unregisterListener(mListener);
		}
	}


    protected boolean isRouteDisplayed() {
		return false;
	}

    /**
     * ��ġ Ȯ���� ���� ������ �޼ҵ�
     */
    private void startLocationService() {
    	// ��ġ ������ ��ü ����
    	LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    	// ������ ��ü ����
    	GPSListener gpsListener = new GPSListener();
    	NetworkListener networkListener = new NetworkListener();
		long minTime = 10000;
		float minDistance = 0;

		// GPS ��ġ ��û
		manager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,
					minTime,
					minDistance,
					gpsListener);
		
		// ��Ʈ��ũ ��ġ ��û
		manager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER,
					minTime,
					minDistance,
					networkListener);

		Toast.makeText(getApplicationContext(), "��ġ Ȯ�� ����. �α׸� Ȯ���ϼ���.", Toast.LENGTH_SHORT).show();
    }

    /**
     * ������ ����
     */
	private class GPSListener implements LocationListener {
		/**
		 * ��ġ�� Ȯ�εǸ� ȣ��Ǵ� �޼ҵ�
		 */
	    public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
			Log.i("GPSListener", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

			// ���� ��ġ�� ���� �����ֱ�
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
     * ������ ����
     */
	private class NetworkListener implements LocationListener {
		/**
		 * ��ġ�� Ȯ�εǸ� ȣ��Ǵ� �޼ҵ�
		 */
	    public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
			Log.i("NetworkListener", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

			// ���� ��ġ�� ���� �����ֱ�
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
	 * ���� ��ġ�� ���� �����ֱ� ���� ������ �޼ҵ�
	 * 
	 * @param latitude
	 * @param longitude
	 */
	private void showCurrentLocation(Double latitude, Double longitude) {
		double intLatitude = latitude.doubleValue() * 1000000;
		double intLongitude = longitude.doubleValue() * 1000000;

		// ���� ��ġ�� GeoPoint ��ü�� ����
		geoPt = new GeoPoint((int) intLatitude, (int) intLongitude);

		MapController controller = mapView.getController();
		controller.animateTo(geoPt);

		int maxZoomlevel = mapView.getMaxZoomLevel();
		int zoomLevel = (int) ((maxZoomlevel + 1)/1.15);
		controller.setZoom(zoomLevel);
		controller.setCenter(geoPt);

		mapView.setSatellite(false);
		mapView.setTraffic(false);

		// ���� ��ġ ������ �������� ǥ���ϱ� ���� ������ �޼ҵ�
		showAllBankItems();
	}

	/**
	 * �������� ǥ���ϱ� ���� ������ �޼ҵ�
	 */
	private void showAllBankItems() {
		int ICON_BANK = R.drawable.bank;
		String msg = "�� ������ : \n ��������(����������)\n"
					+ "�� �ּ� : \n ����� ���Ǳ� �����뵿";

		showItem(geoPt, ICON_BANK, "��������", msg);

	}

	/**
	 * ���� ������ ǥ��
	 * 
	 * @param aPoint
	 * @param markIcon
	 * @param title
	 * @param contents
	 */
	public void showItem(GeoPoint aPoint, int markIcon, String title, String contents) {
		if (mapOverlays == null) {
			mapOverlays = mapView.getOverlays();
		}

		if (bankMarker == null) {
			bankMarker = this.getResources().getDrawable(markIcon);
			bankMarker.setBounds(0, 0, bankMarker.getIntrinsicWidth(), bankMarker.getIntrinsicWidth());
		}

		GeoPoint bankPoint = new GeoPoint(aPoint.getLatitudeE6()+1000, aPoint.getLongitudeE6()+1000);

		overlay = new BankItemOverlay(bankMarker, this);
		OverlayItem overlayItem = new OverlayItem (bankPoint,  title,  contents);

		overlay.addOverlay(overlayItem);
		mapOverlays.add(overlay);

	}

	/**
	 * ������ ������ �ޱ� ���� ������ ��ü ����
	 */
	private final SensorEventListener mListener = new SensorEventListener() {
        private int iOrientation = -1;

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        // ������ ���� ���� �� �ֵ��� ȣ��Ǵ� �޼ҵ�
        public void onSensorChanged(SensorEvent event) {
            if (iOrientation < 0) {
                    iOrientation = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
            }
            
            mCompassView.setAzimuth(event.values[0] + 90 * iOrientation);
            mCompassView.invalidate();

        }

	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
