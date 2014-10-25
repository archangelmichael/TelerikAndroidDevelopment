package com.mediaplayer;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class SoundService extends Service {

	private static final String TAG = SoundService.class.getName();
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.d(TAG, "On start");
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "On create");
		Notify("Media Player", "Service started", 0000);
		
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "On destroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "On start command");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "On bind");
		return null;
	}
	
	private void Notify(String title, String message, int id) {
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(title)
		        .setContentText(message);
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			// mId allows you to update the notification later on.
			mNotificationManager.notify(id, mBuilder.build());
	}

}
