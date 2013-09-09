package org.androidtown.ui.fragment;

import android.app.Activity;
import android.os.Bundle;

/**
 * �����׸�Ʈ�� ����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 */
public class SampleListActivity extends Activity implements SampleListFragment.ListItemSelectedListener {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_list_activity);
	}

	public void onListItemSelected(int index) {
		SampleViewerFragment imageViewer = (SampleViewerFragment) getFragmentManager().findFragmentById(R.id.image_viewer_fragment);
		//if (imageViewer == null || !imageViewer.isInLayout()) {
		//	Intent showImage = new Intent(getApplicationContext(), SampleViewerActivity.class);
		//	showImage.putExtra("index", index);
//
	//		startActivity(showImage);
		//} else {
			imageViewer.update(index);
		//}
	}
}