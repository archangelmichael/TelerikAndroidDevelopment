package com.testingandroidfunctionallity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InflateView extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// USER ALREADY CREATED VIEWS
		//inflateCreatedViews();
		
		// USER CODE CREATED VIEW
		inflateCreatingViews();
	}

	private void inflateCreatingViews() {
		TextView view = new TextView(this);
		view.setText("Code inflated view");
		view.setTextColor(Color.CYAN);
		view.setBackgroundColor(Color.MAGENTA);
		setContentView(view);
	}

	private void inflateCreatedViews() {
		setContentView(R.layout.inflated_view);
		
		ViewGroup group = (ViewGroup) findViewById(R.id.container);
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.inflated_text_view, group, true);
	}

}
