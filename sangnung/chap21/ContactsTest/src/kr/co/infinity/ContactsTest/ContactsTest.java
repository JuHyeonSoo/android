package kr.co.infinity.ContactsTest;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactsTest extends Activity {
    private ListView mContactList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);

        mContactList = (ListView) findViewById(R.id.contactList);
        populateContactList();
    }

    /**
     * �ּҷ��� ���� ���õ� �������� ä���. 
     */
    private void populateContactList() {
        // ����͸� �����Ѵ�. 
        Cursor cursor = getContacts();
        String[] fields = new String[] {
                ContactsContract.Data.DISPLAY_NAME
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.entry, cursor,
                fields, new int[] {R.id.contactEntryText});
        mContactList.setAdapter(adapter);
    }

    /**
     * ���� ���õ� ������ �ּҷ� ����Ʈ�� ���Ѵ�. 
     */
    private Cursor getContacts()
    {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
        };
        return managedQuery(uri, projection, null, null, null);
    }
}
