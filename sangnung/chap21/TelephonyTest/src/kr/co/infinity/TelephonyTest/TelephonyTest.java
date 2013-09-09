package kr.co.infinity.TelephonyTest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class TelephonyTest extends Activity {
	private TelephonyManager manager;
	PhoneStateListener listener = new PhoneStateListener() {
		public void onCallStateChanged(int state, String incomingNumber) {
			Toast.makeText(TelephonyTest.this,
					"����:  " + state + "��ȭ��ȣ: " + incomingNumber, 0).show();

		}

		public void onDataActivity(int direction) {
			Toast.makeText(TelephonyTest.this, "���� : " + direction, 0).show();
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String s = "����: " + manager.getCallState() + "\n��ȭ��ȣ: "
				+ manager.getLine1Number() + "\n������ ����: "
				+ manager.getDataState() + "\n��ġ ���̵�: " + manager.getDeviceId()
				+ "\n��Ʈ��ũ Ÿ��: " + manager.getNetworkType() + "\n��ȭ�� Ÿ��: "
				+ manager.getPhoneType() + "\n�ι� ����: "
				+ manager.isNetworkRoaming();
		TextView text = (TextView) findViewById(R.id.text);
		text.setText(s);
		manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE
				| PhoneStateListener.LISTEN_DATA_ACTIVITY);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		manager.listen(listener, PhoneStateListener.LISTEN_NONE);
	}

}