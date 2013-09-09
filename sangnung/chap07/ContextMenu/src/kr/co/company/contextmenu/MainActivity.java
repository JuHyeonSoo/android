package kr.co.company.contextmenu;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.*;

public class MainActivity extends Activity {
	TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.TextView01);
		registerForContextMenu(text);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("���ؽ�Ʈ �޴�");
		menu.add(0, 1, 0, "����: RED");
		menu.add(0, 2, 0, "����: GREEN");
		menu.add(0, 3, 0, "����: BLUE");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			text.setBackgroundColor(Color.RED);
			return true;
		case 2:
			text.setBackgroundColor(Color.GREEN);
			return true;
		case 3:
			text.setBackgroundColor(Color.BLUE);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

} 