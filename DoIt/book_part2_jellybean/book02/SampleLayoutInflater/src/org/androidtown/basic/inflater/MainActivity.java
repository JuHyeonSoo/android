package org.androidtown.basic.inflater;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * ���̾ƿ� ���÷����͸� �̿��� ���̾ƿ��� �Ϻθ� �������� �ε��ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ��ư�� ������ �� ���̾ƿ��� �������� �ε��մϴ�.
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ���÷��̼����� contentsLayout �ȿ� ���̾ƿ��� �����մϴ�.
				inflateLayout();
			}
		});
        
    }

    /**
     * button.xml �� ���ǵ� ���̾ƿ��� ���� ��Ƽ��Ƽ�� ���̾ƿ� �Ϻη� �߰��ϴ� �޼ҵ� ����
     */
    private void inflateLayout() {
        // XML ���̾ƿ��� ���ǵ� contentsLayout ��ü ����
        LinearLayout contentsLayout = (LinearLayout) findViewById(R.id.contentsLayout);

        // ���÷��̼� ����
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.button, contentsLayout, true);

        // ���� �߰��� ���̾ƿ� �ȿ� ����ִ� ��ư ��ü ����
		Button btnSelect = (Button) findViewById(R.id.btnSelect);
		final CheckBox allDay = (CheckBox) findViewById(R.id.allDay);

		btnSelect.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (allDay.isChecked()) {
					allDay.setChecked(false);
				} else {
					allDay.setChecked(true);
				}
			}
		});

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
