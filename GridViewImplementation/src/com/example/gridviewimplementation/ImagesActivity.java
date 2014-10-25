package com.example.gridviewimplementation;

import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class ImagesActivity extends ActionBarActivity {

	private GridView grid;
	private Context context = this;
	AnimalGridViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_images);
	    
	    Log.d("GRID", "Started");
		
		grid = (GridView)this.findViewById(R.id.images_view);	
		adapter = new AnimalGridViewAdapter(this, getAllAnimals());
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String animalTitle = String.valueOf(adapter.getItem(position).getTitle());
				Toast.makeText(context, animalTitle, Toast.LENGTH_SHORT).show();
				
				Animal animal = (Animal)adapter.getItem(position);
				Intent intent = new Intent(ImagesActivity.this, ImageViewActivity.class);
				intent.putExtra("animal", (Serializable)animal);
				intent.putExtra("avatar", Animal.getAnimalPictureSource(animal));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.images, menu);
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

	public Animal[] getAllAnimals(){
		return new Animal[] {
			new Animal("Cat", "Cute animal. Likes to scratch!"),
			new Animal("Dog", "Loyal animal. Barks at night!"),
			new Animal("Wolf", "Wild doglike animal!"),
			new Animal("Elephant", "Big and heavy!"),
			new Animal("Tiger", "Mighty beautiful killer cat!"),
			new Animal("Lion", "King of all animals!"),
			new Animal("Dolphin", "Most inteligent animal!"),
			new Animal("Horse", "Beautiful fast animal used for riding!"),
			new Animal("Eagle", "Mighty animal likely to attack you rappidly from above!")
	    };  
	}
}
