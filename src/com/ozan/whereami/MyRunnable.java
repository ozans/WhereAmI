package com.ozan.whereami;

import android.app.Activity;

public abstract class MyRunnable implements Runnable {

	protected Activity m_act;
	public MyRunnable(Activity act) {
		m_act=act;
	}

}
