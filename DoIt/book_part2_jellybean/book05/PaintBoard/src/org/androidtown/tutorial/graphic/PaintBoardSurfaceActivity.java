package org.androidtown.tutorial.graphic;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * ���ǽ��並 �̿��ϴ� ����Ʈ���带 �����ֱ� ���� ��Ƽ��Ƽ
 * 
 * @author Mike
 *
 */
public class PaintBoardSurfaceActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        PaintBoardSurface board = new PaintBoardSurface(this);
        setContentView(board);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
