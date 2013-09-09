package com.ai.android.livefolders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.LiveFolders;
//import android.provider.Contacts.People; // �� API�� 2.0 �������� ���ȭ��
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Contacts;
import android.util.Log;

public class MyContactsProvider extends ContentProvider {

    public static final String AUTHORITY = "com.ai.livefolders.contacts";

    // ���̺� ���� ���� �޼���� ���޵Ǵ� Uri
    public static final Uri CONTACTS_URI = Uri.parse("content://" + 
            AUTHORITY + "/contacts" );

    // �� URI�� ����
    private static final int TYPE_MY_URI = 0;
    private static final UriMatcher URI_MATCHER;
    static{
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH); 
        URI_MATCHER.addURI(AUTHORITY, "contacts", TYPE_MY_URI);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public int bulkInsert(Uri arg0, ContentValues[] values) {
        return 0; // ������ ���� ����
    }
    // ���̺� ������ �ʿ��� ����
    // ������ ���̺� ������ ��Ʈ��Ʈ��.
    private static final String[] CURSOR_COLUMNS = new String[]
    {
        BaseColumns._ID,
        LiveFolders.NAME,
        LiveFolders.DESCRIPTION, 
        LiveFolders.INTENT,
        LiveFolders.ICON_PACKAGE, 
        LiveFolders.ICON_RESOURCE
    };

    // ���� ���� ��� ���� �޽����� ǥ���ϴ� ��ſ�
    // ���� ���̺� ���� ������ �������� �˸�
    private static final String[] CURSOR_ERROR_COLUMNS = new String[]
    {
        BaseColumns._ID, 
        LiveFolders.NAME,
        LiveFolders.DESCRIPTION
    };


    // ���� �޽��� ��
    private static final Object[] ERROR_MESSAGE_ROW =
    new Object[]
    {
        -1,                                 // ID
        "����ó�� �����ϴ�",                  // �̸�
        "����ó �����ͺ��̽��� Ȯ���ϼ���"    // ����
    };

    // ����� ���� Ŀ��
    private static MatrixCursor sErrorCursor = new   
            MatrixCursor(CURSOR_ERROR_COLUMNS);
    static
    {
        sErrorCursor.addRow(ERROR_MESSAGE_ROW);
    }

    // ����ó �����ͺ��̽����� ������ ����
    private static final String[] CONTACTS_COLUMN_NAMES = new String[]
    {
        Data._ID,
        Data.DISPLAY_NAME, 
        Data.TIMES_CONTACTED,
        Data.STARRED
    };

    public Cursor query(Uri uri, String[] projection, String selection, 
            String[] selectionArgs, String sortOrder)
    {
        // URI�� �˾Ƴ��� ��ġ���� ������ ������ ��ȯ
        int type = URI_MATCHER.match(uri);
        if(type == UriMatcher.NO_MATCH)
        {
            return sErrorCursor;
        }

        Log.i("ss", "���ǰ� ȣ��Ǿ����ϴ�");

        try
        {
            MatrixCursor mc = loadNewData(this);
            mc.setNotificationUri(getContext().getContentResolver(),
                    Uri.parse("content://contacts/people/"));
            MyCursor wmc = new MyCursor(mc,this);
            return wmc;
        }
        catch (Throwable e)
        {
            return sErrorCursor;
        }
    }

    public static MatrixCursor loadNewData(ContentProvider cp)
    {
        MatrixCursor mc = new MatrixCursor(CURSOR_COLUMNS); 
        Cursor allContacts = null;
        try
        {
            allContacts = cp.getContext().getContentResolver().query(
            	Contacts.CONTENT_URI, 
                CONTACTS_COLUMN_NAMES,
                null, // �� ����
                null,
                Contacts.DISPLAY_NAME); // ���� ����

            while(allContacts.moveToNext())
            {
                String timesContacted = "���� Ƚ��: "+allContacts.getInt(2);

                Object[] rowObject = new Object[]
                {
                    allContacts.getLong(0),                  // ID
                    allContacts.getString(1),                 // �̸�
                    timesContacted,                        // ����
                    Uri.parse("content://contacts/people/"
                            +allContacts.getLong(0)),        // ����Ʈ URI
                    cp.getContext().getPackageName(),       // ��Ű��
                    R.drawable.icon                        // ������
                };
                mc.addRow(rowObject);
            }
            return mc;
        }
        finally
        {
            allContacts.close();
        }
    }

    @Override
    public String getType(Uri uri)
    {
        // �� ���� ���ι��̴��� ������� �ϴ�
        // ���޹��� URI�� ���� Ÿ���� ��ȯ
        // �Ϲ��� ���´� ������ ����.
        // "vnd.android.cursor.dir/vnd.google.note"
        return Data.CONTENT_TYPE;
    }

    public Uri insert(Uri uri, ContentValues initialValues) {
        throw new UnsupportedOperationException(
                "�̰��� ���ۿ� �Ұ��ϹǷ� ������ �� �����ϴ�");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException(
                "�̰��� ���ۿ� �Ұ��ϹǷ� ������ �� �����ϴ�");
    }

    public int  update(Uri uri, ContentValues values, 
            String selection, String[] selectionArgs)
    {
        throw new UnsupportedOperationException(
            "�̰��� ���ۿ� �Ұ��ϹǷ� ������Ʈ�� �� �����ϴ�");
    }
}