package  com.syh;
// �� ������ StockQuoteService2.java�̴�.

import com.syh.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public  class  StockQuoteService2 extends Service
{
	private  NotificationManager notificationMgr;
	public class StockQuoteServiceImpl extends IStockQuoteService.Stub
	{
		
		@Override
		public String getQuote(String ticker, Person requester)
		throws RemoteException   {
			return  "�ȳ��ϼ���, "+requester.getName()+"!"+ticker+"�� �ְ��� 20.0 �Դϴ�.";
		}
	
	}
	
	@Override
	public void  onCreate()  {
		super.onCreate();
		
		notificationMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		displayNotificationMessage("onCreate()�� StockQuoteService2���� ȣ���");
	}
	@Override
	public void  onDestroy()
	{
		displayNotificationMessage("onDestroy()�� StockQuoteService2���� ȣ���");
		super.onDestroy();
	}
	
	@Override
	public void  onStart(Intent intent, int startId)  {
		super.onStart(intent, startId);
	}
	
	@Override
	public  IBinder onBind(Intent intent)
	{
		displayNotificationMessage("onBind()�� StockQuoteService2���� ȣ���");
		return new StockQuoteServiceImpl();
	}
	
	private void  displayNotificationMessage(String  message)
	{
		Notification notification  = new Notification(R.drawable.note,
		message,System.currentTimeMillis());
		
		PendingIntent contentIntent  =
		PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
		
		notification.setLatestEventInfo(this, "StockQuoteService2",message, contentIntent);
		
		notificationMgr.notify(R.id.app_notification_id, notification);
	}
}
