package org.androidtown.basic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * SMS ������ ���� ������ ��ε�ĳ��Ʈ ������
 * 
 * @author Mike
 *
 */
public class MySMSBroadcastReceiver extends BroadcastReceiver {

	/**
	 * �޽����� ���ŵǾ��� �� �ڵ����� ȣ��Ǵ� �޼ҵ��Դϴ�.
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("MySMSBroadcastReceiver", "onReceive");
		
        // SMS �׼����� ���ŵ� �޽������� Ȯ���մϴ�. �������� �޽����� ������ �� ������ �����ϱ� ���� ����մϴ�.
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.d("MySMSBroadcastReceiver", "SMS �޽����� ���ŵǾ����ϴ�.");
        
            // �켱 ������ �ڿ� �ִ� �����ڵ��� ���� ���ϵ��� ��ε�ĳ���� ������ ����մϴ�.
            abortBroadcast();
            
            // ���� ��Ƽ��Ƽ�� ����ݴϴ�. ���� ��Ƽ��Ƽ�� ����Ǿ� ���� ���� ��츦 ���� FLAG_ACTIVITY_NEW_TASK �÷��׸� ����մϴ�.
            Intent myIntent = new Intent(context, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
            
        }
	}
}
