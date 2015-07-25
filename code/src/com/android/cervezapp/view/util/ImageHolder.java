package com.android.cervezapp.view.util;

import java.io.Serializable;

import android.graphics.Bitmap;

/**
 * Permite mantener una foto al momento de girar la pantalla.
 * 
 * @author Billy
 */
public class ImageHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Bitmap foto;

	public static Bitmap getFoto() {
		return foto;
	}

	public static void setFoto(Bitmap foto) {
		ImageHolder.foto = foto;
	}
}