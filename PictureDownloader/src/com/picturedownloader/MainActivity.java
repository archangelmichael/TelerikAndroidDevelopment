package com.picturedownloader;

import java.io.InputStream;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private final String url = "http://3219a2.medialib.glogster.com/manofsteelmovienow/media/94/9421016a22adb21f310391ea03ca06aea00aa020/man-of-steel-man-of-steel-34785819-1024-14926hstry.png";
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	private Button mButton;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.container);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		mButton = (Button) findViewById(R.id.load_button);

		mButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// simpleLoadImage("");
				new LoadIconTask().execute(url);
			}
		});
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

	class LoadIconTask extends AsyncTask<String, Integer, Bitmap> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						params[0]).getContent());

			} catch (Exception e) {
				e.printStackTrace();
			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap image) {
			// TODO Auto-generated method stub
			mProgressBar.setVisibility(ProgressBar.INVISIBLE);
			if (image != null) {
				mImageView.setImageBitmap(image);
			} else {
				Toast.makeText(MainActivity.this,
						"Image Does Not exist or Network Error",
						Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			mProgressBar.setProgress(values[0]);
		}

	}
}
