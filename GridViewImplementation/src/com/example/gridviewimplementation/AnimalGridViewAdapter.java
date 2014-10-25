package com.example.gridviewimplementation;

import android.R.integer;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalGridViewAdapter extends BaseAdapter {

	private Context mContext;
	
	public Animal[] animalsData;
	
	public AnimalGridViewAdapter(Context context, Animal[] data) {
		this.mContext = context;
		this.animalsData = data;
	}	
	
	@Override
	public int getCount() {		
		return animalsData.length;
	}

	@Override
	public Animal getItem(int position) {
		// TODO Auto-generated method stub
		return animalsData[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Animal animal = animalsData[position];
		Log.d("GRID", animal.getTitle());
		int animalAvatar = Animal.getAnimalPictureSource(animal);

		// SHOW IMAGES
		ImageView imageView;
        if (view == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageResource(animalAvatar);
        return imageView;
        
        // SHOW NAMES
//        TextView textView;
//        if (view == null) {
//        	textView = new TextView(mContext);
//        	textView.setLayoutParams(new GridView.LayoutParams(85, 85));
//        	textView.setGravity(Gravity.CENTER);
//        } else {
//        	textView = (TextView) view;
//        }
//
//        textView.setText(animal.getTitle() + " picture: " + animalAvatar);
//        return textView;
        
        
        // ADDITIONAL WRITINGS
//		if (view == null) {    
//	   // if it's not recycled, initialize some attributes  
//			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			view = inflater.inflate(position, parent, false);
//		}
	}
}
