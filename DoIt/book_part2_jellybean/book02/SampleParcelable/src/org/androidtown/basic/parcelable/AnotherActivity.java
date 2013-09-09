package org.androidtown.basic.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * ���ο� ��Ƽ��Ƽ
 * 
 * @author Mike
 */
public class AnotherActivity extends Activity {

	/**
	 * �ΰ� �����͸� ���� ������ Ű ��
	 */
	public static final String KEY_SIMPLE_DATA = "data";
	
	/**
	 * �ؽ�Ʈ��
	 */
	TextView txtMsg;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        Button backBtn = (Button) findViewById(R.id.backBtn);
		
		// ��ư�� ������ �� ���� ��Ƽ��Ƽ�� ���ư��ϴ�.
		backBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
        		// ��ü�� ����ϴ�.
        		Intent resultIntent = new Intent();
        		resultIntent.putExtra("name", "mike");

                // ������ �����ϰ� �� ��Ƽ��Ƽ�� �����մϴ�.
        		setResult(RESULT_OK, resultIntent);
                finish();
			}
		});

		// ���޵� ����Ʈ�� ó���մϴ�.
		processIntent();
    }

    /**
     * ���޵� ����Ʈ ó��
     */
    private void processIntent() {
    	// ����Ʈ ���� ���� ��ü�� �����մϴ�.
        Bundle bundle = getIntent().getExtras();
        
        // ���� ��ü ���� SimpleData ��ü�� �����մϴ�.
        SimpleData data = (SimpleData)bundle.getParcelable(KEY_SIMPLE_DATA);

        // �ؽ�Ʈ�信 ���� �����ݴϴ�.
        txtMsg.setText("Parcelable ��ü�� ���޵� ��\nNumber : " + data.getNumber() + "\nMessage : " + data.getMessage());
    }
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
