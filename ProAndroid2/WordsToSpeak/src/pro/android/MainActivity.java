package pro.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainActivity extends Activity implements OnInitListener, OnUtteranceCompletedListener {
	private EditText words = null;
	private Button  speakBtn  = null;
	private static final int REQ_TTS_STATUS_CHECK = 0;
	private static final String TAG = "TTS Demo";
	private TextToSpeech mTts;
	private int uttCount = 0;
	private int lastUtterance = -1;
	private HashMap<String, String>  params = new HashMap<String, String>();

	
	/** ��Ƽ��Ƽ ���� ���� �ÿ� ȣ��� */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		words = (EditText)findViewById(R.id.wordsToSpeak);
		speakBtn  = (Button)findViewById(R.id.speak);
		speakBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				mTts.speak(words.getText().toString(), TextToSpeech.QUEUE_ADD, null);
			}
		}
	);
	
	// TTS�� �����ϴ����� ��� �������� �˻�
	Intent checkIntent = new Intent();
	checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	startActivityForResult(checkIntent, REQ_TTS_STATUS_CHECK);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQ_TTS_STATUS_CHECK) {
			switch (resultCode) {
				case  TextToSpeech.Engine.CHECK_VOICE_DATA_PASS:
					// TTS�� �����ϸ� ���� ���̸�
					mTts = new TextToSpeech(this, this);
					Log.v(TAG, "Pico�� ���������� ��ġ�ƾ��");
					break;
				case  TextToSpeech.Engine.CHECK_VOICE_DATA_BAD_DATA:
				case  TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_DATA:
				case  TextToSpeech.Engine.CHECK_VOICE_DATA_MISSING_VOLUME:
					// �����Ͱ� ��� ��ġ�ؾ� �Ǹ�
					Log.v(TAG, "��� ��� �ʿ�: " + resultCode);
					Intent installIntent = new Intent();
					installIntent.setAction(
					TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
					startActivity(installIntent);
					break;
				case  TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL:
				default:
					Log.e(TAG, "�����߾��. TTS�� ���� �� ���ƿ�");
		}
		}
		else {
		// �� ���� ��쿡 ���� ó��
		}
	}
	
	//@Override
	public void onClick(View view) {
		StringTokenizer st = new StringTokenizer(words.getText().toString(),",.");
		while (st.hasMoreTokens()) {
			params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, String.valueOf(uttCount++));
			mTts.speak(st.nextToken(), TextToSpeech.QUEUE_ADD, params);
		}
	}

	@Override
	public void onInit(int status) {
		// TTS ������ �ϼ������� ���� ��ư�� ����ȭ����.
		if( status == TextToSpeech.SUCCESS) {
			speakBtn.setEnabled(true);
			mTts.setOnUtteranceCompletedListener(this);
		}
	}

	@Override
	public void onUtteranceCompleted(String uttId) {
		Log.v(TAG, "���ͷ��� Id�� �б� �Ϸ� �޽���: " + uttId);
		lastUtterance = Integer.parseInt(uttId);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		// ��Ŀ���� ������, ���� ����
		if( mTts != null)
		mTts.stop();
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mTts.shutdown();
	}
}
