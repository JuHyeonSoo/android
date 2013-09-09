package kr.co.infinity.IntentServiceTest;

import android.content.Intent;
import android.util.Log;
import android.app.IntentService;

public class MyIntentService extends IntentService {

	/**
	 * A constructor is required, and must call the super constructor with a
	 * name for the worker thread.
	 */
	public MyIntentService() {
		super("MyIntentService");
	}

	/**
	 * The IntentService calls this method from the default worker thread with
	 * the intent that started the service. When this method returns,
	 * IntentService stops the service, as appropriate.
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// ���⼭ �ð��� ���� �ɸ��� �۾��� �����ϸ� �ȴ�.
		// ���� �� ���ͳݿ��� ������ �ٿ�ε� ���� �� �ִ�.
		// ������ �ܼ��� 5�� ���� ��ٷȴٰ� �����Ѵ�.
		long endTime = System.currentTimeMillis() + 5 * 1000;
		Log.i("SERVICE", "onHandleIntent() ");
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		}
	}

}