package kr.co.company.graphicresource1;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class GraphicResource1Activity extends Activity {
	LinearLayout mLinearLayout;

	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);

	    // ���Ͼ� ���̾ƿ��� �����Ѵ� 
	    mLinearLayout = new LinearLayout(this);

	    ShapeDrawable oval = new ShapeDrawable(new OvalShape());
	    oval.setIntrinsicHeight(100);
	    oval.setIntrinsicWidth(100);
	    oval.getPaint().setColor(Color.RED);

	    ImageView i = new ImageView(this);
	    i.setImageDrawable(oval);	// ���⼭ �����ϸ� �ȴ�. 
	    // ImageView�� ���̾ƿ��� �߰��Ѵ�
	    mLinearLayout.addView(i);
	    setContentView(mLinearLayout);
	}

}