package com.ai.android.search.custom;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

public class SuggestUrlProvider extends ContentProvider
{
	private static  final String tag = "SuggestUrlProvider";
	public static  String AUTHORITY = "com.ai.android.search.custom.suggesturlprovider";

	private static final int SEARCH_SUGGEST  = 0;
	private static final int SHORTCUT_REFRESH  = 1;
	private static final UriMatcher sURIMatcher  = buildUriMatcher();

	private static  final String[] COLUMNS  = {
		"_id", // �� ���� �ݵ�� ����־�� ��
		SearchManager.SUGGEST_COLUMN_TEXT_1,
		SearchManager.SUGGEST_COLUMN_TEXT_2,
		SearchManager.SUGGEST_COLUMN_INTENT_DATA,
		SearchManager.SUGGEST_COLUMN_INTENT_ACTION,
		SearchManager.SUGGEST_COLUMN_SHORTCUT_ID
	};
	
	private static  UriMatcher buildUriMatcher()
	{
		UriMatcher matcher  = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(AUTHORITY,
		SearchManager.SUGGEST_URI_PATH_QUERY,
		SEARCH_SUGGEST);
		matcher.addURI(AUTHORITY,
		SearchManager.SUGGEST_URI_PATH_QUERY + "/*",
		SEARCH_SUGGEST);
		matcher.addURI(AUTHORITY,
		SearchManager.SUGGEST_URI_PATH_SHORTCUT,
		SHORTCUT_REFRESH);
		matcher.addURI(AUTHORITY,
		SearchManager.SUGGEST_URI_PATH_SHORTCUT + "/*",
		SHORTCUT_REFRESH);
		return  matcher;
	}
	
	@Override
	public boolean  onCreate() {
		// ���ٸ� �۾��� ���� �ʰ� ������ ��
		Log.d(tag,"onCreate�� ȣ��Ǿ����ϴ�");
		return true;
	}
	
	@Override
	public Cursor query(Uri  uri,  String[] projection, String selection,
	String[]  selectionArgs, String sortOrder)
	{
		Log.d(tag,"���ǰ� URI" + uri + "�� ���� ȣ���");
		Log.d(tag,"����:" + selection);
		
		String query  = selectionArgs[0];
		Log.d(tag,"����:" + query);
		
		switch  (sURIMatcher.match(uri))  {
			case  SEARCH_SUGGEST:
				Log.d(tag,"�˻� ���ð� ȣ���");
				return getSuggestions(query);
			case  SHORTCUT_REFRESH:
				Log.d(tag,"�ٷΰ��� ���ΰ�ħ�� ȣ��� ");
				return null;
			default:
				throw  new IllegalArgumentException("�߸��� URL "  + uri);
		}
	}
	
	private Cursor getSuggestions(String  query)
	{
		if (query == null) return null;
		String word = getWord(query);
		if (word  == null)
		return null;
		
		Log.d(tag,"���ǰ� 3���ڸ� �ѽ��ϴ�");
		
		MatrixCursor cursor = new MatrixCursor(COLUMNS);
		//cursor.addRow(createRow(query,"row1"));
		cursor.addRow(createRow1(word));
		cursor.addRow(createRow2(word));
		return cursor;
	}
	private Object[] createRow1(String query)
	{
		return  columnValuesOfQuery(query, "android.intent.action.VIEW",
		"http://www.thefreedictionary.com/" + query,
		"freedictionary.com���� ã��",
		query);
	}
	
	private Object[] createRow2(String query)
	{
		return columnValuesOfQuery(query,
		"android.intent.action.VIEW",
		"http://www.google.com/search?hl=en&source=hp&q=define%3A/"
		+ query,
		"google.com���� ã��",
		query);
	}
	private Object[] columnValuesOfQuery(String  query,
	String intentAction,
	String url,
	String text1,
	String text2)
	{
		return new String[]  {
			query,	// ID
			text1,	// text1
			text2,	// text2
			url,	// ����Ʈ ������ (�׸��� Ŭ���� �� ���Ե�)
			intentAction, // �׼�
			SearchManager.SUGGEST_NEVER_MAKE_SHORTCUT
		};
	}
	
/*	private Cursor refreshShortcut(String shortcutId, String[] projection) {
		return null;
	}*/
	
	public String getType(Uri   uri) {
		switch  (sURIMatcher.match(uri))  {
			case   SEARCH_SUGGEST:
				return SearchManager.SUGGEST_MIME_TYPE;
			case   SHORTCUT_REFRESH:
				return SearchManager.SHORTCUT_MIME_TYPE;
			default:
				throw  new IllegalArgumentException("Unknown URL  "  + uri);
		}
	}
	
	public Uri  insert(Uri uri, ContentValues values)  {
		throw  new UnsupportedOperationException();
	}
	
	public int delete(Uri uri,  String selection,  String[]  selectionArgs) {
		throw  new UnsupportedOperationException();
	}
	
	public int  update(Uri uri, ContentValues values, String selection, String[]  selectionArgs) {
		throw  new UnsupportedOperationException();
	}
	
	private String getWord(String  query)
	{
		int  dotIndex = query.indexOf('.');
		if (dotIndex < 0)
		return null;
		return query.substring(0,dotIndex);
	}
}
