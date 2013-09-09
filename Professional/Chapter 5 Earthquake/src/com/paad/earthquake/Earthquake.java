package com.paad.earthquake;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Earthquake extends Activity {

	static final private int MENU_UPDATE = Menu.FIRST;
	static final private int QUAKE_DIALOG = 1;
	
    ListView earthquakeListView;
    ArrayAdapter<Quake> aa;

    ArrayList<Quake> earthquakes = new ArrayList<Quake>();
	Quake selectedQuake;

	@Override
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.main);

	    earthquakeListView = (ListView)this.findViewById(R.id.earthquakeListView);

	    earthquakeListView.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView _av, View _v, int _index, long arg3) {
	            selectedQuake = earthquakes.get(_index);
	            showDialog(QUAKE_DIALOG);
	        }
	    });

	    int layoutID = android.R.layout.simple_list_item_1;
	    aa = new ArrayAdapter<Quake>(this, layoutID , earthquakes);
	    earthquakeListView.setAdapter(aa);

	    refreshEarthquakes();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);

	    menu.add(0, MENU_UPDATE, Menu.NONE, R.string.menu_update);

	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    super.onOptionsItemSelected(item);

	    switch (item.getItemId()) {
	        case (MENU_UPDATE): {
	            refreshEarthquakes();
	            return true;
	        }
	    }
	    return false;
	}
	
	@Override
	public Dialog onCreateDialog(int id) {
	    switch(id) {
	        case (QUAKE_DIALOG) :
	            LayoutInflater li = LayoutInflater.from(this);
	            View quakeDetailsView = li.inflate(R.layout.quake_details, null);

	            AlertDialog.Builder quakeDialog = new AlertDialog.Builder(this);
	            quakeDialog.setTitle("지진 발생 시간");
	            quakeDialog.setView(quakeDetailsView);
	            return quakeDialog.create();
	    }
	    return null;
	}

	@Override
	public void onPrepareDialog(int id, Dialog dialog) {
	    switch(id) {
	        case (QUAKE_DIALOG) :
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	            String dateString = sdf.format(selectedQuake.getDate());
	            String quakeText = "진도 " + selectedQuake.getMagnitude() +
	                               "\n" + selectedQuake.getDetails() + "\n" +
	                               selectedQuake.getLink();

	            AlertDialog quakeDialog = (AlertDialog)dialog;
	            quakeDialog.setTitle(dateString);
	            TextView tv = (TextView)quakeDialog.findViewById(R.id.quakeDetailsTextView);
	            tv.setText(quakeText);

	            break;
	    }
	}
    
	private void refreshEarthquakes() {
	    // XML을 가져온다.
	    URL url;
	    try {
	        String quakeFeed = getString(R.string.quake_feed);
	        url = new URL(quakeFeed);

	        URLConnection connection;
	        connection = url.openConnection();

	        HttpURLConnection httpConnection = (HttpURLConnection)connection;
	        int responseCode = httpConnection.getResponseCode();

	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            InputStream in = httpConnection.getInputStream(); 
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();

	            // 지진 정보 피드를 파싱한다.
	            Document dom = db.parse(in);
	            Element docEle = dom.getDocumentElement();

	            // 이전에 있던 지진 정보들을 모두 삭제한다.
	            earthquakes.clear();

	            // 지진 정보로 구성된 리스트를 얻어온다.
	            NodeList nl = docEle.getElementsByTagName("entry");
	            if (nl != null && nl.getLength() > 0) {
	                for (int i = 0 ; i < nl.getLength(); i++) {
	                    Element entry = (Element)nl.item(i);
	                    Element title = (Element)entry.getElementsByTagName("title").item(0);
	                    Element g = (Element)entry.getElementsByTagName("georss:point").item(0);
	                    Element when = (Element)entry.getElementsByTagName("updated").item(0);
	                    Element link = (Element)entry.getElementsByTagName("link").item(0);

	                    String details = title.getFirstChild().getNodeValue();
	                    String linkString = link.getAttribute("href");

	                    String point = g.getFirstChild().getNodeValue();
	                    String dt = when.getFirstChild().getNodeValue();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
	                    Date qdate = new GregorianCalendar(0,0,0).getTime();
	                    try {
	                        qdate = sdf.parse(dt);
	                    } catch (ParseException e) {
	                        e.printStackTrace();
	                    }

	                    String[] location = point.split(" ");
	                    Location l = new Location("dummyGPS");
	                    l.setLatitude(Double.parseDouble(location[0]));
	                    l.setLongitude(Double.parseDouble(location[1]));

	                    String magnitudeString = details.split(" ")[1];
	                    int end = magnitudeString.length()-1;
	                    double magnitude = Double.parseDouble(magnitudeString.substring(0, end));

	                    details = details.split(",")[1].trim();

	                    Quake quake = new Quake(qdate, details, l, magnitude, linkString);

	                    // 새로운 지진 정보를 처리한다.
	                    addNewQuake(quake);
	                }
	            }
	        }
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParserConfigurationException e) {
	        e.printStackTrace();
	    } catch (SAXException e) {
	        e.printStackTrace();
	    } finally {
	    }
	}

	private void addNewQuake(Quake _quake) {
	    // 새로운 지진 정보를 지진 정보 리스트에 추가한다.
	    earthquakes.add(_quake);

	    // 배열 어댑터에 하부 데이터의 변경 사실을 통지한다.
	    aa.notifyDataSetChanged();
	}
}