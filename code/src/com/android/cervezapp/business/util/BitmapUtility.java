package com.android.cervezapp.business.util;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

/**
 * @author Billy
 */
public class BitmapUtility {

	/**
	 * Permite convertir una imagen a un array de bytes.
	 */
	public static byte[] getBytes(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, stream);
		return stream.toByteArray();
	}

	/*
	 * Permite obtener una imagen a partir de un array de bytes.El choto!
	 */
	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}

	/**
	 * Rota la imagen de acuerdo a la orientación de la misma.
	 */
	public static Bitmap rotate(File foto) {
		Bitmap myBitmap = BitmapFactory.decodeFile(foto.getAbsolutePath());
		Matrix matrix = new Matrix();
		try {
			ExifInterface exif = new ExifInterface(foto.getAbsolutePath());
			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
			if (orientation == 6) {
				matrix.postRotate(90);
			} else if (orientation == 3) {
				matrix.postRotate(180);
			} else if (orientation == 8) {
				matrix.postRotate(270);
			}
			myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
		} catch (Exception e) {
		}
		return myBitmap;
	}
}