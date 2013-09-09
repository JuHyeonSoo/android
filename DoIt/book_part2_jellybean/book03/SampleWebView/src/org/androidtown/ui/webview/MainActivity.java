package org.androidtown.ui.webview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * ���並 ȭ��ȿ� �ְ� �۰� �� ���̿� ��ȣ ȣ���ϴ� ����� �˾ƺ� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * �α׸� ���� �±�
	 */
	private static final String TAG = "MainActivity";

	/**
	 * ���� ��ü
	 */
	private WebView webView;
	
	/**
	 * ������Ʈ �ε��� ���� ��ư
	 */
	private Button loadBtn;

	/**
	 * �ڵ鷯 ��ü
	 */
	private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ���� ��ü ����
        webView = (WebView) findViewById(R.id.webview);

        // ���� ���� ����
        WebSettings webSettings = webView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        webView.setWebChromeClient(new WebBrowserClient());
        webView.addJavascriptInterface(new JavaScriptMethods(), "sample");
        
        // assets ������ �ִ� ���� ������ �ε�
        webView.loadUrl("file:///android_asset/sample.html");

        final EditText urlInput = (EditText) findViewById(R.id.urlInput);
        
        // ��ư �̺�Ʈ ó��
        loadBtn = (Button) findViewById(R.id.loadBtn);
        loadBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		// �Է��� URL�� ������ �ε�
        		webView.loadUrl(urlInput.getText().toString());
        	}
        });

    }

    /**
     * �ڹٽ�ũ��Ʈ �Լ��� ȣ���ϱ� ���� Ŭ���� ����
     */
    final class JavaScriptMethods {

    	JavaScriptMethods() {
        
    	}

        public void clickOnFace() {
            mHandler.post(new Runnable() {
                public void run() {
                	// ��ư�� �ؽ�Ʈ ����
                	loadBtn.setText("Ŭ���Ŀ���");
                	// �ڹٽ�ũ��Ʈ �Լ� ȣ��
                	webView.loadUrl("javascript:changeFace()");
                }
            });

        }
    }

    final class WebBrowserClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(TAG, message);
            result.confirm();

            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
