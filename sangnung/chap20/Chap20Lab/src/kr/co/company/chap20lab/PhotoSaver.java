package kr.co.company.chap20lab;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.widget.Toast;

public class PhotoSaver implements PictureCallback {

	private final Context context;

	public PhotoSaver(Context context) {
		this.context = context;
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {

		File sdDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File pictureFileDir = new File(sdDir, "CameraExample");

		if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {

			Toast.makeText(context, "�̹����� ������ ���丮�� ������ �� ����",
					Toast.LENGTH_LONG).show();
			return;

		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
		String date = dateFormat.format(new Date());
		String photoFile = "pic_" + date + ".jpg";

		String filename = pictureFileDir.getPath() + File.separator + photoFile;

		File pictureFile = new File(filename);

		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();
			Toast.makeText(context, "���ο� �̹����� ������:" + photoFile,
					Toast.LENGTH_LONG).show();
		} catch (Exception error) {
			Toast.makeText(context, "�̹��� ���� ���� ���� �߻�", Toast.LENGTH_LONG)
					.show();
		}
	}

}