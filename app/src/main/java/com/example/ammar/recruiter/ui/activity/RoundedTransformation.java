package com.example.ammar.recruiter.ui.activity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class RoundedTransformation implements Transformation {

	@Override
	public Bitmap transform(Bitmap source) {
		int size = Math.min(source.getWidth(), source.getHeight());

		int x = (source.getWidth() - size) / 4;
		int y = (source.getHeight() - size) / 4;

		Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
		if (squaredBitmap != source) {
			source.recycle();
		}

		Bitmap bitmap = Bitmap.createBitmap(size, size, Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		BitmapShader shader = new BitmapShader(squaredBitmap,
				BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
		paint.setShader(shader);
		paint.setAntiAlias(true);

		float r = size / 2f;
		canvas.drawCircle(r, r, r, paint);

		squaredBitmap.recycle();
		return bitmap;
	}

	@Override
	public String key() {
		return "circle";
	}

}
