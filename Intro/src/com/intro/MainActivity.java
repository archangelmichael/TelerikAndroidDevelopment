package com.intro;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	final String TAG = "IntroToAndroid";
	final Context context = this;
	private TextView originalText;
	private EditText editText;
	private Button changeTextButton;
	private Button viewHistoryButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeTextButton = (Button)findViewById(R.id.changeTextBtn);
        changeTextButton.setOnClickListener(this);
        viewHistoryButton = (Button)findViewById(R.id.viewHistoryBtn);
        viewHistoryButton.setOnClickListener(this);
        
        editText = (EditText)findViewById(R.id.newText);
        originalText   = (TextView)findViewById(R.id.showText);
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
        
        return super.onOptionsItemSelected(item);
    }
    
    @Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}


	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.changeTextBtn) {
			Toast.makeText(context, "Text has been changed", Toast.LENGTH_SHORT).show();
	    	String oldText = originalText.getText().toString();
	    	String newText = editText.getText().toString();
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
	    	String postDate = dateFormat.format(Calendar.getInstance().getTime());
	    	MessageHistory.addChangeLog(new MessageChange(oldText, newText, postDate));
	    	editText.setText("");
	    	originalText.setText(newText);
	    	Log.d(TAG, oldText + " changed to: " + newText);
		} else if (v.getId() == R.id.viewHistoryBtn) {
			Intent intent = new Intent(MainActivity.this, TextMessageListActivity.class);
			this.startActivity(intent);
			Toast.makeText(context, "Loading Changes History", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "Invalid Selection", Toast.LENGTH_SHORT).show();
		}
	}
}
