package kr.co.company.ContactsTest1;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContactsTest1 extends Activity {
	private TextView text;
	private StringBuilder display = new StringBuilder();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		text = (TextView) findViewById(R.id.text);
		populateContactList();
	}

	/**
	 * ����ó�� ��ȭ��ȣ�� ȭ���� ä���.
	 */
	private void populateContactList() {
		// ����ó�� �����Ѵ�.
		Cursor cursor = getContacts();

		// ����ó�� �ϳ��� ó���Ѵ�.
		while (cursor.moveToNext()) {
			display.append(cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			display.append("\n");
			String id = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));

			// ����ó�� ��ȭ��ȣ �����͸� ���Ѵ�.
			Cursor cursor1 = getData(id);
			while (cursor1.moveToNext()) {
				display.append(cursor1.getString(cursor1
						.getColumnIndex(Phone.NUMBER)));
				display.append("\n");
			}
			display.append("\n\n");
		}
		text.setText(display);
	}

	/**
	 * ��ü ����ó ����Ʈ�� ���Ѵ�.
	 */
	private Cursor getContacts() {
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME };
		return managedQuery(uri, projection, null, null, null);
	}

	/**
	 * ���̵� contactId�� ����ó�� ��ȭ��ȣ���� ���Ѵ�.
	 */
	private Cursor getData(String contactId) {
		return getContentResolver()
				.query(Data.CONTENT_URI,
						new String[] { Data._ID, Phone.NUMBER, Phone.TYPE,
								Phone.LABEL },
						Data.CONTACT_ID + "=?" + " AND " + Data.MIMETYPE + "='"
								+ Phone.CONTENT_ITEM_TYPE + "'",
						new String[] { contactId }, null);
	}
}
