package org.androidtown.pdf;

import java.io.File;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * ����Ʈ�� �̿��� PDF ������ ���� ���� �� ���� ����� ���� �� �� �ֽ��ϴ�.
 *  
 *  �� ������Ʈ ���� data ������ �ִ� sample.pdf ������ �ܸ��� SD ī�忡 �־��ٸ�,
 *  /sdcard/sample.pdf ��� �Է»��ڿ� �Է��ϸ� �˴ϴ�.
 * 
 * @author Mike
 */
public class MainActivity extends Activity {

	EditText edit01;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        edit01 = (EditText) findViewById(R.id.edit01);
        
        // ��ư�� ������ �� PDF�� ���� ���� ������ openPDF() �޼ҵ带 ȣ���մϴ�.
        Button openBtn = (Button) findViewById(R.id.openBtn);
        openBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		// �Է��� ���ϸ��� �����ɴϴ�.
        		String filename = edit01.getText().toString();
        		if (filename.length() > 0) {
        			openPDF(filename.trim());
        		} else {
        			Toast.makeText(getApplicationContext(), "PDF ���ϸ��� �Է��ϼ���.", Toast.LENGTH_SHORT).show();
        		}
        	}
        });
        
    }
    
    /**
     * PDF ������ ���� ���� ������ �޼ҵ�
     * 
     * @param contentsPath
     */
    public void openPDF(String contentsPath) {
    	File file = new File(contentsPath);

        if (file.exists()) {
        	// �Է��� ���� ������ Uri ��ü ����
            Uri path = Uri.fromFile(file);
            
            // ����Ʈ ��ü�� ����� setDataAndType() �޼ҵ�� Uri ��ü ����
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
            	// ��Ƽ��Ƽ ����
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
            	Toast.makeText(this, "PDF ������ ���� ���� ��� ���� �����ϴ�.", Toast.LENGTH_SHORT).show();
            }
        } else {
        	Toast.makeText(this, "PDF ������ �����ϴ�.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
