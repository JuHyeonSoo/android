package pro.android;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener {
	private EditText words = null;
	private Button speakBtn = null;
	private EditText filename = null;
	private Button recordBtn = null;
	private Button playBtn = null;
	private EditText useWith = null;
	private Button assocBtn = null;
	private String soundFilename = null;
	private File soundFile = null;
	private static final int REQ_TTS_STATUS_CHECK = 0;
	private static final String TAG = "TTS Demo";
	private TextToSpeech mTts = null;
	private MediaPlayer player = null;
	
	/** ��Ƽ��Ƽ ���� ���� �ÿ� ȣ��� */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		words = (EditText)findViewById(R.id.wordsToSpeak);
		filename = (EditText)findViewById(R.id.filename);
		useWith = (EditText)findViewById(R.id.realText);
		
		speakBtn = (Button)findViewById(R.id.speakBtn);
		speakBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
			mTts.speak(words.getText().toString(), TextToSpeech.QUEUE_ADD, null);
		}});
		
		recordBtn = (Button)findViewById(R.id.recordBtn);
		recordBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				soundFilename = filename.getText().toString();
				soundFile = new File(soundFilename);
				if (soundFile.exists())
					soundFile.delete();
				
				if(mTts.synthesizeToFile(words.getText().toString(), null,
				soundFilename)
				== TextToSpeech.SUCCESS) {
					Toast.makeText(getBaseContext(),
					"Sound file created",
					Toast.LENGTH_SHORT).show();
					playBtn.setEnabled(true);
					assocBtn.setEnabled(true);
				}
				else {
					Toast.makeText(getBaseContext(),
					"Oops! Sound file not created",
					Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		playBtn = (Button)findViewById(R.id.playBtn);
		playBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					player = new MediaPlayer();
					player.setDataSource(soundFilename);
					player.prepare();
					player.start();
				}
				catch(Exception e) {
					Toast.makeText(getBaseContext(),
					"Hmmmmm. Can't play file",
					Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
		});
		
		assocBtn = (Button)findViewById(R.id.assocBtn);
		assocBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mTts.addSpeech(useWith.getText().toString(), soundFilename);
				Toast.makeText(getBaseContext(),
				"Associated!",
				Toast.LENGTH_SHORT).show();
			}
		});
		
		// TTS�� �����ϴ����� ��� �غ� �ƴ��� �˻�
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
	}
		
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_TTS_STATUS_CHECK) {
			switch (resultCode) {
			case TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
				// TTS�� �����ϸ� ���� ���̸�
				mTts = new TextToSpeech(this, this);
				Log.v(TAG, "Pico�� ����� ��ġ�� �ֳ׿�");
				break;
			case TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:
			case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
			case TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
				// �����Ͱ� ��� ��ġ�ؾ� �ϸ�
				Log.v(TAG, "��� ��� �ʿ�: " + resultCode);
				Intent installIntent = new Intent();
				installIntent.setAction(
				TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
				break;
			case TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
			default:
				Log.e(TAG, "�����߾��. TTS�� ���� �� ���ƿ�");
			}
		}
		else {
		// �� ���� ��쿡 ���� �ڵ� �ֱ�
		}
	}
	
	@Override
	public void onInit(int status) {
		// TTS ������ �غ�Ǿ����� ��ư�� ����ȭ
		if( status == TextToSpeech.SUCCESS) {
			speakBtn.setEnabled(true);
			recordBtn.setEnabled(true);
		}
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		// ��Ŀ���� �����, ��� ����
		if(player != null) {
			player.stop();
		}
		// ��Ŀ���� �����, �б� ����
		if( mTts != null)
			mTts.stop();
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if(player != null) {
			player.release();
		}
		if( mTts != null) {
			mTts.shutdown();
		}
	}
}
