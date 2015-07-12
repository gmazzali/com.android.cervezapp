package com.android.cervezapp.view.listener;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.android.cervezapp.view.field.FechaPickerFragment;

/**
 * @author Billy
 */
public class CambiarFechaListener implements OnClickListener {

	private Activity activity;

	private EditText fechaEditText;

	public CambiarFechaListener(Activity activity, EditText fechaEditText) {
		this.activity = activity;
		this.fechaEditText = fechaEditText;
	}

	@Override
	public void onClick(View v) {
		FechaPickerFragment fechaPickerFragment = new FechaPickerFragment(this.fechaEditText);
		fechaPickerFragment.show(this.activity.getFragmentManager(), "Fecha nacimiento");
	}
}