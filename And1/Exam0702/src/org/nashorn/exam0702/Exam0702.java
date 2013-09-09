package org.nashorn.exam0702;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import android.net.*;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class Exam0702 extends Activity {
	
	Exam0702 curActivity = this;
	
	private String selectPackageName = "";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        PackageManager packagemanager = getPackageManager(); 
        final List<PackageInfo> appList = packagemanager.getInstalledPackages(0);
        ArrayAdapter<PackageInfo> adaptedAppList = new ArrayAdapter<PackageInfo>(this, android.R.layout.simple_list_item_single_choice, appList);
        ListView listView = (ListView)findViewById(R.id.application_list);
        listView.setAdapter(adaptedAppList);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				final PackageInfo runApp = appList.get(arg2);
				
				selectPackageName = runApp.packageName;
			}
		});
    }
    
    public void fileCopy(String src, String dest)
    {
    	try
    	{
	    	InputStream myInput = new FileInputStream(src);
	    	String outFileName = dest;
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	    	byte[] buffer = new byte[1024];
	    	
	    	int total_length = 0;
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    		total_length+=length;
	    	}
	    	total_length+=length;

	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
    	}
    	catch(Exception e)
    	{
    		Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
        // Inflate the currently selected menu XML resource.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        
        return true;
    }
	
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
	   		case R.id.backup:
	   		{
	   			if (selectPackageName.length() > 0)
   				{
					//������ ��Ű�� ���� ����
	   				String srcFile = "/data/app/"+selectPackageName+".apk";
	   				String destFile = "/sdcard/"+selectPackageName+".apk";
	   				
		   			try
		   			{
		   				File file = new File(srcFile);
        			    if (file.isFile()) {
        			    	
        			    	fileCopy(srcFile, destFile);
  		   			       
  		   			       Toast.makeText(this, selectPackageName+"�� ����߽��ϴ�.", Toast.LENGTH_SHORT).show();
        			    }
        			    else
        			    	Toast.makeText(this, "�ش� ���ø����̼��� ����� �� �����ϴ�.", Toast.LENGTH_SHORT).show();
		   			}
		   			catch(Exception e)
		   			{
		   				Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
		   			}
   				}
   				else
   					Toast.makeText(this, "����� ���ø����̼��� �����ϼ���.", Toast.LENGTH_SHORT).show();
	   		}
   			return true;
   			
	   		case R.id.uninstall:
   			{
   				if (selectPackageName.length() > 0)
   				{
	   				AlertDialog.Builder alertDialog = new AlertDialog.Builder(curActivity);
					alertDialog.setTitle("���ø����̼� ����");
					alertDialog.setMessage("������ ���ø����̼�("+selectPackageName+")�� �����Ͻðڽ��ϱ�?");
					alertDialog.setPositiveButton("��", 
		   				new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								Uri uri = Uri.fromParts("package", selectPackageName, null);
						        Intent it = new Intent(Intent.ACTION_DELETE, uri);
						        startActivity(it); 
							}
						});
					alertDialog.setNegativeButton("�ƴϿ�", 
		   				new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
							}
						});
					alertDialog.show();   		
   				}
   			}
   			return true;
	   			

    	}
	    
    	return super.onOptionsItemSelected(item);
    }   	
}