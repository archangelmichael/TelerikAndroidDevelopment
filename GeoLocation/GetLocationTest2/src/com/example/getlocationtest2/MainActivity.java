package com.example.getlocationtest2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private LocationManager locationManager = null;
	private LocationListener locationListener = null;

	private Button btnGetLocation = null;
	private EditText editLocation = null;
	private ProgressBar progressBar = null;

	private static final String TAG = "Debug";
	private Boolean flag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressBar = (ProgressBar) findViewById(R.id.get_location_progress);
		progressBar.setVisibility(View.INVISIBLE);

		editLocation = (EditText) findViewById(R.id.location_info);

		btnGetLocation = (Button) findViewById(R.id.add_location_button);
		btnGetLocation.setOnClickListener(this);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

	}

	@Override
	public void onClick(View v) {
		flag = displayGpsStatus();
		if (flag) {
			Log.v(TAG, "getLocationCoordinates");
			editLocation.setText("Please!! move your device to"
					+ " see the changes in coordinates." + "\n Wait..");
			progressBar.setVisibility(View.VISIBLE);
			locationListener = new MyLocationListener();

			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

		} else {
			alertbox("Gps Status!!", "Your GPS is: OFF");
		}
	}

	private Boolean displayGpsStatus() {
		if (locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {
			return true;
		} else {
			return false;
		}
	}

	protected void alertbox(String title, String mymessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your Device's GPS is Disable")
				.setCancelable(false)
				.setTitle("** Gps Status **")
				.setPositiveButton("Gps On",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent myIntent = new Intent(
										Settings.ACTION_SECURITY_SETTINGS);
								startActivity(myIntent);
								dialog.cancel();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// cancel the dialog box
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	/*----------Listener class to get coordinates ------------- */
	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {

			editLocation.setText("");
			progressBar.setVisibility(View.INVISIBLE);
			Toast.makeText(
					getBaseContext(),
					"Location changed : Lat: " + loc.getLatitude() + " Lng: "
							+ loc.getLongitude(), Toast.LENGTH_SHORT).show();
			String longitude = "Longitude: " + loc.getLongitude();
			Log.v(TAG, longitude);
			String latitude = "Latitude: " + loc.getLatitude();
			Log.v(TAG, latitude);

			

			String s = longitude + "\n" + latitude	+ "\n";
			editLocation.setText(s);
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
}
