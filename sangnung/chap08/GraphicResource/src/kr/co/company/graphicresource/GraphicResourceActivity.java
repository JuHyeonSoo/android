package kr.co.company.graphicresource;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GraphicResourceActivity extends Activity {
	LinearLayout mLinearLayout;

	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);

	    // ���Ͼ� ���̾ƿ��� �����Ѵ� 
	    mLinearLayout = new LinearLayout(this);

	    // ImageView ��ü�� �����Ѵ� 
	    ImageView i = new ImageView(this);
		i.setImageDrawable(getResources().getDrawable(R.drawable.oval));
	    i.setMinimumHeight(100);
	    i.setMinimumWidth(100);

	    // ImageView�� ���̾ƿ��� �߰��Ѵ�
	    mLinearLayout.addView(i);
	    setContentView(mLinearLayout);
	}

}