package com.ai.android.search.simplesp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.util.Log;

public class  SearchActivity extends Activity
{
	private final static String tag ="SearchActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(tag,"���� �����ǰ� �ֽ��ϴ�.");
		// �׷��� ������ ������ ���� setContentView(R.layout.layout_test_search_activity);
		//this.setDefaultKeyMode(Activity.DEFAULT_KEYS_SEARCH_GLOBAL);
		this.setDefaultKeyMode(Activity.DEFAULT_KEYS_SEARCH_LOCAL);
		
		// �˻� ���Ǹ� �����ͼ� ó��
		final Intent queryIntent = getIntent();
		final String queryAction  = queryIntent.getAction();
		if (Intent.ACTION_SEARCH.equals(queryAction))
		{
			Log.d(tag,"�˻��� �� ����Ʈ");
			this.doSearchQuery(queryIntent);
		}
		else  {
			Log.d(tag,"�� �˻��� �� ����Ʈ");
		}
		return;
	}

	@Override
	public void  onNewIntent(final Intent  newIntent)
	{
		super.onNewIntent(newIntent);
		Log.d(tag,"new intent  calling  me");
		
		// �˻� ���Ǹ� �����ͼ� ó��
		final  Intent queryIntent = getIntent();
		final String  queryAction = queryIntent.getAction();
		if (Intent.ACTION_SEARCH.equals(queryAction))
		{
			this.doSearchQuery(queryIntent); Log.d(tag,"�˻��� �� ����Ʈ");
		}
		else  {
			Log.d(tag,"�� �˻��� �� ����Ʈ");
		}
	}
	private void  doSearchQuery(final Intent queryIntent)
	{
		final  String queryString = queryIntent.getStringExtra(SearchManager.QUERY);
		
		// �ֱ� ���� �����׸� ���ι��̴��� ���� ���ڿ��� ���
		SearchRecentSuggestions suggestions  = new SearchRecentSuggestions(this, SimpleSuggestionProvider.AUTHORITY, SimpleSuggestionProvider.MODE);
		suggestions.saveRecentQuery(queryString, null);
	}
}
