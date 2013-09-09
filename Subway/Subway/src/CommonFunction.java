﻿public class CommonFunction {
	// public static final CommonFunction thisclass = new CommonFunction();

	public String getStationCode(String strinput) {
		int index = strinput.indexOf("subCode");
		int Endindex = strinput.indexOf('&', index);

		String strTemp = strinput.substring(index, Endindex);

		index = strTemp.indexOf('=');

		return strTemp.substring(++index, strTemp.length());
	}

	// 모바일서울의 역코드 변경
	public static String getStrStationLine(String strLine) {
		String strStationLine = "";

		if (strLine.compareTo("ln1") == 0) {
			strStationLine = "1호선";
		} else if (strLine.compareTo("ln2") == 0) {
			strStationLine = "2호선";
		} else if (strLine.compareTo("ln3") == 0) {
			strStationLine = "3호선";
		} else if (strLine.compareTo("ln4") == 0) {
			strStationLine = "4호선";
		} else if (strLine.compareTo("ln5") == 0) {
			strStationLine = "5호선";
		} else if (strLine.compareTo("ln6") == 0) {
			strStationLine = "6호선";
		} else if (strLine.compareTo("ln7") == 0) {
			strStationLine = "7호선";
		} else if (strLine.compareTo("ln8") == 0) {
			strStationLine = "8호선";
		} else if (strLine.compareTo("ln9") == 0) {
			strStationLine = "9호선";
		} else if (strLine.compareTo("lnB") == 0) {
			strStationLine = "분당선";
		} else if (strLine.compareTo("lnI") == 0) {
			strStationLine = "인천1호선";
		} else if (strLine.compareTo("lnJ") == 0) {
			strStationLine = "중앙선";
		} else if (strLine.compareTo("lnK") == 0) {
			strStationLine = "경의선";
		} else if (strLine.compareTo("lnA") == 0) {
			strStationLine = "공항철도";
		}

		return strStationLine;
	}

	public static String getStationLine(String strLine) {
		String strStationLine = "";

		if (strLine.compareTo("1") == 0)
			strStationLine = "1호선";

		else if (strLine.compareTo("2") == 0)
			strStationLine = "2호선";

		else if (strLine.compareTo("3") == 0)
			strStationLine = "3호선";

		else if (strLine.compareTo("4") == 0)
			strStationLine = "4호선";

		else if (strLine.compareTo("5") == 0)
			strStationLine = "5호선";

		else if (strLine.compareTo("6") == 0)
			strStationLine = "6호선";

		else if (strLine.compareTo("7") == 0)
			strStationLine = "7호선";

		else if (strLine.compareTo("8") == 0)
			strStationLine = "8호선";

		else if (strLine.compareTo("9") == 0)
			strStationLine = "9호선";

		else if (strLine.compareTo("B") == 0)
			strStationLine = "분당선";

		else if (strLine.compareTo("I") == 0)
			strStationLine = "인천1호선";

		else if (strLine.compareTo("J") == 0)
			strStationLine = "중앙선";

		else if (strLine.compareTo("K") == 0)
			strStationLine = "경의선";

		else if (strLine.compareTo("A") == 0)
			strStationLine = "공항철도";

		else if (strLine.compareTo("G") == 0)
			strStationLine = "경춘선";

		return strStationLine;
	}
}
