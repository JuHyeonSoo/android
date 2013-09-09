package org.androidtown.basic.receiver.sms;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * SMS ������ ���� ��ε�ĳ��Ʈ �������Դϴ�.
 * 
 * @author Mike
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {

	/**
	 * �α��� ���� �±�
	 */
	public static final String TAG = "SMSBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "onReceive() �޼ҵ� ȣ���.");

        // SMS ���� ���� �޽������� �ٽ� �ѹ� Ȯ���մϴ�.
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Log.i(TAG, "SMS�� �����Ͽ����ϴ�.");

            // �켱������ ���� �ٸ� SMS ���� �۵��� ���ڸ� ���޹��� ���ϵ��� ������ ����մϴ�.
            abortBroadcast();

            // SMS �޽����� �Ľ��մϴ�.
            Bundle bundle = intent.getExtras();
    		Object messages[] = (Object[])bundle.get("pdus");
    		SmsMessage smsMessage[] = new SmsMessage[messages.length];

    		int smsCount = messages.length;
    		for(int i = 0; i < smsCount; i++) {
    			// PDU �������� �Ǿ� �ִ� �޽����� �����մϴ�.
    			smsMessage[i] = SmsMessage.createFromPdu((byte[])messages[i]);
    		}

    		// SMS ���� �ð� Ȯ��
    		Date curDate = new Date(smsMessage[0].getTimestampMillis());
    		Log.i(TAG, "SMS Timestamp : " + curDate.toString());

    		// SMS �߽� ��ȣ Ȯ��
    		String origNumber = smsMessage[0].getOriginatingAddress();
    		
    		// SMS �޽��� Ȯ��
    		String message = smsMessage[0].getMessageBody().toString();
    		Log.i(TAG, "SMS : " + origNumber + ", " + message);

            // �޽����� ������ ��Ƽ��Ƽ�� ����ݴϴ�.
            Intent myIntent = new Intent(context, SMSDisplayActivity.class);
            
            // �÷��׸� �̿��մϴ�.
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            
            myIntent.putExtra("number", origNumber);
            myIntent.putExtra("message", message);
            myIntent.putExtra("timestamp", curDate.toString());

            context.startActivity(myIntent);

        }

	}

}
