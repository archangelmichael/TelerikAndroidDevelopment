package com.mediaplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SoundActivity extends ActionBarActivity implements View.OnClickListener {
	SeekBar seekBar; 
	ImageButton playButton, stopButton, nextButton, prevButton, soundButton; 
	MediaPlayer player; 
	TextView songTitle; 
	Handler seekHandler = new Handler();
	private boolean isPlayingFirstSong = true;
	private static List<String> songs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound);
		
		getInit();
		seekUpdation();
		getSongs();
		//this.startService(new Intent(SoundActivity.this, SoundService.class));
	}
	
	private void getSongs() {
		File dir = new File("../MediaPlayer/res/raw/");
		songs = new ArrayList<String>();
		traverse(dir);
	}
	
	public static void traverse (File dir) {
	    if (dir.exists()) {
	        File[] files = dir.listFiles();
	        for (int i = 0; i < files.length; ++i) {
	            File file = files[i];
	            if (file.isDirectory()) {
	                traverse(file);
	            } else {
	            	songs.add(file.getName());
	            	Log.d("FILE", file.getName());
	            }
	        }
	    }
	} 
	
	public void getInit() { 
		seekBar = (SeekBar) findViewById(R.id.seek_bar); 
		playButton = (ImageButton) findViewById(R.id.play_button); 
		stopButton = (ImageButton) findViewById(R.id.stop_button);
		
		nextButton = (ImageButton) findViewById(R.id.next_button);
		prevButton = (ImageButton) findViewById(R.id.previous_button);
		
		soundButton = (ImageButton) findViewById(R.id.sound_button);
		songTitle = (TextView) findViewById(R.id.title); 
		
		playButton.setOnClickListener(this); 
		stopButton.setOnClickListener(this); 
		
		nextButton.setOnClickListener(this); 
		prevButton.setOnClickListener(this); 
		
		soundButton.setOnClickListener(this); 
		player = MediaPlayer.create(this, R.raw.win1); 
		seekBar.setMax(player.getDuration()); 
	}
	
	Runnable run = new Runnable() { 
		@Override public void run() { 
			seekUpdation(); 
		} 
	};
	
	public void seekUpdation() { 
		seekBar.setProgress(player.getCurrentPosition()); 
		seekHandler.postDelayed(run, 1000); 
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) { 
			case R.id.play_button: 
				Toast.makeText(this, "Song Started", Toast.LENGTH_SHORT).show();
				songTitle.setText("Playing..."); 
				playButton.setVisibility(View.INVISIBLE);
				stopButton.setVisibility(View.VISIBLE);
				player.start(); 
				break; 
			case R.id.stop_button: 
				Toast.makeText(this, "Song Stopped", Toast.LENGTH_SHORT).show();
				stopButton.setVisibility(View.INVISIBLE);
				playButton.setVisibility(View.VISIBLE);
				player.pause(); 
				songTitle.setText("Paused..."); 
				break;
			case R.id.next_button:
				playNextSong();
				Toast.makeText(this, "Next song", Toast.LENGTH_SHORT).show();
				break;
			case R.id.previous_button:
				playNextSong();
				Toast.makeText(this, "Previous song", Toast.LENGTH_SHORT).show();
				break;
		}
	}

	private void playNextSong() {
		player.stop();
		playButton.setVisibility(View.INVISIBLE);
		stopButton.setVisibility(View.VISIBLE);
		
		if (isPlayingFirstSong) {
			player = MediaPlayer.create(this, R.raw.win2);
			isPlayingFirstSong = false;
		}
		else {
			player = MediaPlayer.create(this, R.raw.win1);
			isPlayingFirstSong = true;
		}
		
		player.start();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sound, menu);
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
}
