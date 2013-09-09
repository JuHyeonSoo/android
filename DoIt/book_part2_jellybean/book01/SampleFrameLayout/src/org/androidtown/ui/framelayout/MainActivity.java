package org.androidtown.ui.framelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * ������ ���̾ƿ��� �̿��� �� ���� �並 ��ø��Ű�� ���ü� �Ӽ��� �̿��� ���� �ٲٸ鼭 �����ݴϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	Button button01;
	ImageView imageView01;
	ImageView imageView02;
	int imageIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // �̹��� �ٲٱ� ��ư
        button01 = (Button) findViewById(R.id.button01);
        
        // ù��° �̹��� ��
        imageView01 = (ImageView) findViewById(R.id.imageView01);
        
        // �ι�° �̹��� ��
        imageView02 = (ImageView) findViewById(R.id.imageView02);

        // �̹��� �ٲٱ� ��ư�� ������ ��
        button01.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		changeImage();
        	}
        });

    }

    /**
     * ���� �����ִ� �̹����䰡 �ƴ� �ٸ� �̹����並 ���̵��� �ϴ� �޼ҵ�
     */
    private void changeImage() {
    	if (imageIndex == 0) {
    		imageView01.setVisibility(View.VISIBLE);
    		imageView02.setVisibility(View.INVISIBLE);

    		imageIndex = 1;
    	} else if (imageIndex == 1) {
    		imageView01.setVisibility(View.INVISIBLE);
    		imageView02.setVisibility(View.VISIBLE);

    		imageIndex = 0;
    	}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
