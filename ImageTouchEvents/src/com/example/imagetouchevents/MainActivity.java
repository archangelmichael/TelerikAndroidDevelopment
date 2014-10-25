package com.example.imagetouchevents;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnTouchListener {

	private static final String TAG = "Touch" ;
	// These matrices will be used to move and zoom image
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF start = new  PointF();
	public static PointF mid = new PointF();

	// We can be in one of these 3 states
	public static final int NONE = 0;
	public static final int DRAG = 1;
	public static final int ZOOM = 2;
	public static int mode = NONE;
	private int clickCount = 0;
	private long startTime;
   	private long duration;
   	private static final int MAX_DURATION = 500;

	float oldDist;

	private float[] matrixValues = new float[9];

	@Override
	public void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    ImageView view = (ImageView) findViewById(R.id.imageView);
	    view.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

	    ImageView view = (ImageView) v;

	    switch (event.getAction() & MotionEvent.ACTION_MASK) {

	    case MotionEvent.ACTION_DOWN:
	    	startTime = System.currentTimeMillis();
            clickCount++;
	    	
	        savedMatrix.set(matrix);
	        start.set(event.getX(), event.getY());
	        Log.d(TAG, "mode=DRAG" );
	        mode = DRAG;
	        break;

	    case MotionEvent.ACTION_POINTER_DOWN:

	        oldDist = spacing(event);
	        Log.d(TAG, "oldDist=" + oldDist);
	        if (oldDist > 10f) {

	            savedMatrix.set(matrix);
	            midPoint(mid, event);
	            mode = ZOOM;
	            Log.d(TAG, "mode=ZOOM" );
	        }
	        break;

	    case MotionEvent.ACTION_MOVE:

	        if (mode == DRAG) {

	            matrix.set(savedMatrix);
	            matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
	        }
	        else if (mode == ZOOM) {

	            float newDist = spacing(event);
	            Log.d(TAG, "newDist=" + newDist);
	            if (newDist > 10f) {

	                matrix.set(savedMatrix);
	                float scale = newDist / oldDist;
	                matrix.postScale(scale, scale, mid.x, mid.y);
	            }
	        }
	        break;

	    case MotionEvent.ACTION_UP:
	    	long time = System.currentTimeMillis() - startTime;
            duration=  duration + time;
            if(clickCount == 2)
            {
                if(duration<= MAX_DURATION)
                {
                    Toast.makeText(MainActivity.this, "double tap",Toast.LENGTH_LONG).show();
                }
                clickCount = 0;
                duration = 0;
                break;             
            }
	    case MotionEvent.ACTION_POINTER_UP:

	        mode = NONE;
	        Log.d(TAG, "mode=NONE" );
	        break;
	    }

	    // Perform the transformation
	    view.setImageMatrix(matrix);

	    return true; // indicate event was handled
	}

	  private float spacing(MotionEvent event) {
	    float x = event.getX(0) - event.getX(1);
	    float y = event.getY(0) - event.getY(1);
	    return FloatMath.sqrt(x * x + y * y);
	}

	private void midPoint(PointF point, MotionEvent event) {

	    float x = event.getX(0) + event.getX(1);
	    float y = event.getY(0) + event.getY(1);
	    point.set(x / 2, y / 2);
	}
}
