package com.ozan.whereami;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {

	private Context m_context;
	private Activity m_act;

	public MyLocationListener(Activity act, Context context) {
		m_context = context;
		m_act = act;
	}

	@Override
	public void onLocationChanged(Location location) {
		final String provider=location.getProvider();
		final String lat=String.valueOf(location.getLatitude());  
		final String lon=String.valueOf(location.getLongitude());  
		String str=lat+", "+lon;
		Toast.makeText(m_context, str, Toast.LENGTH_LONG).show();

		new Thread(new Runnable() {
			@Override
			public void run() {
		try {
			String URL="http://gws2.maps.yahoo.com/findlocation?pf=1&locale=en_US&flags=&offset=15&q="+lat+"%2c"+lon+"&gflags=R&start=0&count=100&format=json";
			HttpClient httpclient = new DefaultHttpClient();
			final HttpResponse response = httpclient.execute(new HttpGet(URL));
			final StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String responseString = out.toString();

				JSONObject reader = new JSONObject(responseString);
				JSONObject result  = reader.getJSONObject("Result");
				String line1=result.getString("line1");
				String line2=result.getString("line2");
				String line3=result.getString("line3");
				//String line4=result.getString("line4"); //usually the country name
				String county=result.getString("county");
				String country=result.getString("country");
				final String str="["+provider+"] "+line1+(line2.length()!=0?", "+line2:"")+(line3.length()!=0?", "+line3:"")+", "+county+", "+country;

				m_act.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(m_context, str, Toast.LENGTH_LONG).show();		
					}
				});
			} else {
				response.getEntity().getContent().close();
				m_act.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(m_context, "Error: " + statusLine.getReasonPhrase(),
								Toast.LENGTH_LONG).show();
					}
				});

				throw new IOException(statusLine.getReasonPhrase());
			}
		}
		catch (final Exception e)
		{
			m_act.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(m_context, e.toString(),
							Toast.LENGTH_LONG).show();
				}
			});
			
		}
			}}).start();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
