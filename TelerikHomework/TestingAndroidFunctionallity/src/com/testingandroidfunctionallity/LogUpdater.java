package com.testingandroidfunctionallity;

import android.util.Log;

public class LogUpdater extends Thread{
	private static String TAG;
	private static final int Delay = 3000;
	private boolean isStarted = false;
	
	public LogUpdater(String tag)
	{
		super("Update Thread");
		this.TAG = tag;
	}
	
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			try {
				Log.d(TAG,"I am workin'g");
                Thread.sleep(Delay);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                this.setStarted(false);
            }
		}
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
}
