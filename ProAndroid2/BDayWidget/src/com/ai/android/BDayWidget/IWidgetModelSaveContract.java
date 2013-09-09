package com.ai.android.BDayWidget;

import java.util.Map;

public interface IWidgetModelSaveContract
{
	public void  setValueForPref(String key, String value);
	public String getPrefname();
	
	// ����Ǳ� ���ϴ� Ű-�� �� ��ȯ
	public Map<String,String> getPrefsToSave();
	
	// ��ȯ �� ȣ���
	public void  init();
}
