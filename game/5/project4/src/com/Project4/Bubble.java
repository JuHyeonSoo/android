package com.Project4;

import java.util.*;

import android.content.*;
import android.graphics.*;
import android.graphics.Shader.TileMode;

//-------------------------------------
//  비눗방울
//-------------------------------------
public class Bubble {
	public int x, y, rad;			// 좌표, 반지름
	public  Bitmap imgBall;			// 비트맵 이미지
	public  boolean dead = false; 	// 터뜨림 여부
	
	private int _rad;				// 원래의 반지름
	private int sx, sy;				// 이동 방향 및 속도
	private int width, height;		// View의 크기
	private Bitmap Bubbles[] = new Bitmap[6];	// 풍선 애니메이션 용 이미지	
	private int imgNum = 0;			// 이미지 번호
	private int loop = 0;			// 루프 카운터
	
	//-------------------------------------
	//  생성자
	//-------------------------------------
	public Bubble(Context context, int _x, int _y, int _width, int _height) {
		width = _width;
		height = _height;
		x = _x;
		y = _y;
		
	    imgBall = BitmapFactory.decodeResource(context.getResources(), R.drawable.bubble_1);
	    
	    Random rnd = new Random();
	    _rad = rnd.nextInt(11) + 20;		// 반지름 : 20 ~ 30;
	    rad = _rad;
	    
	    // 반지름이 2 간격으로 커졌다 작아지는 공 6개 만들기
	    for (int i = 0; i <= 3; i++)
	    	Bubbles[i] = Bitmap.createScaledBitmap(imgBall, _rad * 2 + i * 2, _rad * 2 + i * 2, false);
	    Bubbles[4] = Bubbles[2];  
	    Bubbles[5] = Bubbles[1];
	    imgBall = Bubbles[0];
	    
	    sx = 2;
	    sy = rnd.nextInt(2) == 0 ? -2 : 2;
	    MoveBubble();
	}
	
	//-------------------------------------
	//  Move
	//-------------------------------------
	public void MoveBubble() {
		loop++;
		if (loop % 3 == 0) {
			imgNum++;			// 비눗방울 번호
			if (imgNum > 5) imgNum = 0;
			imgBall = Bubbles[imgNum];
			
			// 비눗방울의 반지름 설정
			rad = _rad + (imgNum <= 3 ? imgNum : 6 - imgNum) * 2;   
		}
		
		x += sx;
		y += sy;
		if (x >= width + rad) 	// 오른쪽 벽이면 맨 왼쪽으로 
			x = -rad;
		
		if (y <= rad || y >= 200) { 	// 상하로 충돌 
			sy = -sy;
			y += sy;
		}
	}
} // Bubble

//------------------------------------------------------------

//-------------------------------------
//  작은 방울
//-------------------------------------
class SmallBall {
	public int x, y, rad;			// 좌표, 반지름
	public  boolean dead = false; 	// 터뜨림 여부
	public  Bitmap imgBall;			// 비트맵 이미지
	
	private int width, height;		// View의 크기
	private int cx, cy;				// 원의 중심점
	private int cr;					// 원의 반지름
	private double r;				// 이동 각도 (radian)
	private int speed;				// 이동 속도
	private int num;				// 이미지 번호
	private int life;				// 생명 주기
	
	//-------------------------------------
	//  생성자
	//-------------------------------------
	public SmallBall(Context context, int _x, int _y, int ang, int _width, int _height) {
		cx = _x;		// 중심점
		cy = _y;
		width = _width;
		height = _height;
		r = ang * Math.PI / 180;		// 각도 radian
		
		Random rnd = new Random();
		speed = rnd.nextInt(5) + 2;		// 속도     : 2~6
		rad = rnd.nextInt(6) + 2;		// 반지름   : 2~7
		num = rnd.nextInt(6);			// 이미지  : 0~5
		life = rnd.nextInt(31) + 20;	// 20~50
		
		imgBall = BitmapFactory.decodeResource(context.getResources(), R.drawable.b0 + num);
		imgBall = Bitmap.createScaledBitmap(imgBall, rad * 2, rad * 2, false);
		cr = 10;						// 원의 초기 반지름
		MoveBall();
	}

	//-------------------------------------
	//  MoveBall
	//-------------------------------------
	public void MoveBall() {
		life--;
		cr += speed;
		x = (int) (cx + Math.cos(r) * cr); 
		y = (int) (cy - Math.sin(r) * cr); 
		if (x < -rad || x > width + rad ||
				y < -rad || y > height + rad || life <= 0)
			dead = true;
	}
}

