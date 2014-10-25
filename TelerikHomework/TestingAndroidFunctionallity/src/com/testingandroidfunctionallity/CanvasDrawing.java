package com.testingandroidfunctionallity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;

public class CanvasDrawing extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new CustomView(this));
	}
	
	private class CustomView extends View {
		private static final float offsetStep = 50f;
		private float offsetX;
		private float offsetY;
		
		private Paint paint;
		private Path path;
		
		public CustomView(Context context) {
			super(context);


			this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			this.path = new Path();
			
			this.path.moveTo(300, 300);
			this.path.lineTo(400, 300);
			this.path.lineTo(400, 400);
			this.path.lineTo(300, 400);
			this.path.lineTo(300, 300);
			this.path.close();
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.save(Canvas.MATRIX_SAVE_FLAG);
			canvas.translate(this.offsetX, this.offsetY);
			
			canvas.drawColor(Color.CYAN);
			
			this.paint.setColor(Color.parseColor("#a0d8ef"));
			this.paint.setStrokeWidth(2);
			canvas.drawLine(100, 100, 200, 200, this.paint);
			
			this.paint.setStyle(Paint.Style.FILL);
			this.paint.setColor(Color.GREEN);
			canvas.drawRect(100, 400, 300, 440, this.paint);
			
			this.paint.setStyle(Paint.Style.STROKE);
			this.paint.setColor(Color.YELLOW);
			canvas.drawRect(100, 500, 300, 540, this.paint);
			
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.small);
			canvas.drawBitmap(bitmap, 20, 20, this.paint);
			
			this.paint.setColor(Color.RED);
			canvas.drawPath(this.path, this.paint);
			canvas.restore();
		}
		
		@Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() != MotionEvent.ACTION_DOWN)
                return false;

            float eventX = event.getX();
            float eventY = event.getY();

            if (eventX < getWidth() / 2)
                offsetX += offsetStep;
            else
                offsetX -= offsetStep;

            if (eventY <= getHeight() / 2)
                offsetY += offsetStep;
            else
                offsetY -= offsetStep;

            invalidate();

            return true;
        }
	}
}
