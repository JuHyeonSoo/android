package com.androidbook;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

public class PromptListener implements android.content.DialogInterface.OnClickListener 
{
	// ������Ʈ ���� ���� ��ȯ�ϴ� ���� ����
	private String promptReply = null; 
	
	// ������Ʈ ���� �˾Ƴ��� ���� �信 ������ ����
	View promptDialogView = null; 
	
	// �並 �����ڿ� ������
	public PromptListener(View inDialogView) {
		promptDialogView = inDialogView; 
	}

	// ��ȭâ�� ���� �ݹ� �޼���
	public void onClick(DialogInterface v, int buttonId) {
		if (buttonId == DialogInterface.BUTTON1) {
			// OK ��ư
			promptReply = getPromptText(); 
		}
		else {
			// Cancel ��ư
			//promptValue = null; 
		}
	}
	
	// ����Ʈ �ڽ��� ����ִ� ������ �˾Ƴ��� ���� �޼���
	private String getPromptText() {
		EditText et = (EditText) 
						promptDialogView.findViewById(R.id.promptmessage); 
		return et.getText().toString(); 
	}
	public String getPromptReply() { return promptReply; }
}
