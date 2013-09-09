package com.androidbook;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class Alerts
{
	public static void showAlert(String message, Context ctx) 
	{
		// �並 �ε���
		LayoutInflater li = LayoutInflater.from(ctx); 
	    View view = li.inflate(R.layout.alert_layout, null); 
	
	    // ������ �����ϰ� �� ����
	    AlertDialog.Builder builder = new AlertDialog.Builder(ctx); 
	    builder.setTitle("���â"); 
	    builder.setView(view); 
	
	    // ��ư�� ������ �߰�
	    EmptyListener pl = new EmptyListener(); 
		builder.setPositiveButton("OK", pl);
	
	    // ��ȭâ ����
	    AlertDialog ad = builder.create(); 
	
	    // ������ ��ȭâ�� ǥ��
	    ad.show();
	}
}
