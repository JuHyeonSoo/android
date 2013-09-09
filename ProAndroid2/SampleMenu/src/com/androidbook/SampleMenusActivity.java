package com.androidbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
//import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

public class SampleMenusActivity extends Activity {

	// ������ onCreateOptions���� �ʱ�ȭ
	Menu myMenu = null; 

	@Override 
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.main); 
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) 
	{
    // �θ� ȣ���ؼ� �ý��� ������ ��� �޴��� �μӽ�Ŵ
	super.onCreateOptionsMenu(menu); 
	
	this.myMenu = menu; 
	
	// �Ϲ� �޴� �׸� �߰�
	addRegularMenuItems(menu); 
	
	// 2�� �޴� �׸� �߰�
	add5SecondaryMenuItems(menu); 

	// ���� �޴� �׸� �߰�
	addSubMenu(menu);
	// �޴��� ǥ���Ϸ��� return�� ��ȯ�ؾ� ��
	// false�� ��ȯ�Ǹ� �޴��� ǥ�õ��� ����
	return true; 
	}

	private void addRegularMenuItems(Menu menu)
	{
		int base=Menu.FIRST;  // ���� 1�̴�

	    menu.add(base,base,base,"�߰�"); 
	    menu.add(base,base+1,base+1,"�׸� 2"); 
	    menu.add(base,base+2,base+2,"����"); 

	    menu.add(base,base+3,base+3,"2�� �޴� �����"); 
	    menu.add(base,base+4,base+4,"2�� �޴� ���̱�"); 

	    menu.add(base,base+5,base+5,"2�� �޴� ����ȭ"); 
	    menu.add(base,base+6,base+6,"2�� �޴� �񰡿�ȭ"); 

	    menu.add(base,base+7,base+7,"2�� �޴� üũ"); 
	    menu.add(base,base+8,base+8,"2�� �޴� üũ ����"); 
	}

	private void add5SecondaryMenuItems(Menu menu)
	{
		// 2�� �׸���� �ٸ� ��� �޴� �׸�� �Ȱ��� ���δ�.
		int base=Menu.CATEGORY_SECONDARY; 
	
		menu.add(base,base+1,base+1,"2��. �׸� 1"); 
		menu.add(base,base+2,base+2,"2��. �׸� 2"); 
		menu.add(base,base+3,base+3,"2��. �׸� 3"); 
		menu.add(base,base+3,base+3,"2��. �׸� 4"); 
		menu.add(base,base+4,base+4,"2��. �׸� 5"); 
	}

	@Override 
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			appendText(" $nhello"); 
		}
		else if (item.getItemId() == 2) {
			appendText("\nitem2");
		}
		else if (item.getItemId() == 3) {
			emptyText();
		}
		else if (item.getItemId() == 4) {
			// 2�� �޴� �׸� �����
			this.appendMenuItemText(item); 
			this.myMenu.setGroupVisible(Menu.CATEGORY_SECONDARY,false); 
		}
		else if (item.getItemId() == 5) {
			// 2�� �޴� �׸� ���̱�
			this.appendMenuItemText(item); 
			this.myMenu.setGroupVisible(Menu.CATEGORY_SECONDARY,true); 
		}
		else if (item.getItemId() == 6) {
			// 2�� �޴� �׸� ����ȭ
			this.appendMenuItemText(item); 
			this.myMenu.setGroupEnabled(Menu.CATEGORY_SECONDARY,true); 
		}
		else if (item.getItemId() == 7) {
			// 2�� �޴� �׸� �񰡿�ȭ
			this.appendMenuItemText(item); 
			this.myMenu.setGroupEnabled(Menu.CATEGORY_SECONDARY,false); 
		}
		else if (item.getItemId() == 8) {
			// 2�� �޴� �׸� üũ
			this.appendMenuItemText(item); 
			myMenu.setGroupCheckable(Menu.CATEGORY_SECONDARY,true,false); 
		}
		else if (item.getItemId() == 9) {
			// 2�� �޴� �׸� üũ ����
			this.appendMenuItemText(item); 
			myMenu.setGroupCheckable(Menu.CATEGORY_SECONDARY,false,false); 
		}
		else {
			this.appendMenuItemText(item); 
		}
		// �޴� �׸� ó���� �Ϸ�Ǹ� �ݵ�� true�� ��ȯ�ؾ� �Ѵ�.
		return true;
	}

	// ������ �ؽ�Ʈ ���ڿ��� TextView�� �Ҵ�
	private void appendText(String text) {
		TextView tv = (TextView)this.findViewById(R.id.textViewId); 
		tv.setText(tv.getText() + text); 
	}

	// ���� ������ �޴� �׸��� TextView�� �Ҵ�
	private void appendMenuItemText(MenuItem menuItem) {
		String title = menuItem.getTitle().toString(); 
		TextView tv = (TextView)this.findViewById(R.id.textViewId); 
		tv.setText(tv.getText() + " _n" + title); 
	}

	// TextView�� ������ ���
	private void emptyText() {
		TextView tv = (TextView)this.findViewById(R.id.textViewId); 
		tv.setText(""); 
	}

	private void addSubMenu(Menu menu) 
	{
	  // 2�� �׸���� �ٸ� �޴���� �Ȱ��� ǥ�õȴ�.
	  int base=Menu.FIRST + 100; 
	  SubMenu sm = menu.addSubMenu(base,base+1,Menu.NONE,"���� �޴�"); 
	  sm.add(base,base+2,base+2, "���� �׸� 1"); 
	  sm.add(base,base+3,base+3, "���� �׸� 2"); 
	  sm.add(base,base+4,base+4, "���� �׸� 3"); 

	  //MenuItem item1 = null;
	// ���� �޴� �׸� �������� �������� �ʴ´�.
	  //item1.setIcon(R.drawable.icon48x48_2);

	  // �̷��� �ϸ� ��������,
	  sm.setIcon(R.drawable.icon48x48_1);

	  // �̷��� �ϸ� ��Ÿ�� ���ܰ� �߻��Ѵ�.
	  //sm.addSubMenu("������"); 
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	  MenuInflater inflater = getMenuInflater(); // ��Ƽ��Ƽ�κ���
	  inflater.inflate(R.menu.my_menu, menu); 

	  // �ݵ�� true�� ��ȯ�ؾ߸� �޴��� ���δ�.
	  return true; 
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item) 
	{
	  this.appendMenuItemText(item); 
	  if (item.getItemId() == R.id.menu_clear) 
	  {
	    this.emptyText(); 
	  }
	  else if (item.getItemId() == R.id.menu_dial) 
	  {
	    this.dial();
	  }
	  else if (item.getItemId() == R.id.menu_testPick) 
	  {
	    IntentsUtils.invokePick(this); 
	  }
	  else if (item.getItemId() == R.id.menu_testGetContent) 
	  {
	    IntentsUtils.invokeGetContent(this); 
	  }
	  else if (item.getItemId() == R.id.menu_show_browser) 
	  {
	    IntentsUtils.tryOneOfThese(this); 
	  }
	  // �޴� �׸� ó���� �Ϸ�Ǹ� �ݵ�� true�� ��ȯ�ؾ� �Ѵ�.
		return true;
	}
	
	private void dial() {
		// TODO Auto-generated method stub
		
	}
	
	private void emptyText() {
		// TODO Auto-generated method stub
		
	}
	private void appendMenuItemText(MenuItem item) {
		// TODO Auto-generated method stub
		
	}*/
}
