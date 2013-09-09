package org.androidtown.intent.call;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ����Ʈ�� ��ȭ�ɱ� ȭ���� �����ִ� ����� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	TextView text01;
	EditText edit01;
	Button btnCall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		text01 = (TextView) findViewById(R.id.text01);
		edit01 = (EditText) findViewById(R.id.edit01);

		btnCall = (Button) findViewById(R.id.btnCall);

		// ��ȭ�ɱ� ��ư�� ������ �� ó���ϴ� �����ʸ� ��ü�� ����
		btnCall.setOnClickListener(new ClickHandler());

	}

    /**
     * Ŭ�� �̺�Ʈ�� ó���ϴ� Ŭ������ ������ ����
     */
	private class ClickHandler implements OnClickListener {
		public void onClick(View v) {
			try {
				// �Է»��ڿ� �Է��� ��ȭ��ȣ�� ������
				String myData = edit01.getText().toString();
				
				// ����Ʈ�� ����� �̰��� �̿��� ��Ƽ��Ƽ�� �����
				Intent myActivity2 = new Intent(Intent.ACTION_DIAL, Uri.parse(myData));
				startActivity(myActivity2);

			} catch (Exception ex) {
				Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
