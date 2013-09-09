package org.androidtown.ui.stackview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StackView stk = (StackView)this.findViewById(R.id.stackView1);
        stk.setCameraDistance(10.0f);
        
        ArrayList<StackItem> items = new ArrayList<StackItem>();
        items.add(new StackItem("(1) ��ο�", this.getResources().getDrawable(R.drawable.contact)));
        items.add(new StackItem("(2) ȫ����", this.getResources().getDrawable(R.drawable.contact)));
        items.add(new StackItem("(3) ������", this.getResources().getDrawable(R.drawable.contact)));
        items.add(new StackItem("(4) ������", this.getResources().getDrawable(R.drawable.contact)));
        items.add(new StackItem("(5) ������", this.getResources().getDrawable(R.drawable.contact)));
        
        StackAdapter adapt = new StackAdapter(this, R.layout.stack_item, items);
        
        stk.setAdapter(adapt);
        
    }

    /**
     * ����� Ŭ���� ����
     */
    public class StackAdapter extends ArrayAdapter<StackItem> {
    	 
    	private ArrayList<StackItem> items;
    	private Context ctx;
    	 
    	public StackAdapter(Context context, int resId, ArrayList<StackItem> objects) {
    		super(context, resId, objects);
    	 
    		this.items = objects;
    		this.ctx = context;
    	}

    	public View getView(int position, View convertView, ViewGroup parent) {
    		View v = convertView;
    		if (v == null) {
    			LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    			v = inflater.inflate(R.layout.stack_item, null);
    		}
    	 
    		StackItem m = items.get(position);
    	 
    		if (m != null) {
    			TextView text = (TextView) v.findViewById(R.id.textView1);
    			ImageView img = (ImageView) v.findViewById(R.id.imageView1);
    	 
    			if (text != null) {
    				text.setText(m.itemText);
    				img.setImageDrawable(m.itemPhoto);
    			}
    		}

    		return v;
    	}
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
