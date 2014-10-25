package com.testingandroidfunctionallity;

import java.io.IOException;
import java.io.InputStream;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Contactables;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements OnMenuItemClickListener {
	Context context = this;
	ImageView tiger;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// GET IMAGE FROM ASSETS
		tiger = (ImageView)this.findViewById(R.id.tiger);
		tiger.setImageBitmap(getBitmapFromAssests("tigerimg.jpg"));
		
		// WRITE DATA TO FILE (BEWARE THE USER PERMISSIONS)
		FileUtil.writeDataToFile(this, "Test Write To File");
		
		// SETTING CUSTOM ACTIVITY BAR LAYOUT
		ActionBar actionBar = this.getActionBar();
		actionBar.hide();
		actionBar.show();
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setCustomView(R.layout.action_bar);
		actionBar.setDisplayShowCustomEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		else if (id == R.id.action_add) {
			Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
			return true;
		}
		else if (id == R.id.action_delete) {
			Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// GET IMAGE FROM ASSETS (move image to assets folder)
	private Bitmap getBitmapFromAssests(String filename) {
		AssetManager assetManager = this.getAssets();
		InputStream inputStream = null;
		
		try {
			inputStream = assetManager.open(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Bitmap fromAsset = BitmapFactory.decodeStream(inputStream);
		return fromAsset;
	}
	
	// INFLATE NEW VIEW
	public void inflateView(View view) {
		Intent intent = new Intent(MainActivity.this, InflateView.class);
		startActivity(intent);
	}
	
	// MANAGE SERVICE (Add service to manifest)
	public void startService(View view) {
		this.startService(new Intent(MainActivity.this, LogService.class));
	}
	
	// MANAGE SERVICE (Add service to manifest)
	public void stopService(View view) {
		this.stopService(new Intent(MainActivity.this, LogService.class));
	}
	
	
	// MAKE POPUP MENU
	public void popUpMenu(View view) {
		PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
		popupMenu.setOnMenuItemClickListener(this);
		popupMenu.inflate(R.menu.main);
		popupMenu.show();
	}
	
	// DRAW ON CANVAS
	public void drawOnCanvas(View view) {
		Intent intent = new Intent(MainActivity.this, CanvasDrawing.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
		return false;
	}
}
