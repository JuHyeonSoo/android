package org.androidtown.basic.receiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;

/**
 * SMS�� �����ϴ� ��ε�ĳ��Ʈ �������Դϴ�.
 * �����ڸ� ������ Ŭ������ ������ �� ����Ͽ� ����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ��ε�ĳ��Ʈ �����ڷ� ������ Ŭ������ �ν��Ͻ� ��ü�� �����մϴ�.
        MySMSBroadcastReceiver myReceiver = new MySMSBroadcastReceiver();

        // SMS�� �ޱ� ���� ����Ʈ����(SMS_RECEIVED)�� �����մϴ�.
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        // �����ڸ� �ڵ忡�� ����մϴ�. �Ŵ��佺Ʈ�� ����ϸ� �� �κ��� ���� �����մϴ�.
        //registerReceiver(myReceiver, filter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
