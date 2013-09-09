package org.androidtown.basic.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable �������̽��� �����ϴ� Ŭ���� ����
 * 
 * @author Mike
 */
public class SimpleData implements Parcelable {
	// ���� ������
	int number;
	
	// ���ڿ� ������
	String message;

	/**
	 * ������ 2���� �̿��Ͽ� �ʱ�ȭ�ϴ� ������
	 * 
	 * @param num
	 * @param msg
	 */
	public SimpleData(int num, String msg) {
		number = num;
		message = msg;
	}
	
	/**
	 * �ٸ� Parcel ��ü�� �̿��� �ʱ�ȭ�ϴ� ������
	 * 
	 * @param src
	 */
	public SimpleData(Parcel src) {
		number = src.readInt();
		message = src.readString();
	}
	
	/**
	 * ������ CREATOR ��ü ����
	 */
	@SuppressWarnings("unchecked")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		
		public SimpleData createFromParcel(Parcel in) {
			return new SimpleData(in);
		}
		
		public SimpleData[] newArray(int size) {
			return new SimpleData[size];
		}
		
	};
	 
	
	public int describeContents() {
		return 0;
	}

	/**
	 * �����͸� Parcel ��ü�� ����
	 */
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(number);
		dest.writeString(message);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
