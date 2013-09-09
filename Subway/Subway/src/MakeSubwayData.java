﻿import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeSubwayData {
	static String strCID; // 지역코드
	static String strLID; // 노선코드
	static String strSID; // 역코드
	static String strStationName; // 역명
	static String strStationLine; // 호선
	static String strStationURL; // URL(m.seoul.go.kr)
	static String strpreStation; // 이전 역
	static String strnextStation; // 다음 역
	static String strStationInfo; // 역정보(전화번호, 주소)
	static String strStaionExit; // 출구정보
	static String strStationTime; // 첫차, 막차정보

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = null;
			Statement stat;
			PreparedStatement prep = null;

			conn = DriverManager.getConnection("jdbc:sqlite:subway.db");
			stat = conn.createStatement();
			stat.executeUpdate("drop table if exists Station;");
			stat.executeUpdate("CREATE TABLE Station(StationName, StationLine, StationURL, preStation, nextStation, StationInfo, StationExit, StationTime, primary key (StationLine, stationName, StationURL));");
			prep = conn
					.prepareStatement("INSERT into Station values (?, ?, ?, ?, ?, ?, ?, ?);");

			/**
			 * 자료수집
			 */

			StationList stationList = new StationList();
			ArrayList<StationList> stationlist = stationList.getStationList();

			// ArrayList 모든역
			for (int i = 0; i < stationlist.size(); i++) {
				// for (int i = 0; i < 20; i++) {
				// for (int i = 6; i < 20; i++) {
				// 초기화
				strCID = "1000"; // 수도권이므로 1000으로 고정
				strLID = ""; // 노선코드
				strSID = ""; // 역코드
				strpreStation = ""; // 이전 역
				strnextStation = ""; // 다음 역
				strStaionExit = ""; // 출구정보
				strStationURL = ""; // URL(m.seoul.go.kr)
				strStationInfo = ""; // 역정보(전화번호, 주소)
				strStationTime = ""; // 첫차, 막차정보

				// strCID = stationlist.get(i).getStrCID();
				strCID = "1000"; // 수도권이므로 1000으로 고정
				strStationName = stationlist.get(i).getStrName();
				strStationLine = CommonFunction.getStationLine(stationlist.get(
						i).getStrLine());

				// 지역코드, 호선코드, 역코드 얻기(CID, LID, SID)
				String URL = String
						.format("http://traffic.map.naver.com/Subway/Pop_Subway.asp?CID=%s&SEFlag=1&SearchName=",
								strCID)
						+ URLEncoder.encode(strStationName, "EUC-KR");
				String data = DownloadHtml(URL);

				Pattern StationPattern = Pattern
						.compile("javascript:SendData\\((.+),(.+),(.+),'(.+)'\\)(.+)(\\s+)(.+)>(.+)</td>");
				Matcher StationMatches = StationPattern.matcher(data);

				if (StationMatches.find()) {
					do {
						String strLine = StationMatches.group(8).trim();
						String strName = StationMatches.group(4).trim()
								.replace("'", "");
						int nIndex = strName.indexOf('(');

						if (nIndex > 0) {
							strName = strName.substring(0, nIndex);
						}

						// 수집한 역명, 호선과 일치하면 수집, 아니면 다음 조회 결과로 넘어감.
						if (strLine.compareTo(strStationLine) == 0
								&& strName.compareTo(strStationName) == 0) {
							strCID = StationMatches.group(1).trim(); // 지역코드
							strSID = StationMatches.group(2).trim(); // 역코드
							strLID = StationMatches.group(3).trim(); // 호선코드

							break;
						} else
							continue;
					} while (StationMatches.find());
				} else {
					continue;
				}

				/**
				 * 역 정보
				 */
				getStationInfo();

				/**
				 * 출구 정보
				 */
				getExitInfo();

				/**
				 * 첫차, 막차 스케줄
				 */
				getScheduleInfo();

				/**
				 * 모바일서울 URL
				 */
				getMobileSeoulURL();

				// DB 추가.
				prep.setString(1, strStationName);
				prep.setString(2, strStationLine);
				prep.setString(3, strStationURL);
				prep.setString(4, strpreStation);
				prep.setString(5, strnextStation);
				prep.setString(6, strStationInfo);
				prep.setString(7, strStaionExit);
				prep.setString(8, strStationTime);

				prep.addBatch();

				// 데이터가 잘 수집되었는지 확인하기 위한 Log
				String strLog = String.format("%s, %s, %s, %s, %s, %s",
						strStationName, strStationLine, strStationURL,
						strpreStation, strnextStation, strStationInfo);
				System.out.println(strLog);
			} // DB와 연결이 끊어지지 않았다면 데이터를 삽입한다.
			
			if (conn != null) {
				conn.setAutoCommit(false);
				prep.executeBatch();
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 역 정보 (이전역, 다음역, 주소, 전화번호)
	 */
	private static void getStationInfo() {
		// TODO Auto-generated method stub

		String StationURL = String
				.format("http://traffic.map.naver.com/Subway/StationInfo_Detail.asp?CID=%s&TMenu=2&LMenu=1&LID=%s&SID=%s",
						strCID, strLID, strSID);
		System.out.println(StationURL);
		String StationPagedata = DownloadHtml(StationURL).trim();

		String StationInfoPatternCompile = String
				.format("<area shape=\"rect\" (.+) href=\"javascript:StationInfo\\(%s,%s,%s\\)\" title=\"(.+)\" />",
						strCID, strLID, strSID);

		Pattern StationPattern = Pattern.compile(StationInfoPatternCompile);
		Matcher StationPatternMatches = StationPattern.matcher(StationPagedata);

		if (StationPatternMatches.find()) {
			strStationName = StationPatternMatches.group(2);

			// naver 역정보 보정 <-> 죽전 오류
			if (strSID.compareTo("1533") == 0
					&& strStationName.compareTo("죽전(단국대)") == 0)
				strStationName = "보정";

			else if (strSID.compareTo("1534") == 0
					&& strStationName.compareTo("보정") == 0)
				strStationName = "죽전(단국대)";
		}

		// 이전 역 / 다음 역
		StationPattern = Pattern
				.compile("<td width=\"85\"(.+)>(.+)</td>(\\s+)(.+)(\\s+)<td width=\"70\"(.+)>(.+)</td>(\\s+)(.+)(\\s+)<td width=\"85\"(.+)>(.+)</td>");
		StationPatternMatches = StationPattern.matcher(StationPagedata);

		while (StationPatternMatches.find()) {
			// 마곡나루역 미개통 처리.
			if (Integer.parseInt(strSID) == 905) {
				continue;
			}

			String strPre = StationPatternMatches.group(1);
			String strNext = StationPatternMatches.group(11);

			Pattern stationNamePattern = Pattern
					.compile("(.+)>(.+)</a>/<a (.+)");
			Matcher stationNameMatches = stationNamePattern.matcher(strPre);

			if (stationNameMatches.find()) {
				strpreStation = stationNameMatches.group(2).trim() + ", ";
			}

			stationNameMatches = stationNamePattern.matcher(strNext);

			if (stationNameMatches.find()) {
				strnextStation = stationNameMatches.group(2).trim() + ", ";
			}

			strpreStation += StationPatternMatches.group(2).replace("</a>", "");
			strnextStation += StationPatternMatches.group(12).replace("</a>",
					"");

			// 2호선 성수역
			if (Integer.parseInt(strSID) == 211) {
				strpreStation = "뚝섬, 용답";
				strnextStation = "건대입구";
			}

			// 2호선 성수역 지선
			// 네이버에서 2호선 성수역 지선역은 이전역과 다음역이 반대로 되어있다.
			if (Integer.parseInt(strSID) >= 251
					&& Integer.parseInt(strSID) <= 254) {
				String strTemp;

				strTemp = strpreStation;
				strpreStation = strnextStation;
				strnextStation = strTemp;
			}
		}

		// 역정보 - 주소, 전화번호
		StationPattern = Pattern
				.compile("<td width=\"500\" class=\"gray01\">(.+)</td>");
		StationPatternMatches = StationPattern.matcher(StationPagedata);

		String strAddress = "";
		String strTel = "";

		while (StationPatternMatches.find()) {
			String strTemp1 = StationPatternMatches.group(0).trim();

			if (strTemp1.contains("주소")) {
				strAddress = StationPatternMatches.group(1).trim()
						.replace("<b>", "").replace("</b>", "");
			}

			if (strTemp1.contains("전화번호")) {
				strTel = StationPatternMatches.group(1).trim()
						.replace("<b>", "").replace("</b>", "");
			}
		}

		strStationInfo = strAddress + "\n" + strTel;
	}

	/**
	 * 출구 정보
	 */
	private static void getExitInfo() {
		// TODO Auto-generated method stub

		String StationURL = "http://traffic.map.naver.com/Subway/StationInfo_Detail_GateInfo.asp?CID="
				+ strCID + "&TMenu=2&LMenu=1&LID=" + strLID + "&SID=" + strSID;
		String StationPagedata = DownloadHtml(StationURL);

		Pattern ExitInfoPattern;
		Matcher ExitInfoPatternMatches;
		String strStationdata;

		int nBeginIndex;
		int nEndIndex;

		// 출구정보만 잘라 내기.
		nBeginIndex = StationPagedata
				.indexOf("<table cellspacing=\"0\" cellpadding=\"0\" border=0 ID=\"Table37\">");
		nEndIndex = StationPagedata.indexOf("</table>", nBeginIndex);

		if (nBeginIndex > 0 && nEndIndex > 0) {
			strStationdata = StationPagedata.substring(nBeginIndex, nEndIndex);

			ExitInfoPattern = Pattern.compile("<tr valign=\"top\">");
			ExitInfoPatternMatches = ExitInfoPattern.matcher(strStationdata);

			// 출구별로 데이터 가져오기.
			while (ExitInfoPatternMatches.find()) {
				String strExitDetail = "";
				String strExit = "";

				nBeginIndex = ExitInfoPatternMatches.start();
				nEndIndex = strStationdata.indexOf("</tr>", nBeginIndex);

				// 각 출구별 정보만 잘라내기
				String strExitdata = strStationdata.substring(nBeginIndex,
						nEndIndex);

				// 출구번호
				Pattern ExitPattern = Pattern
						.compile("ico_qno2_(.+).gif\"(.+)>(.+)<a(.+)");
				Matcher ExitPatternMatches = ExitPattern.matcher(strExitdata);

				if (ExitPatternMatches.find()) {
					String strExiteNumber = ExitPatternMatches.group(1); // 출구번호

					if (strExiteNumber.startsWith("0")) {
						strExiteNumber = strExiteNumber.replace("0", "");
					}

					strExit = strExiteNumber + ExitPatternMatches.group(3)
							+ " : ";
				}

				Pattern StationExitPattern = Pattern.compile("(.+);");
				Matcher StationExitPatternMatches = StationExitPattern
						.matcher(strExitdata);

				while (StationExitPatternMatches.find()) {
					strExitDetail += StationExitPatternMatches.group(1).trim()
							.replace(",&nbsp", ", ").replace("&nbsp", "");
				}

				strExit += strExitDetail;
				strStaionExit += strExit + "//";
			}
		}
	}

	/**
	 * 첫차, 막차 정보
	 */
	private static void getScheduleInfo() {
		// TODO Auto-generated method stub

		String strStationURL = "http://traffic.map.naver.com/Subway/StationInfo_Detail_TimeBoard.asp?CID="
				+ strCID + "&TMenu=2&LMenu=1&LID=" + strLID + "&SID=" + strSID;
		String StationPagedata = DownloadHtml(strStationURL);

		String strTimeWorkdayFirst = "";
		String strTimeWorkdayLast = "";
		String strTimeSaturdayFirst = "";
		String strTimeSaturdayLast = "";
		String strTimeSundayFirst = "";
		String strTimeSundayLast = "";

		String strScheduledata;

		// 첫차, 막차정보만 잘라 내기.
		// 상행 첫차
		int start = StationPagedata
				.indexOf("<table cellspacing=\"0\" cellpadding=\"0\" ID=\"Table38\">");
		int end = StationPagedata.indexOf("</table>", start);

		Pattern ScheduleInfoPattern;
		Matcher ScheduleInfoPatternMatches;

		if (start > 0 && end > 0) {
			strScheduledata = StationPagedata.substring(start, end);

			/**
			 * 첫차/막차정보 수집
			 */

			ScheduleInfoPattern = Pattern
					.compile("<td width=\"120\" class=\"gray01\">(.+)</td>(\\s+)<td width=\"13\" class=\"gray04\">(.+)</td>(\\s+)<td class=\"gray01\">(.+)</td>");
			ScheduleInfoPatternMatches = ScheduleInfoPattern
					.matcher(strScheduledata);

			// 요일별로 데이터 가져오기.
			while (ScheduleInfoPatternMatches.find()) {
				String strToward = ScheduleInfoPatternMatches.group(1); // 방향
				String strTime = ScheduleInfoPatternMatches.group(5); // 시간

				if (strToward.contains("(평일")) {
					strTimeWorkdayFirst += strTime + "("
							+ strToward.replace("(평일)", "") + "),";
				} else if (strToward.contains("(토요일)")) {
					strTimeSaturdayFirst += strTime + "("
							+ strToward.replace("(토요일)", "") + "),";
				} else if (strToward.contains("(일/공휴일)")) {
					strTimeSundayFirst += strTime + "("
							+ strToward.replace("(일/공휴일)", "") + "),";
				}
			}

			// 상행과 하행 구분
			strTimeWorkdayFirst += "###";
			strTimeSaturdayFirst += "###";
			strTimeSundayFirst += "###";
		}

		// 상행 막차
		start = StationPagedata
				.indexOf("<table cellspacing=\"0\" cellpadding=\"0\" ID=\"Table39\">");
		end = StationPagedata.indexOf("</table>", start);

		if (start > 0 && end > 0) {
			strScheduledata = StationPagedata.substring(start, end);

			/**
			 * 첫차/막차정보 수집
			 */

			// <td width="120"
			// class="gray01">서울역(경의선)(평일)</td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td
			// width="13"
			// class="gray04">|</td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td
			// class="gray01">05:32</td>
			ScheduleInfoPattern = Pattern
					.compile("<td width=\"120\" class=\"gray01\">(.+)</td>(\\s+)<td width=\"13\" class=\"gray04\">(.+)</td>(\\s+)<td class=\"gray01\">(.+)</td>");
			ScheduleInfoPatternMatches = ScheduleInfoPattern
					.matcher(strScheduledata);

			// 요일별로 데이터 가져오기.
			while (ScheduleInfoPatternMatches.find()) {
				String strToward = ScheduleInfoPatternMatches.group(1); // 방향
				String strTime = ScheduleInfoPatternMatches.group(5); // 시간

				if (strToward.contains("(평일")) {
					strTimeWorkdayLast += strTime + "("
							+ strToward.replace("(평일)", "") + "),";
				} else if (strToward.contains("(토요일)")) {
					strTimeSaturdayLast += strTime + "("
							+ strToward.replace("(토요일)", "") + "),";
				} else if (strToward.contains("(일/공휴일)")) {
					strTimeSundayLast += strTime + "("
							+ strToward.replace("(일/공휴일)", "") + "),";
				}
			}
			// 상행과 하행 구분 strTimeWorkdayLast += "###"; strTimeSaturdayLast +=
			// "###";
			// strTimeSundayLast += "###";
		}

		// 하행 첫차
		start = StationPagedata
				.indexOf("<table cellspacing=\"0\" cellpadding=\"0\" ID=\"Table40\">");
		end = StationPagedata.indexOf("</table>", start);

		if (start > 0 && end > 0) {
			strScheduledata = StationPagedata.substring(start, end);

			/**
			 * 첫차/막차정보 수집
			 */

			// 상행과 하행 구분
			strTimeWorkdayLast += "###";
			strTimeSaturdayLast += "###";
			strTimeSundayLast += "###";

			// <td width="120"
			// class="gray01">서울역(경의선)(평일)</td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td
			// width="13"
			// class="gray04">|</td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td
			// class="gray01">05:32</td>
			ScheduleInfoPattern = Pattern
					.compile("<td width=\"120\" class=\"gray01\">(.+)</td>(\\s+)<td width=\"13\" class=\"gray04\">(.+)</td>(\\s+)<td class=\"gray01\">(.+)</td>");
			ScheduleInfoPatternMatches = ScheduleInfoPattern
					.matcher(strScheduledata);

			// 요일별로 데이터 가져오기.
			while (ScheduleInfoPatternMatches.find()) {
				String strToward = ScheduleInfoPatternMatches.group(1); // 방향
				String strTime = ScheduleInfoPatternMatches.group(5); // 시간

				if (strToward.contains("(평일")) {
					strTimeWorkdayFirst += strTime + "("
							+ strToward.replace("(평일)", "") + "),";
				} else if (strToward.contains("(토요일)")) {
					strTimeSaturdayFirst += strTime + "("
							+ strToward.replace("(토요일)", "") + "),";
				} else if (strToward.contains("(일/공휴일)")) {
					strTimeSundayFirst += strTime + "("
							+ strToward.replace("(일/공휴일)", "") + "),";
				}
			}

			// 상행과 하행 구분
			strTimeWorkdayFirst += "###";
			strTimeSaturdayFirst += "###";
			strTimeSundayFirst += "###";
		}

		// 하행 막차
		start = StationPagedata
				.indexOf("<table cellspacing=\"0\" cellpadding=\"0\" ID=\"Table41\">");
		end = StationPagedata.indexOf("</table>", start);

		if (start > 0 && end > 0) {
			strScheduledata = StationPagedata.substring(start, end);

			/**
			 * 첫차/막차정보 수집
			 */

			// 상행과 하행 구분 strTimeWorkdayLast += "###"; strTimeSaturdayLast +=
			// "###";
			// strTimeSundayLast += "###";

			// <td width="120"
			// class="gray01">서울역(경의선)(평일)</td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td
			// width="13"
			// class="gray04">|</td>\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td
			// class="gray01">05:32</td>
			ScheduleInfoPattern = Pattern
					.compile("<td width=\"120\" class=\"gray01\">(.+)</td>(\\s+)<td width=\"13\" class=\"gray04\">(.+)</td>(\\s+)<td class=\"gray01\">(.+)</td>");
			ScheduleInfoPatternMatches = ScheduleInfoPattern
					.matcher(strScheduledata);

			// 요일별로 데이터 가져오기.
			while (ScheduleInfoPatternMatches.find()) {
				String strToward = ScheduleInfoPatternMatches.group(1); // 방향
				String strTime = ScheduleInfoPatternMatches.group(5); // 시간

				if (strToward.contains("(평일")) {
					strTimeWorkdayLast += strTime + "("
							+ strToward.replace("(평일)", "") + "),";
				} else if (strToward.contains("(토요일)")) {
					strTimeSaturdayLast += strTime + "("
							+ strToward.replace("(토요일)", "") + "),";
				} else if (strToward.contains("(일/공휴일)")) {
					strTimeSundayLast += strTime + "("
							+ strToward.replace("(일/공휴일)", "") + "),";
				}
			}

			// 상행과 하행 구분
			strTimeWorkdayLast += "###";
			strTimeSaturdayLast += "###";
			strTimeSundayLast += "###";
		}

		strStationTime += "<평일>\n" + strTimeWorkdayFirst + "//"
				+ strTimeWorkdayLast + "/>\n" + "<토요일>\n"
				+ strTimeSaturdayFirst + "//" + strTimeSaturdayLast + "/>\n"
				+ "<공휴일>\n" + strTimeSundayFirst + "//" + strTimeSundayLast
				+ "/>\n";
	}

	/**
	 * 모바일서울 URL
	 */
	private static void getMobileSeoulURL() {
		// TODO Auto-generated method stub

		String strURL = "";
		String strPagedata;
		String strTempStationName = "";

		// 총신대입구(이수)의 경우가 있을 수 있다.
		Pattern pattern = Pattern.compile("(.+)\\((.+)\\)");
		Matcher matches = pattern.matcher(strStationName);

		if (matches.find()) {
			strTempStationName = matches.group(1);
		} else {
			strTempStationName = strStationName;
		}

		// '서울역'의 경우 '서울' 로 검색한다.
		int nIndex = strStationName.lastIndexOf("역");

		if (nIndex == strStationName.length() - 1) {
			strTempStationName = strStationName.substring(0, nIndex);
		} else if (strStationName.indexOf(" ") > 0) {
			// 역명에 공백이 있으면 제거.
			strTempStationName = strStationName.replace(" ", "");
		}

		try {
			strURL = "http://m.seoul.go.kr/traffic/SubInfoSearchList.do?subSearch="
					+ URLEncoder.encode(strTempStationName, "EUC-KR")
					+ "&flag=4";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		strPagedata = DownloadHtml(strURL);

		Pattern URLPattern = Pattern
				.compile("<a href=\"(.+)\" class=\"lst_a\"><div class=\"(.+)\">(.+)</div></a>");
		Matcher URLPatternMatches = URLPattern.matcher(strPagedata);

		while (URLPatternMatches.find()) {
			String strTempLine = CommonFunction
					.getStrStationLine(URLPatternMatches.group(2));
			String strTempName = URLPatternMatches.group(3);
			String strName2 = "";

			pattern = Pattern.compile("(.+) (.+)");
			matches = pattern.matcher(strTempName);

			while (matches.find()) {
				strTempName = matches.group(2);

				if (strTempName.length() != strTempStationName.length()) {

					// 총신대입구(이수)의 경우가 있을 수 있다.
					Pattern namePattern = Pattern.compile("(.+)\\((.+)\\)");
					Matcher nameMatches = namePattern.matcher(strTempName);

					if (nameMatches.find()) {
						strTempName = nameMatches.group(1);
						strName2 = nameMatches.group(2);
					} else {
						namePattern = Pattern.compile("(.+) (.+)");
						nameMatches = namePattern.matcher(strTempName);

						if (nameMatches.find()) {
							strTempName = nameMatches.group(1);
						}
					}
				}
			}

			String strTempStationLine = strStationLine;

			// 역명과 호선이 일치 할 경우만 URL을 저장한다.
			if (strTempStationLine.compareTo(strTempLine) == 0
					&& strTempStationName.compareTo(strTempName) == 0) {
				strStationURL = URLPatternMatches.group(1)
						.replace("&amp;", "&");
				break;
			} else if (strTempStationLine.compareTo(strTempLine) == 0
					&& strTempStationName.compareTo(strName2) == 0) {
				strStationURL = URLPatternMatches.group(1)
						.replace("&amp;", "&");
				break;
			} else {
				continue;
			}
		}
	}

	// Html Url을 인자로 받아 html 내용을 리턴하는 함수.
	public static String DownloadHtml(String strURL) {
		StringBuilder html = new StringBuilder();

		try {
			URL url = new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn != null) {
				// Connection Timout 5초
				conn.setConnectTimeout(5000);
				conn.setUseCaches(false);

				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(conn.getInputStream(),
									"EUC-KR"));

					String line = "";

					while (true) {
						line = br.readLine();

						if (line == null)
							break;

						// readLine으로 라인단위로 읽었기 때문에 계행문자(\n) 삽입
						html.append(line + "\n");
					}

					br.close();
				}

				conn.disconnect();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return html.toString();
	}
}
