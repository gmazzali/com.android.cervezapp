package com.android.cervezapp.view;

import android.app.Activity;
import android.os.Bundle;

import com.android.cervezapp.R;

/**
 * Ventana de selección de usuario de inicio.
 * 
 * @author Billy
 */
public class SeleccionUsuarioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_seleccion_usuario);
	}
}