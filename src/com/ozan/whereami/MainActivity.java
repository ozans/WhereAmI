package com.ozan.whereami;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		finish();

		runOnUiThread(new MyRunnable(this) {
			@Override
			public void run() {
				try {
					LocationManager mlocManager=null;  
					LocationListener mlocListener;  
					mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);  
					mlocListener = new MyLocationListener(m_act,getBaseContext());  
					
					Criteria criteria = new Criteria();
					criteria.setAccuracy(Criteria.ACCURACY_FINE);
					String provider = mlocManager.getBestProvider(criteria, true);
					
					mlocManager.requestSingleUpdate(provider, mlocListener, Looper.myLooper());
				}
				catch (final Exception e)
				{
					System.out.println(e.toString());
					Toast.makeText(getBaseContext(), e.toString(),
							Toast.LENGTH_LONG).show();
				}

			}});
	}
}
