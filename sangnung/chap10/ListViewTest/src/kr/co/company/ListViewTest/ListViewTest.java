package kr.co.company.ListViewTest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String[] list = { "���", "��", "����", "����", "����", "���ξ���", "����", "�ٳ���",
				"Ű��", "��", "����" };

		ArrayAdapter<String> adapter;
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);

		ListView listview = (ListView) findViewById(R.id.ListView01);
		listview.setAdapter(adapter);
	}
}