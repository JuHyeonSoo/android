package org.androidtown.ui.progress;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * ���α׷����ٸ� ����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	/**
	 * ���α׷����ٸ� ������ �� ����� ���
	 */
	public static final int PROGRESS_DIALOG = 1001;
	
	/**
	 * ���α׷��� ��ȭ���� ��ü
	 */
	ProgressDialog progressDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ���̾ƿ��� �߰��� ���α׷����� ��ü ����
        ProgressBar proBar = (ProgressBar) findViewById(R.id.progressBar01);
        proBar.setIndeterminate(false);
        proBar.setMax(100);
        proBar.setProgress(80);

        // ������ �̹��� ����
        ImageView icon = (ImageView) findViewById(R.id.iconItem);
        Resources res = getResources();
        Drawable drawable = (Drawable) res.getDrawable(R.drawable.apple);
        icon.setImageDrawable(drawable);

        // �ؽ�Ʈ ����
        TextView nameText = (TextView) findViewById(R.id.dataItem01);
        nameText.setText("���");
		
        // �ؽ�Ʈ ����
        TextView progressText = (TextView) findViewById(R.id.dataItem02);
		progressText.setText("80%");
		
		// �����ֱ� ��ư �̺�Ʈ ����
		Button btnShow = (Button) findViewById(R.id.btnShow);
		btnShow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				showDialog(PROGRESS_DIALOG);
			}
		});
		
		// �ݱ� ��ư �̺�Ʈ ����
		Button btnClose = (Button) findViewById(R.id.btnClose);
		btnClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
			}
		});
        
    }

    /**
     * ��ȭ���ڸ� ���� �� �ֵ��� �ڵ����� ȣ��Ǵ� �޼ҵ�
     */
    public Dialog onCreateDialog(int id) {
    	switch (id) {
	    	case (PROGRESS_DIALOG):
	    		progressDialog = new ProgressDialog(this);
	    		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    		progressDialog.setMessage("�����͸� Ȯ���ϴ� ���Դϴ�.");
	    		
	    		return progressDialog;
    	}
    	
    	return null;
    }
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
