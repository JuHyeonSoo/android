package org.androidtown.http.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * HTTP Client ���̺귯���� �̿��� ��û�ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	EditText input01;
	TextView txtMsg;

	public static String defaultUrl = "http://m.naver.com";

	Handler handler = new Handler();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        input01 = (EditText) findViewById(R.id.input01);
        input01.setText(defaultUrl);

        txtMsg = (TextView) findViewById(R.id.txtMsg);

        // ��ư �̺�Ʈ ó��
        Button requestBtn = (Button) findViewById(R.id.requestBtn);
        requestBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		String urlStr = input01.getText().toString();
        		
        		ConnectThread thread = new ConnectThread(urlStr);
				thread.start();
        		
        	}
        });
        
    }

    
    /**
     * ���� ������ ������ ����
     */
    class ConnectThread extends Thread {
    	String urlStr;
    	
    	public ConnectThread(String inStr) {
    		urlStr = inStr;
    	}
    	
    	public void run() {

    		try {
    			final String output = request(urlStr);
    			handler.post(new Runnable() {
					public void run() {
						txtMsg.setText(output);
					}
    			});
    			
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}

    	}
    	

        private String request(String urlStr) {
        	StringBuilder output = new StringBuilder();
        	try {
        		HttpClient client = new DefaultHttpClient();
        		HttpPost httppost = new HttpPost(urlStr);

        		List<NameValuePair> fields = new ArrayList<NameValuePair>(1);
        		fields.add(new BasicNameValuePair("data", "test"));
    			httppost.setEntity(new UrlEncodedFormEntity(fields));

    			Log.d("SampleHTTPClient", "\nRequest using HttpClient ...");
    			HttpResponse response = client.execute(httppost);
    			Log.d("SampleHTTPClient", "\nResponse from HttpClient ...");

    			InputStream instream = response.getEntity().getContent();
    			BufferedReader reader = new BufferedReader(new InputStreamReader(instream)) ;
    			String line = null;
    			while(true) {
    				line = reader.readLine();
    				if (line == null) {
    					break;
    				}
    				output.append(line + "\n");
    			}
    			
    			reader.close();
        	} catch(Exception ex) {
        		Log.e("SampleHTTPClient", "Exception in processing response.", ex);
        	}

        	return output.toString();
        }

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
