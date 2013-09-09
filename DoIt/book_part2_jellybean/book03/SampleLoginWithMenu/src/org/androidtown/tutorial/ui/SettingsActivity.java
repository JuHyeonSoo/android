package org.androidtown.tutorial.ui;

import java.util.Vector;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

/**
 * ���� ������ ���� ȭ��
 * API���� �����ϴ� ���� ȭ���� �ٸ� ������� ������ ��
 * 
 * @author Mike
 */
public class SettingsActivity extends Activity {

	LinearLayout mainLayout;
	LinearLayout.LayoutParams params1, params2;
	
	Vector<String> titleList = new Vector<String>();
	Vector<EditText> inputList = new Vector<EditText>();
	
	public static final String PREF_NAME = "Settings";
	
	ViewFlipper flipper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ÿ��Ʋ�� ������ �ʵ��� ��
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.settings);
       
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        
        params1 = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);
        params1.leftMargin = 10;
        params1.rightMargin = 10;
        
        params2 = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.FILL_PARENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.rightMargin = 10;
      
        // Ÿ��Ʋ �κ��� ���� ���� �Ϳ� �ؽ�Ʈ ����
        TitleButton titleBtn = (TitleButton) findViewById(R.id.titleBtn);
        titleBtn.setTitleText("ȯ�漳��");
        
        // �ø��� ��ü
        flipper = (ViewFlipper) findViewById(R.id.flipper);
        
        TextButtonItem item01 = new TextButtonItem(this);
        item01.setTitleText("New drug from USA");
        item01.setContentsText("Glaksos Inc. announced new drug.");
        flipper.addView(item01);
        
        TextButtonItem item02 = new TextButtonItem(this);
        item02.setTitleText("Aspirin proved");
        item02.setContentsText("Aspirin is effective to high BP.");
        flipper.addView(item02);
        
        TextButtonItem item03 = new TextButtonItem(this);
        item03.setTitleText("Medicine Conference");
        item03.setContentsText("10th medicine conference in Seoul.");
        flipper.addView(item03);
         
        flipper.setInAnimation(this, R.anim.push_down_in);
        flipper.setOutAnimation(this, R.anim.push_down_out);
        flipper.setFlipInterval(5000);
        
        // ���� ��ư �̺�Ʈ ó��
        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		save();
        		finish();
        	}
        });

        // ��� ��ư �̺�Ʈ ó��
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        	}
        });

        init();
        
    }
    
    /**
     * �ʱ�ȭ
     */
    private void init() {
    	addItem("Host");
    	addItem("Port");
    }
    
    
    protected void onPause() {
    	super.onPause();

    	// �ø���
    	flipper.stopFlipping();
        
    }
    
    protected void onResume() {
    	super.onResume();
    	
    	load();
    	
    	// �ø���
    	flipper.startFlipping();
        
    }
    
    /**
     * ������ �߰�
     * 
     * @param title
     */
    public void addItem(String title) {
        LinearLayout aRow = new LinearLayout(this);
        
        TextView aText = new TextView(this);
        aText.setText(title);
        aText.setTextSize(16);
        aRow.addView(aText, params1);

        EditText aInput = new EditText(this);
        aRow.addView(aInput, params2);
        
        mainLayout.addView(aRow, params2);
        
        titleList.add(title);
        inputList.add(aInput);
    }
    
    /**
     * Preferences �� ����
     */
    public void save() {
    	Toast.makeText(this, "save() ȣ���.", 2000).show();
    	
    	SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        
        for (int i = 0; i < inputList.size(); i++) {
        	String title = titleList.get(i);
        	String itemStr = inputList.get(i).getText().toString().trim();
        	editor.putString(title, itemStr);
        }
        
        editor.commit();
    }
    
    /**
     * Preferences ���� �ε�
     */
    public void load() {
    	Toast.makeText(this, "load() ȣ���.", 2000).show();
    	
    	SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);       
        
    	if (settings != null) {
    		for (int i = 0; i < titleList.size(); i++) {
    			String title = titleList.get(i);
    			String itemStr = settings.getString(title, "");
    			EditText item = inputList.get(i);
    			item.setText(itemStr);
    			item.invalidate();
    			
    		}
    	}
    	
    }
    
}
