package com.oz.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class BasicService extends Service implements Runnable {

	@Override
	public void run() {
		
		runHandler();
		
	}

	
	public abstract void runHandler();
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
