package pro.android;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.SimpleCursorAdapter;

public class ListDemoActivity extends ListActivity
{
	private SimpleCursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// ù ��° XML�� ����Ʈ ��� ����
		setContentView(R.layout.list);

		// ����Ʈ ���ι��̴� ������
		Cursor c = getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
		
		// ��Ƽ��Ƽ�� Ŀ���� �����ֱ⸦ ��ġ��Ŵ
		startManagingCursor(c);
		String[] cols = new String[]{Phone.DISPLAY_NAME};
		int[] names = new int[]{R.id.row_tv};
		adapter = new SimpleCursorAdapter(this,R.layout.listview,c,cols,names);
		this.setListAdapter(adapter);
	}
}
