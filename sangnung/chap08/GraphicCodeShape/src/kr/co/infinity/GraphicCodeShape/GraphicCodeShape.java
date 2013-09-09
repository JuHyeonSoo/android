package kr.co.infinity.GraphicCodeShape;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GraphicCodeShape extends Activity {
	LinearLayout mLinearLayout;

	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  // ���Ͼ� ���̾ƿ��� �����Ѵ�. 
	  mLinearLayout = new LinearLayout(this);

	  Path p = new Path();
	  p.moveTo(100, 100);
	  p.lineTo(50, 150);
	  p.lineTo(100, 200);
	  p.lineTo(100, 100);

	  ShapeDrawable shape = new ShapeDrawable(new PathShape(p, 200 , 200));
	  shape.setIntrinsicHeight(300);
	  shape.setIntrinsicWidth(300);
	  shape.getPaint().setColor(Color.MAGENTA);

	  ImageView i = new ImageView(this);
	  i.setImageDrawable(shape);	// ���⼭ �ٷ� �����ϸ� �ȴ�. 

	  // ImageView�� ���̾ƿ��� �߰��Ѵ�.
	  mLinearLayout.addView(i);
	  setContentView(mLinearLayout);

	}

}