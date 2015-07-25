package com.android.cervezapp.persistence.util;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

/**
 * @author Billy
 */
public class DbBitmapUtility {

	/**
	 * Permite convertir una imagen a un array de bytes.
	 */
	public static byte[] getBytes(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, stream);
		return stream.toByteArray();
	}

	/*
	 * Permite obtener una imagen a partir de un array de bytes.
	 */
	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}
}