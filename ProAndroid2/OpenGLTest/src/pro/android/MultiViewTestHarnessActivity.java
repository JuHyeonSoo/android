package pro.android;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MenuItem;

public  class  MultiViewTestHarnessActivity extends Activity  {
	private GLSurfaceView mTestHarness;
	@Override
	protected void  onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
	
		mTestHarness  = new GLSurfaceView(this);
		mTestHarness.setEGLConfigChooser(false);
	
		Intent intent = getIntent();
		int mid = intent.getIntExtra("com.ai.menuid",  R.id.MenuId_OpenGL15_Current);
		if (mid  == R.id.MenuId_OpenGL15_Current)
		{
			mTestHarness.setRenderer(new TexturedPolygonRenderer(this));
			mTestHarness.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
			setContentView(mTestHarness);
			return;
		}
	
		if (mid  == R.id.mid_OpenGL15_SimpleTriangle)
		{
			mTestHarness.setRenderer(new SimpleTriangleRenderer(this));
			mTestHarness.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
			setContentView(mTestHarness);
			return;
		}
		if (mid  == R.id.mid_OpenGL15_AnimatedTriangle15)
		{
			mTestHarness.setRenderer(new AnimatedSimpleTriangleRenderer(this));
			setContentView(mTestHarness);
			return;
		}
		if (mid  == R.id.mid_rectangle)
		{
			mTestHarness.setRenderer(new SimpleRectangleRenderer(this));
			mTestHarness.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
			setContentView(mTestHarness);
			return;
		}
		if (mid  == R.id.mid_square_polygon)
		{
			mTestHarness.setRenderer(new SquareRenderer(this));
			mTestHarness.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
			setContentView(mTestHarness);
			return;
		}
		if (mid  == R.id.mid_polygon)
		{
			mTestHarness.setRenderer(new PolygonRenderer(this));
			setContentView(mTestHarness);
			return;
		}
		if (mid  == R.id.mid_textured_square)
		{
			mTestHarness.setRenderer(new TexturedSquareRenderer(this));
			mTestHarness.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
			setContentView(mTestHarness);
			return;
		}
		// ���� ��� if�� �ش���� ���� �� ������ ����
		mTestHarness.setRenderer(new TexturedPolygonRenderer(this));
		mTestHarness.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		setContentView(mTestHarness);
		return;
	}
	@Override
	protected void  onResume() 	{
		super.onResume();
		mTestHarness.onResume();
	}
	@Override
	protected void  onPause()  {
		super.onPause();
		mTestHarness.onPause();
	}
	
	
	@Override
	public  boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.mid_OpenGL15_SimpleTriangle)
		{
			// �� �޴� �׸��� ���ÿ��� �ٸ� �뵵�� ����� ���� �ִ�
			// ���� ��Ƽ��Ƽ�� ����Ű�� ��
			return true;
		}
		// �� �޴� �׸���� ���� �並 ����Ű�� ��
		this.invokeMultiView(item.getItemId());
		return true;
	}

	// �޴� ID�� ���� �ε� ����Ʈ�� ���� ���� �並 ȣ��
	// mid: �޴� ID
	private void  invokeMultiView(int  mid)
	{
		Intent intent = new Intent(this,MultiViewTestHarnessActivity.class);
		intent.putExtra("com.ai.menuid",  mid);
		startActivity(intent);
	}

}
