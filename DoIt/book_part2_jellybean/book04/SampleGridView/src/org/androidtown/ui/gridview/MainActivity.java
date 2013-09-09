package org.androidtown.ui.gridview;

import org.ubiworks.mobile.protocol.mdbc.android.MRecord;
import org.ubiworks.mobile.protocol.mdbc.android.MTable;
import org.ubiworks.mobile.protocol.mdbc.android.MType;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

/**
 * �׸���� ������ ����Ͽ� ���̺� ������� �����͸� �����ִ� ����� ���� �� �� �ֽ��ϴ�.
 * ���� ���̺��� �̿��� �����͸� �����ϴ� ����� ��� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	// �׸���� ��ü
	GridView gridview;
	
	// ����� ��ü
	DataAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ÿ��Ʋ ���߱�
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        // �׸���� ��ü ����
        gridview = (GridView) findViewById(R.id.gridview);

        // ����� ��ü ����
        adapter = new DataAdapter(this);

        // ���� ���̺�� ������ ����
        MTable table = createSampleTable();
        adapter.setTable(table);
        gridview.setNumColumns(this.adapter.getTable().countColumn);

        gridview.setAdapter(adapter);

        // �̺�Ʈ ó��
        gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				int countColumn = adapter.getTable().countColumn;
				int rowIndex = position / countColumn;

				Toast.makeText(getApplicationContext(), "Selected position : " + position + ", rowIndex : " + rowIndex, Toast.LENGTH_SHORT).show();

				adapter.setSelectedRow(rowIndex);
				adapter.notifyDataSetChanged();
			}
		});

    }

    /**
     * ���� ���̺�� ������ �����
     */
    private MTable createSampleTable() {
    	MTable outTable = new MTable("Contacts");

    	try {
	        outTable.addColumn("Name", MType.STRING, 60);
	        outTable.addColumn("Address", MType.STRING, 120);
	        outTable.addColumn("Group", MType.STRING, 40);

	        MRecord aRecord = outTable.createRecord();
	        aRecord.addCell("Mike");
	        aRecord.addCell("Seoul");
	        aRecord.addCell("Friends");
	        outTable.add(aRecord);

	        aRecord = outTable.createRecord();
	        aRecord.addCell("Ginnie");
	        aRecord.addCell("Busan");
	        aRecord.addCell("Friends");
	        outTable.add(aRecord);

	        aRecord = outTable.createRecord();
	        aRecord.addCell("John");
	        aRecord.addCell("Daejeon");
	        aRecord.addCell("Family");
	        outTable.add(aRecord);

	        aRecord = outTable.createRecord();
	        aRecord.addCell("ȫ�浿");
	        aRecord.addCell("����");
	        aRecord.addCell("����");
	        outTable.add(aRecord);

	        aRecord = outTable.createRecord();
	        aRecord.addCell("ȫ����");
	        aRecord.addCell("�λ�");
	        aRecord.addCell("����");
	        outTable.add(aRecord);

	        aRecord = outTable.createRecord();
	        aRecord.addCell("������");
	        aRecord.addCell("�뱸");
	        aRecord.addCell("����");
	        outTable.add(aRecord);

	        aRecord = outTable.createRecord();
	        aRecord.addCell("������");
	        aRecord.addCell("��õ");
	        aRecord.addCell("����");
	        outTable.add(aRecord);

	        aRecord = outTable.createRecord();
	        aRecord.addCell("������");
	        aRecord.addCell("����");
	        aRecord.addCell("����");
	        outTable.add(aRecord);
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}

        return outTable;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
