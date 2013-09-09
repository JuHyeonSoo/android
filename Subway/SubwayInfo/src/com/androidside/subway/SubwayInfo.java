﻿package com.androidside.subway;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.androidside.subway.common.DBAdapter;
import com.androidside.subway.common.LocalDBAdapter;
import com.androidside.subway.common.StationAdapter;
import com.androidside.subway.common.StationInfo;
import com.androidside.subway.ui.Station;

public class SubwayInfo extends ListActivity implements Runnable {

	static private final String tag = "Androidside_Subway";

	public static Activity ctx;
	ArrayList<StationInfo> arrStation = new ArrayList<StationInfo>();
	StationInfo stationInfo;
	Thread thread = null;

	private ProgressDialog progressDialog;
	private Dialog dialog;
	private Dialog Line_dialog;
	public static String strDBname;

	String strCID = "1000";
	StationAdapter mAdapter = null;
	int mJobId = 0;
	int mListId = 0;

	private SharedPreferences preferences;
	private static String strVersion;

	boolean bSearch = false; // 검색 플래그

	static AutoCompleteTextView txtViewStationName;
	static Button btnSearch;
	static Button btnLine;
	static Display display;
	
	static final String[] STATION = new String[] { "가능", "가산디지털단지", "간석", "개봉",
			"관악", "광명", "구로", "구일", "군포", "금정", "금천구청", "남영", "노량진", "녹양",
			"녹천", "당정", "대방", "덕계", "덕정", "도봉", "도봉산", "도원", "도화", "독산", "동대문",
			"동두천", "동두천중앙", "동묘앞", "동암", "동인천", "두정", "망월사", "명학", "방학", "배방",
			"백운", "병점", "보산", "봉명", "부개", "부천", "부평", "서동탄", "서울역", "서정리",
			"석계", "석수", "성균관대", "성북", "성환", "세류", "세마", "소사", "소요산", "송내",
			"송탄", "수원", "시청", "신길", "신도림", "신설동", "신이문", "신창", "쌍용", "아산",
			"안양", "양주", "역곡", "영등포", "오류동", "오산", "오산대", "온수", "온양온천", "외대앞",
			"용산", "월계", "의왕", "의정부", "인천", "제기동", "제물포", "종각", "종로3가", "종로5가",
			"주안", "중동", "지제", "지행", "직산", "진위", "창동", "천안", "청량리", "탕정", "평택",
			"풍기", "화서", "회기", "회룡", "강남", "강변", "건대입구", "교대", "구로디지털단지", "구의",
			"까치산", "낙성대", "당산", "대림", "도림천", "동대문역사문화공원", "뚝섬", "문래", "방배",
			"봉천", "사당", "삼성", "상왕십리", "서울대입구", "서초", "선릉", "성수", "신답", "신당",
			"신대방", "신림", "신정네거리", "신천", "신촌", "아현", "양천구청", "역삼", "영등포구청",
			"왕십리", "용답", "용두", "을지로3가", "을지로4가", "을지로입구", "이대", "잠실", "잠실나루",
			"종합운동장", "충정로", "한양대", "합정", "홍대입구", "가락시장", "경복궁", "경찰병원",
			"고속터미널", "구파발", "금호", "남부터미널", "녹번", "대곡", "대청", "대치", "대화", "도곡",
			"독립문", "동대입구", "마두", "매봉", "무악재", "백석", "불광", "삼송", "수서", "신사",
			"안국", "압구정", "약수", "양재", "연신내", "오금", "옥수", "원당", "일원", "잠원",
			"정발산", "주엽", "지축", "충무로", "학여울", "홍제", "화정", "경마공원", "고잔", "공단",
			"과천", "길음", "남태령", "노원", "당고개", "대공원", "대야미", "동작", "명동", "미아",
			"미아삼거리", "반월", "범계", "산본", "삼각지", "상계", "상록수", "선바위", "성신여대입구",
			"수리산", "수유", "숙대입구", "신길온천", "신용산", "쌍문", "안산", "오이도", "이촌", "인덕원",
			"정부과천청사", "정왕", "중앙", "총신대입구(이수)", "평촌", "한대앞", "한성대입구", "혜화",
			"회현", "강동", "개롱", "개화산", "거여", "고덕", "공덕", "광나루", "광화문", "군자",
			"굽은다리", "길동", "김포공항", "답십리", "둔촌동", "마곡", "마장", "마천", "마포", "명일",
			"목동", "발산", "방이", "방화", "상일동", "서대문", "송정", "신금호", "신정", "아차산",
			"애오개", "양평", "여의나루", "여의도", "영등포시장", "오목교", "올림픽공원", "우장산", "장한평",
			"천호", "청구", "행당", "화곡", "고려대", "광흥창", "구산", "녹사평", "대흥", "독바위",
			"돌곶이", "디지털미디어시티", "마포구청", "망원", "버티고개", "보문", "봉화산", "상수", "상월곡",
			"새절", "안암", "역촌", "월곡", "월드컵경기장", "응암", "이태원", "증산", "창신", "태릉입구",
			"한강진", "화랑대", "효창공원앞", "강남구청", "공릉", "광명사거리", "남구로", "남성", "내방",
			"논현", "뚝섬유원지", "마들", "먹골", "면목", "반포", "보라매", "사가정", "상도", "상봉",
			"수락산", "숭실대입구", "신대방삼거리", "신풍", "어린이대공원", "용마산", "이수", "장승배기",
			"장암", "중계", "중곡", "중화", "천왕", "철산", "청담", "하계", "학동", "강동구청",
			"남한산성입구", "단대오거리", "모란", "몽촌토성", "문정", "복정", "산성", "석촌", "송파",
			"수진", "신흥", "암사", "장지", "가양", "개화", "공항시장", "구반포", "국회의사당", "노들",
			"등촌", "마곡나루", "사평", "샛강", "선유도", "신논현", "신목동", "신반포", "신방화",
			"양천향교", "염창", "증미", "흑석", "개포동", "경원대", "구룡", "대모산입구", "미금", "보정",
			"서현", "수내", "야탑", "오리", "이매", "정자", "죽전", "태평", "한티", "간석오거리",
			"갈산", "경인교대입구", "계산", "계양", "국제업무지구", "귤현", "동막", "동수", "동춘",
			"문학경기장", "박촌", "부평구청", "부평삼거리", "부평시장", "선학", "센트럴파크", "신연수",
			"예술회관", "원인재", "인천대입구", "인천시청", "인천터미널", "임학", "작전", "지식정보단지",
			"캠퍼스타운", "테크노파크", "구리", "국수", "덕소", "도농", "도심", "망우", "서빙고", "신원",
			"아신", "양수", "양원", "양정", "오빈", "용문", "운길산", "원덕", "응봉", "중랑", "팔당",
			"한남", "가좌", "곡산", "금릉", "금촌", "능곡", "문산", "백마", "수색", "운정", "월롱",
			"일산", "탄현", "파주", "풍산", "행신", "화전", "검암", "운서", "공항화물청사", "인천국제공항",
			"가평", "갈매", "강촌", "굴봉산", "금곡", "김유정", "남춘천", "대성리", "마석", "백양리",
			"사릉", "상천", "청평", "춘천", "퇴계원", "평내호평" };

