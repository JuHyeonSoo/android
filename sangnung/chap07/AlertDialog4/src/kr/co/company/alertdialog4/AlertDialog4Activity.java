package kr.co.company.alertdialog4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlertDialog4Activity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button b = (Button) findViewById(R.id.Button01);
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ProgressDialog dialog = 	
					ProgressDialog.show(AlertDialog4Activity.this, "", "�ε� ���Դϴ�. ��ٷ��ּ���.", true, true);
				// �۾��� �Ѵ�. 
				//dialog.dismiss();  �۾��� ������ dismiss()�� ȣ��
			}
		});
	}
}
