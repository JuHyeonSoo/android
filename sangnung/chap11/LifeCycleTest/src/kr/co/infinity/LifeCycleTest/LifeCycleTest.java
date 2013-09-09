package kr.co.infinity.LifeCycleTest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LifeCycleTest extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("LifeCycle", "onStart() ȣ��");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("LifeCycle", "onStop() ȣ��");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i("LifeCycle", "onResume() ȣ��");
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.i("LifeCycle", "onRestart() ȣ��");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("LifeCycle", "onDestroy() ȣ��");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i("LifeCycle", "onPause() ȣ��");
	}
}