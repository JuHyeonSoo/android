package com.ai.android.search.custom;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SearchActivity extends Activity
{
	private final static String tag = "SearchActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d(tag,"���� �����Ǵ� ��");
		setContentView(R.layout.layout_search_activity);
		
		// �˻� ���Ǹ� �����ͼ� ó��
		final  Intent queryIntent = getIntent();
		
		// ���� �׼�
		final  String queryAction = queryIntent.getAction();
		Log.d(tag,"����Ʈ �׼� ����:"+queryAction);
		
		final String  queryString = queryIntent.getStringExtra(SearchManager.QUERY);
		Log.d(tag,"����Ʈ ���� ����:"+queryString);
		
		if (Intent.ACTION_SEARCH.equals(queryAction))
		{
			this.doSearchQuery(queryIntent);
		}
		else if (Intent.ACTION_VIEW.equals(queryAction))
		{
			this.doView(queryIntent);
		}
		else  {
			Log.d(tag,"�˻� �ܷ̿κ��� ����Ʈ ����");
		}
		return;
	}
	
	@Override
	public void  onNewIntent(final Intent  newIntent)
	{
		super.onNewIntent(newIntent); Log.d(tag,"���� ȣ���ϴ� �� ����Ʈ");

		// �˻� ���Ǹ� �����ͼ� ó��
		final  Intent queryIntent = newIntent;

		// ���� �׼�
		final String  queryAction = queryIntent.getAction();
		Log.d(tag,"�� ����Ʈ �׼�:"+queryAction);

		final  String queryString =
		queryIntent.getStringExtra(SearchManager.QUERY);
		Log.d(tag,"�� ����Ʈ ����:"+queryString);

		if (Intent.ACTION_SEARCH.equals(queryAction))
		{
			this.doSearchQuery(queryIntent);
		}
		else if (Intent.ACTION_VIEW.equals(queryAction))
		{
			this.doView(queryIntent);
		}
		else  {
		Log.d(tag,"�˻����� ȣ����� ���� �� ����Ʈ");
		}
		return;
	}
	private void  doSearchQuery(final Intent queryIntent)
	{
		final  String queryString =
		queryIntent.getStringExtra(SearchManager.QUERY);
		appendText("�˻� ���� ����:"  + queryString);
	}
	private void  appendText(String msg)
	{
		TextView tv = (TextView)this.findViewById(R.id.text1);
		tv.setText(tv.getText() + "\n" + msg);
	}
	private void  doView(final Intent  queryIntent)
	{
		Uri uri = queryIntent.getData();
		String action = queryIntent.getAction();
		Intent i = new Intent(action);
		i.setData(uri);
		startActivity(i);
		this.finish();
	}
}
