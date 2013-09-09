package kr.co.company.optionmenu1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuItem item1 = menu.add(0, 1, 0, "���ο� ����");
		item1.setIcon(R.drawable.new_game);
		item1.setAlphabeticShortcut('a');

		MenuItem item2 = menu.add(0, 2, 0, "���");
		item2.setIcon(R.drawable.quit);
		item2.setAlphabeticShortcut('b');

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this, "���ο� ���� ����", Toast.LENGTH_SHORT).show();
			return true;
		case 2:
			Toast.makeText(this, "��� ����", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}