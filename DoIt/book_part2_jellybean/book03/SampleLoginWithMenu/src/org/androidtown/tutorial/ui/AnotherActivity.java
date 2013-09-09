package org.androidtown.tutorial.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * �Ǵٸ� ��Ƽ��Ƽ
 * 
 * @author Mike
 */
public class AnotherActivity extends Activity {
    // ����
	int[] colors = {0xff000000, 0xff440000, 0xff884400, 0xffaa8844, 0xffffaa88,
					0xffffffaa, 0xffffffff, 0xffaaffff, 0xff88aaff, 0xff4488aa};
	// ��ư ID
	int[] buttonIds = {R.id.Button01, R.id.Button02, R.id.Button03, R.id.Button04, R.id.Button05,
			R.id.Button06, R.id.Button07, R.id.Button08, R.id.Button09, R.id.Button10};
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);
        
        // ��ư �̺�Ʈ ó��
        Button btn = null;
        ColorOnClickListener listener = null;
        
        for (int i = 0; i < buttonIds.length; i++) {
	        btn = (Button) findViewById(buttonIds[i]);
	        listener = new ColorOnClickListener(i);
	        btn.setBackgroundColor(colors[i]);
	        
	        // �����ʸ� ������ ������ �� �� ��ü�� ������
	        btn.setOnClickListener(listener);
        }
        
    }
    

    /**
     * �̺�Ʈ ó���� ���� ������ ������ ��ü
     */
    class ColorOnClickListener implements OnClickListener {
    	 
    	int index;
    	
    	public ColorOnClickListener(int idx) {
    		index = idx;
    	}
    	
    	public void onClick(View v) {
    		// ���� ��Ƽ��Ƽ�� ������ ������
    		Intent resultIntent = new Intent();        		
            resultIntent.putExtra("color", colors[index]);

            // ���� ���� �� �ݱ�
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
    	}
    	
    }
    
}
