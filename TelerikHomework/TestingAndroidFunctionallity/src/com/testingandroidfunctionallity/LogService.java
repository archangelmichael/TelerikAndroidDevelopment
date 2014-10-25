package com.testingandroidfunctionallity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

@SuppressLint("NewApi")
public class LogService extends Service {	
	private static final String TAG = LogService.class.getSimpleName();		
	private LogUpdater update;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBind");
		return null;
		
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		update = new LogUpdater(TAG);
		Log.d(TAG, "onCreate");
	}
	
	@Override
	public synchronized void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.d(TAG, "onStart");
		if(!update.isStarted()) {
			Notify("Media Player", "Service started", 0000);
			update.start();
			update.setStarted(true);
		}
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroy");
		super.onDestroy();
		if (update.isStarted()) {
			update.interrupt();
			update.setStarted(false);
			Notify("Media Player", "Service stopped", 0000);
		}
		
		update = null;
	}
	
	private void Notify(String title, String message, int id) {
		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(title)
		        .setContentText(message);
		
		Intent resultIntent = new Intent(this, MainActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		
		NotificationManager mNotificationManager =
			    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			// mId allows you to update the notification later on.
			mNotificationManager.notify(id, mBuilder.build());
	}
}
