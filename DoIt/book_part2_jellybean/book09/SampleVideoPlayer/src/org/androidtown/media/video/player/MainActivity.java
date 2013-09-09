package org.androidtown.media.video.player;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * ������ ��� ����� ���� �� �� �ֽ��ϴ�.
 * 
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	static final String VIDEO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
	private VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		Button startBtn = (Button) findViewById(R.id.startBtn);
		Button volumeBtn = (Button) findViewById(R.id.volumeBtn);

		startBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				videoView.seekTo(0);
				videoView.start();
			}
		});

		volumeBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
				int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, AudioManager.FLAG_SHOW_UI);
			}
		});

		videoView = (VideoView) findViewById(R.id.videoView);

		MediaController mc = new MediaController(this);
		videoView.setMediaController(mc);
		videoView.setVideoURI(Uri.parse(VIDEO_URL));
		videoView.requestFocus();

		videoView.setOnPreparedListener(new OnPreparedListener() {
			public void onPrepared(MediaPlayer player) {
				Toast.makeText(getApplicationContext(), "�������� �غ�Ǿ����ϴ�.\n'���' ��ư�� ��������.", Toast.LENGTH_LONG).show();
			}
		});

		videoView.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer player) {
				Toast.makeText(getApplicationContext(), "������ ����� �Ϸ�Ǿ����ϴ�.", Toast.LENGTH_LONG).show();
			}
		});

	}

	protected void onResume() {
		Toast.makeText(getApplicationContext(), "������ �غ����Դϴ�.\n��� ��ٷ��ּ���.", Toast.LENGTH_LONG).show();

		super.onResume();
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