//------------------------------------------------------------

//-------------------------------------
//  물방울 - 거미의 총알
//-------------------------------------
class WaterBall {
	public int x, y, rad;			// 좌표, 반지름
	public  boolean dead = false; 	// 제거 여부
	public  Bitmap imgBall;			// 비트맵 이미지
	
	private int width, height;		// View의 크기
	private int speed;				// 이동 속도
	
	//-------------------------------------
	//  생성자
	//-------------------------------------
	public WaterBall(Context context, int _x, int _y, int _width, int _height) {
		x = _x;	
		y = _y;
		width = _width;
		height = _height;
	    imgBall = BitmapFactory.decodeResource(context.getResources(), R.drawable.w0);
	    rad = imgBall.getWidth() / 2;
	    speed = 8;
	    MoveBall();
	}

	//-------------------------------------
	//  Move Water
	//-------------------------------------
	public void MoveBall() {
		y -= speed;
		if (y < 0) dead = true;
	}
}

//------------------------------------------------------------

//-------------------------------------
//  득점 표시 
//-------------------------------------
class Score {
	public int x, y, sw, sh;			// 좌표, 전체 이미지 크기
	public Bitmap imgScore;				// 글자 출력용 이미지
	
	private Bitmap fonts[] = new Bitmap[10];
	private int loop = 0;				// loop counter
	
	//-------------------------------------
	//  생성자
	//-------------------------------------
	public Score(Context context, int _x, int _y, int _score) {
		x = _x;	
		y = _y;
		for (int i = 0; i <= 9; i++)
			fonts[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.f0 + i);
		MakeScore(_score);
		Move();
	}

	//-------------------------------------
	//  점수 이미지
	//-------------------------------------
	public void MakeScore(int _score) {
		String score = "" + _score;			// 문자로 변환
		int L = score.length();				// 문자열의 길이

		// 빈 비트맵을 만든다
		imgScore = Bitmap.createBitmap(fonts[0].getWidth() * L, fonts[0].getHeight(), fonts[0].getConfig());

		// 비트맵에 이미지를 그려넣을 Canvas 생성
		Canvas canvas = new Canvas();
		// Canvas가 비트맵에 출력하도록 설정 - 이후의 모든 출력은 imgScore에 그려진다 
		canvas.setBitmap(imgScore);
		
		int w = fonts[0].getWidth();	 
		for (int i = 0; i < L; i++) {
			int n = (int) score.charAt(i) - 48;
			canvas.drawBitmap(fonts[n], w * i, 0, null); 
		}

		sw = imgScore.getWidth() / 2;
		sh = imgScore.getHeight() / 2;
	}

	//-------------------------------------
	//  Move
	//-------------------------------------
	public boolean Move() {
		y -= 4;
		if (y < -20)
			return false;
		else
			return true;
	}
}	

//------------------------------------------------------------

//-------------------------------------
//  게이지 표시 
//-------------------------------------
class Gauge {
	public boolean isTimeout = false;
	public Bitmap imgGauge;
	private long startTime;				// 시작시간
	private int sec;
	private int pos = 0;				// 게이지 시작 위치
	private Canvas canvas = new Canvas(); 
	private Paint paint = new Paint();
	
	//-------------------------------------
	//  생성자
	//-------------------------------------
	public Gauge(long _startTime, int _sec) {
		startTime = _startTime;
		sec = _sec;
		pos = 0;
		imgGauge = Bitmap.createBitmap(150, 15, Bitmap.Config.ARGB_8888);
		canvas.setBitmap(imgGauge);
		
		//게이지 만들기
		paint.setShader(new LinearGradient(0, 0, 0, 15, Color.GREEN, Color.BLACK, TileMode.CLAMP));
		canvas.drawRect(pos, 0, 150, 15, paint);
		Progress();
	}
	
	//-------------------------------------
	//  Progress
	//-------------------------------------
	public boolean Progress() {
		long thisTime = System.currentTimeMillis();
		float f = (thisTime - startTime) / 1000f;
		pos = (int) (150f / sec * f);
		Paint pnt = new Paint();
		pnt.setColor(Color.WHITE);
		canvas.drawRect(0, 0, 150, 15, pnt);	// 게이지 지움
		canvas.drawRect(pos, 0, 150, 15, paint);
		return false;
	}
}


