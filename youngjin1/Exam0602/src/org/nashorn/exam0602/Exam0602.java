package org.nashorn.exam0602;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Exam0602 extends Activity {
	
	private String[] nameList;
	private String[] phoneList;
	
	private ArrayList<String> list;
	private ArrayAdapter<String> arrayAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        list = new ArrayList<String>();
		
		new loadContactsData().execute("");
    }
    
	public void getContactsData()
	{
		//�ּҷ� ���� ��ȸ
		Cursor c = this.getContentResolver().query(
        	Contacts.CONTENT_URI, new String[] { BaseColumns._ID },
        	null, null, null);
        c.moveToFirst();
        
        //�ּҷϿ� ��ϵ� ����ó�� 1�� �̻��̸�
        if (c.getCount() > 0)
        {
            nameList = new String[c.getCount()];
            phoneList = new String[c.getCount()];
           
            //���̵�� �� ����� �̸�, ��ȭ��ȣ�� ��ȸ 
            for ( int i = 0 ; i < c.getCount() ; i++ ) 
            {
            	try
            	{
            		//���̵� �̿��Ͽ� ���� ������ �ٽ� ��ȸ
                    String _id = c.getString(c.getColumnIndex(BaseColumns._ID));
                    Uri contactUri = ContentUris.withAppendedId(Contacts.CONTENT_URI, Integer.parseInt(_id));
                    Uri dataUri = Uri.withAppendedPath(contactUri, Data.CONTENT_DIRECTORY);
                    Cursor c2 = this.getContentResolver().query(
                        dataUri,
                        new String[] {
                            BaseColumns._ID, Data.MIMETYPE, Data.DATA1, Data.DATA2, Data.DATA3,
                            Data.DATA4, Data.DATA5, Data.DATA6, Data.DATA7, Data.DATA8,
                            Data.DATA9, Data.DATA10, Data.DATA11, Data.DATA12, Data.DATA13, 
                            Data.DATA14, Data.DATA15 },
                        null, null, null );    
                    
                    try 
                    {
                        while ( c2.moveToNext() ) 
                        {    
                    		if ( !c2.isNull(c.getColumnIndex(BaseColumns._ID)) ) 
                            {
                                //MINE Ÿ������ � �������� �� �� �ִ�.
                                String mimeType = c2.getString(c2.getColumnIndex(Data.MIMETYPE));
                                //�̸��� ���
                                if ( mimeType.equals(StructuredName.CONTENT_ITEM_TYPE) ) {
                                	nameList[i] = c2.getString(c2.getColumnIndex(StructuredName.DISPLAY_NAME));
                                //��ȭ��ȣ�� ���
                                } else if ( mimeType.equals(Phone.CONTENT_ITEM_TYPE) ) {
                                    phoneList[i] = c2.getString(c2.getColumnIndex(Phone.NUMBER));
                                }
                            }
                        }
                    } finally {
                        c2.close();
                    }
                    
                    //ListView�� ǥ�õ� ���� �߰�
                	list.add(nameList[i]);
                	
                	//���� �׸����� �Ѿ��.
                	c.moveToNext();
            	} catch (Exception e)
            	{
            		//Toast.makeText(adddial, e.toString(), Toast.LENGTH_LONG).show();   
            	}
            }
        }
	}
        
    private class loadContactsData extends AsyncTask<String, Void, Void> {   
	  
        private String Content;   
        private String Error = null;   
        private ProgressDialog Dialog = new ProgressDialog(Exam0602.this);   
           
        protected void onPreExecute() {   
            Dialog.setMessage("�ּҷ� ������ �ε� ���Դϴ�...");   
            Dialog.show();   
        }   
  
        protected Void doInBackground(String... urls) {   
            
        	getContactsData();
               
            return null;   
        }   
           
        protected void onPostExecute(Void unused) {   
            Dialog.dismiss();   

    		ListView listView = (ListView)findViewById(R.id.list);
    		//listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    		
    		arrayAdapter = new ArrayAdapter<String>(getBaseContext(),
    			android.R.layout.simple_list_item_1, list);

      		listView.setAdapter(arrayAdapter);	
     		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    			@Override
    			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    					long arg3) {
    				// TODO Auto-generated method stub
    			}
    		});
     		
     		EditText findText = (EditText)findViewById(R.id.input);
     		findText.addTextChangedListener(new TextWatcher(){
     		    public void afterTextChanged(Editable s) {
     		    	findName(s.toString());
     		    }

     		    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
     		    }

     		    public void onTextChanged(CharSequence s, int start, int before, int count) {
     		    }
     		});
            
            if (Error != null) {   
                Toast.makeText(Exam0602.this, Error, Toast.LENGTH_LONG).show();   
            } else {   
                //Toast.makeText(parent, "Source: " + Content, Toast.LENGTH_LONG).show();   
            }   
        }   
    }

    final static char[] chosungWord   = { 
    	0x3131, 0x3132, 0x3134, 0x3137, 0x3138, //�� �� �� �� ��      
    	0x3139, 0x3141, 0x3142, 0x3143, 0x3145, //�� �� �� �� ��
    	0x3146, 0x3147, 0x3148, 0x3149, 0x314a, //�� �� �� �� ��
    	0x314b, 0x314c, 0x314d, 0x314e 			//�� �� �� ��
    };
    
    public String hangulToOnlyChosung(String s) 
    { 
		int chosungNum, tempNum;
		String resultString = "";
		
		for (int i = 0; i < s.length(); i++) 
		{
			char ch = s.charAt(i);
			if (ch != ' ')
			{
				if (ch >= 0xAC00 && ch <= 0xD7A3) 
				{
					tempNum = ch - 0xAC00;
					chosungNum = tempNum / (21 * 28);
					
					resultString += chosungWord[chosungNum];
				} 
				else 
				{
					resultString += ch;
				}
			}
		}
		return resultString;
    }
    
    public void findName(String findString)
	{
		if (findString.length() > 0)
		{
			String onlyChosungString = hangulToOnlyChosung(findString);

			list.clear();
			for (int i = 0; i < nameList.length; i++)
			{
				String onlyChosungNameString = hangulToOnlyChosung(nameList[i]);
				if (onlyChosungNameString.matches(onlyChosungString+".*"))
				{
					list.add(nameList[i]);
				}
			}
			arrayAdapter.notifyDataSetChanged();
		}
		else
		{
			list.clear();
			for (int i = 0; i < nameList.length; i++)
			{
				list.add(nameList[i]);
			}
			arrayAdapter.notifyDataSetChanged();
		}
	}
}