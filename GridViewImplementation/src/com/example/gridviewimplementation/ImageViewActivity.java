package com.example.gridviewimplementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageViewActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);
		
		Intent intent = getIntent();
		Animal animal = (Animal)intent.getSerializableExtra("animal");
		intent.removeExtra("animal");
		int avatar = intent.getIntExtra("avatar", -1);
		
		ImageView img = (ImageView)findViewById(R.id.image_view);
		TextView title = (TextView)findViewById(R.id.image_title);
		TextView details = (TextView)findViewById(R.id.image_details);
		img.setImageResource(avatar);
		title.setText(animal.getTitle());
		details.setText(animal.getDetails()); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.image_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
