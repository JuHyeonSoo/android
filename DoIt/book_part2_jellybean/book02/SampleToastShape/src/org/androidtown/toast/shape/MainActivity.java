package org.androidtown.toast.shape;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ��ư�� ������ �� �佺Ʈ�� ����� �ٲپ� �����ݴϴ�.
        Button showBtn = (Button) findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// �佺Ʈ�� ����������ϰ� �ִ� ���̾ƿ��� ���÷��̼��մϴ�.
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(
						R.layout.toastborder,
						(ViewGroup) findViewById(R.id.toast_layout_root));
				
				// ���̾ƿ��� �ؽ�Ʈ�信 ������ ���ڿ��� �����մϴ�.
				TextView text = (TextView) layout.findViewById(R.id.text);
				text.setText("Hello My Android!");
				
				// �佺Ʈ ��ü�� ����ϴ�.
				Toast toast = new Toast(getApplicationContext());
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
				
				// �佺Ʈ�� �並 �����մϴ�. �� �䰡 �佺Ʈ�� �������ϴ�.
				toast.setView(layout);
				toast.show();
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
