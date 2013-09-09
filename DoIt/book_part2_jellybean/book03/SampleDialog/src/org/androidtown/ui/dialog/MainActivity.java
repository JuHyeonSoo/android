package org.androidtown.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �����׸�Ʈ�� ��ȭ���ڸ� ����ϴ� ����� ���� �� �� �ֽ��ϴ�.
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
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				Fragment prev = getFragmentManager().findFragmentByTag("dialog");
				if (prev != null) {
					ft.remove(prev);
				}
				ft.addToBackStack(null);

				// ��ȭ���ڸ� ����� �����ֱ�
				InfoDialogFragment dialog = new InfoDialogFragment();
				dialog.show(ft, "dialog");
			}
		});
		
		// �ݱ� ��ư �̺�Ʈ ����
		Button btnClose = (Button) findViewById(R.id.btnClose);
		btnClose.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /**
     * ���� ������ ��ȭ���� �����׸�Ʈ
     */
    class InfoDialogFragment extends DialogFragment {
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    	View view = inflater.inflate(R.layout.info_dialog, container, false);
	
	    	Button btnShow = (Button) view.findViewById(R.id.btnShow);
	    	btnShow.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "�� ��ư�� ���Ƚ��ϴ�.", Toast.LENGTH_SHORT).show();
					dismiss();
				}
			});
	    	
	    	Button btnClose = (Button) view.findViewById(R.id.btnClose);
	    	btnClose.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "�ƴϿ� ��ư�� ���Ƚ��ϴ�.", Toast.LENGTH_SHORT).show();
					dismiss();
				}
			});
	    	
	    	return view;
    	}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Toast.makeText(getApplicationContext(), "onCreateDialog() called in a Fragment.", Toast.LENGTH_SHORT).show();
			
			return super.onCreateDialog(savedInstanceState);
		}
    	
    }
    
}
