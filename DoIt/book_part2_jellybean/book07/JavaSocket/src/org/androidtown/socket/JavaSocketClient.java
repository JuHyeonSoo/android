package org.androidtown.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ǥ�� �ڹٿ��� ������ ����ϴ� ����� ���� �� �� �ֽ��ϴ�.
 *
 * @author Mike
 */
public class JavaSocketClient {

	public static void main(String[] args) {

		try {

			int portNumber = 11001;
			Socket aSocket = new Socket("localhost", portNumber);
			ObjectOutputStream outstream = new ObjectOutputStream(aSocket.getOutputStream());
			outstream.writeObject("Hello Android Town");
			outstream.flush();

			ObjectInputStream instream = new ObjectInputStream(aSocket.getInputStream());
			System.out.println(instream.readObject());
			aSocket.close();

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
