package org.androidtown.thread.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * AsyncTask �� �̿��Ͽ� ��׶��� �۾��� �����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	TextView textView01;
	ProgressBar progress;
	BackgroundTask task;
	int value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView01 = (TextView) findViewById(R.id.textView01);
        progress = (ProgressBar) findViewById(R.id.progress);

        // ���� ��ư �̺�Ʈ ó��
        Button executeBtn = (Button) findViewById(R.id.executeBtn);
        executeBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		// ���ο� Task ��ü�� ����� ����
        		task = new BackgroundTask();
        		task.execute(100);
        	}
        });

        // ��� ��ư �̺�Ʈ ó��
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		task.cancel(true);
        	}
        });
    }


    /**
     * ���ο� Task ��ü�� ����
     */
    class BackgroundTask extends AsyncTask<Integer , Integer , Integer> {
    	protected void onPreExecute() {
    		value = 0;
    		progress.setProgress(value);
    	}

    	protected Integer doInBackground(Integer ... values) {
    		while (isCancelled() == false) {
    			value++;
    			if (value >= 100) {
    				break;
    			} else {
    				publishProgress(value);
    			}

    			try {
    				Thread.sleep(100);
    			} catch (InterruptedException ex) {}
    		}

    		return value;
    	}

    	protected void onProgressUpdate(Integer ... values) {
    		progress.setProgress(values[0].intValue());
    		textView01.setText("Current Value : " + values[0].toString());
    	}

    	protected void onPostExecute(Integer result) {
    		progress.setProgress(0);
    		textView01.setText("Finished.");
    	}

    	protected void onCancelled() {
    		progress.setProgress(0);
    		textView01.setText("Cancelled.");
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