	public void Update() {
		progressDialog = ProgressDialog.show(ctx, "Loading", getString(R.string.loading),
				true, false);

		thread = new Thread(this);

		mJobId = IConstant.JOB_DBCOPY;

		try {
			thread.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doCopy() {
		File outDir = null;
		File outfile = null;

		// 외장메모리가 사용가능 상태인지 확인
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			outDir = new File(IConstant.SD_PATH + IConstant.DB_PATH);

			outDir.mkdirs(); // 디렉토리 생성
			outfile = new File(outDir, "subway.db");

			InputStream is = null;
			OutputStream os = null;
			int size;

			try {
				// AssetsManager를 이용하여 subway.db파일 읽기
				is = getAssets().open("subway.db");
				size = is.available();

				outfile.createNewFile(); // 파일 생성
				os = new FileOutputStream(outfile);

				byte[] buffer = new byte[size];

				is.read(buffer);
				os.write(buffer);

				is.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.main);
		
		txtViewStationName = (AutoCompleteTextView) findViewById(R.id.txtViewStationName);
		btnSearch = (Button) this.findViewById(R.id.btnSearch);
		btnLine = (Button) this.findViewById(R.id.btnline);
	
		ctx = this;
		preferences = getPreferences(MODE_WORLD_WRITEABLE);
		strVersion = preferences.getString("VERSION", "");

		// SharedPreferences에 저장된 버전과 app 버전이 다를 때 DB파일 외장메모리로 복사
		if (getString(R.string.appversion).compareTo(strVersion) != 0) {
			// 외장메모리가 사용가능할 때
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				Update();
			} else {
				showDialog(IConstant.DIALOG_NOSDCARD);
			}
		}
	
		// 자동완성 리스트 선택 시 자동으로 해당 역 조회
		txtViewStationName
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						String strStationName = (parent
								.getItemAtPosition(position)).toString();
						SearchButton(strStationName);
					}
				});
		
		// 검색 버튼
		btnSearch.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 버튼 클릭 할 때 소프트 키보드가 없어지게..
				InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.hideSoftInputFromWindow(txtViewStationName
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
				// AutoCompleteTextView에서 역명 가져오기.
				String strStationName = txtViewStationName.getText().toString()
						.trim();
				SearchButton(strStationName);
			}
		});
		
		// 호선버튼
		btnLine.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 버튼 클릭 할 때 소프트 키보드가 없어지게..
				InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.hideSoftInputFromWindow(txtViewStationName
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
				lineButton();
			}
		});
	
		registerForContextMenu(getListView()); // 컨텍스트 메뉴 등록
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		// 자동완성 텍스트뷰 구현
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_dropdown_item_1line, STATION);
		txtViewStationName.setAdapter(adapter);
		txtViewStationName.setText("");
	
		// 최근 검색역 리스트 조회
		makeRecentStation();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 뒤로가기 버튼
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (bSearch) {
				makeRecentStation();

				bSearch = false;
				return true;
			}

			finish();
		}
		return false;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case IConstant.DIALOG_DELETE_ALL:
			return dialog = new AlertDialog.Builder(ctx).setTitle(R.string.delete)
					.setMessage(R.string.delete_message)
					.setPositiveButton(R.string.alert_dialog_ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// TODO Auto-generated method stub
									LocalDBAdapter.open(ctx);
									LocalDBAdapter.deleteItemAll(/*strCID*/);
									LocalDBAdapter.close();

									// 최근 검색 리스트 생성
									makeRecentStation();
								}
							}).setNegativeButton(R.string.alert_dialog_cancle,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// TODO Auto-generated method stub
								}
							}).create();

		case IConstant.DIALOG_EXIT:
			return dialog = new AlertDialog.Builder(ctx).setTitle(R.string.exit)
					.setMessage(R.string.exit_message).setPositiveButton(
							R.string.alert_dialog_ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// TODO Auto-generated method stub
									finish();
								}
							}).setNegativeButton(R.string.alert_dialog_cancle,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
								}
							}).create();

		case IConstant.DIALOG_NOSDCARD:
			return new AlertDialog.Builder(ctx)
					.setTitle(R.string.sd_error)
					.setMessage(R.string.sd_error_detail)
					.setPositiveButton("확인",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// TODO Auto-generated method stub
									// 종료
									finish();
								}
							}).create();
		}

		return super.onCreateDialog(id);
	}

	/**
	 * 리스트 아이템 클릭
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		// 버튼 클릭 할 때 소프트 키보드가 없어지게..
		InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(txtViewStationName
				.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	
		StationInfo stationInfo = (StationInfo) l.getItemAtPosition(position);
		String strStationName = stationInfo.getStationName();
		String strStationLine = stationInfo.getLine();
	
		Intent intent = new Intent(SubwayInfo.this, Station.class);
	
		intent.putExtra("StationName", strStationName);
		intent.putExtra("StationLine", strStationLine);
		intent.putExtra("StationLocal", strCID);
	
		startActivity(intent);
	}

	/**
	 * 리스트 롱클릭 컨텍스트 메뉴
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (mListId == IConstant.LIST_FAVORITE) {
			super.onCreateContextMenu(menu, v, menuInfo);
			menu.setHeaderTitle(getString(R.string.delete_item));
			menu.add(0, IConstant.DELETE_ID, 2, R.string.menu_delete);
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// 개별삭제
		case IConstant.DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			final StationInfo station = arrStation.get((int) info.id);

			LocalDBAdapter.open(ctx);

			LocalDBAdapter.deleteItem(station.getStationName(), station
					.getLine()/*, station.getStationLocal()*/);

			LocalDBAdapter.close();
			makeRecentStation();

			return true;
		}

		return super.onContextItemSelected(item);
	}

	/**
	 * 옵션메뉴
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		if (mListId == IConstant.LIST_LINE) {
			menu.getItem(0).setVisible(false);
		} else if (mListId == IConstant.LIST_FAVORITE) {
			menu.getItem(0).setVisible(true);
		}
	
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	
		switch (item.getItemId()) {
		case R.id.deleteall:
			// 즐겨찾기 모두 삭제
			showDialog(IConstant.DIALOG_DELETE_ALL);
			break;
		
		case R.id.exit:
			// 애플리케이션 종료
			finish();
			break;
		}
	
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 검색 버튼
	 * 
	 * @param strStationName
	 */
	public void SearchButton(String strStationName) {
		bSearch = true;
	
		// 검색어가 없는 경우
		if ("".equals(strStationName)) {
			return;
		}
	
		// 검색어에서 "역" 제거
		if (strStationName.contains("역")) {
			strStationName.replace("역", "");
		}
	
		try {
			// DB Open
			DBAdapter.open();
	
			Log.e("com.androidside.subway", strStationName);
	
			// 역명, 지역으로 역 리스트 조회
			Cursor c = DBAdapter.selectStationToName(strStationName/*, strCID*/);
	
			// 리스트뷰에 채우기
			fillData(c);
	
			// 커서 닫기
			c.close();
	
			// DB Close
			DBAdapter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// AutoCompleteTextView 초기화
		txtViewStationName.setText("");
	}

	/**
	 * 호선 버튼
	 */
	public void lineButton() {
		// 호선선택 다이얼로그는 한번만 떠야 한다.
		if (Line_dialog != null) {
			if (Line_dialog.isShowing()) {
				return;
			}
		}
	
		Line_dialog = new AlertDialog.Builder(ctx).setTitle(
				R.string.line_select).setItems(R.array.cid_1000,
				new DialogInterface.OnClickListener() {
	
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						// TODO Auto-generated method stub
						String[] arrSubwayLine = getResources().getStringArray(
								R.array.cid_1000);
	
						try {
							// DB Open
							DBAdapter.open();
	
							// 호선명, 지역으로 역 리스트 조회
							Cursor c = DBAdapter.selectStationToLine(
									arrSubwayLine[whichButton]/*, strCID*/);
							// 리스트뷰에 채우기
							fillData(c);
	
							// Cursor Close
							c.close();
							// DB Close
							DBAdapter.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).setNegativeButton(R.string.alert_dialog_cancle,
				new DialogInterface.OnClickListener() {
	
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						// TODO Auto-generated method stub
					}
				}).create();
	
		Line_dialog.show();
	}
	
	/**
	 * 최근 검색역 리스트 조회
	 */
	public void makeRecentStation() {
		try {
			// LocalDB Open
			LocalDBAdapter.open(ctx);
	
			// 최근검색역 조회
			Cursor c = LocalDBAdapter.select();
	
			// 리스트 생성
			fillData(c);
	
			c.close();
			LocalDBAdapter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 역 리스트 생성
	 */
	private void fillData(Cursor c) {
		arrStation.clear();
	
		if (dialog != null) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
	
		if (Line_dialog != null) {
			if (Line_dialog.isShowing()) {
				Line_dialog.dismiss();
			}
		}
	
		while (c.moveToNext()) {
			String strStationLine = c.getString(1);
			String strStationName = c.getString(0);
	
			StationInfo stationInfo = new StationInfo(strStationLine,
					strStationName);
			arrStation.add(stationInfo);
		}
	
		// 어댑터를 생성합니다.
		mAdapter = new StationAdapter(this, R.layout.row, arrStation);
		setListAdapter(mAdapter);
	
		mListId = IConstant.LIST_FAVORITE;
		mAdapter.notifyDataSetChanged();
	
		c.close();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch (mJobId) {
		case IConstant.JOB_DBCOPY:
			doCopy();
			handler.sendEmptyMessage(IConstant.JOB_DBCOPY);
			break;
		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (mJobId) {
			case IConstant.JOB_DBCOPY:
				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}

				savePreferences();
				makeRecentStation();
				break;
			}
		}
	};

	/**
	 * 설정값 저장
	 */
	private void savePreferences() {
		preferences.edit().putString("VERSION", getString(R.string.appversion))
				.commit();
	}
}