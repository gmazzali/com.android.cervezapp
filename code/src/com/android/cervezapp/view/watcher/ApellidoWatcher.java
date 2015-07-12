package com.android.cervezapp.view.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * @author Billy
 */
public class ApellidoWatcher implements TextWatcher {

	private TextView component = null;

	public ApellidoWatcher(TextView component) {
		super();
		this.component = component;
	}

	@Override
	public void afterTextChanged(Editable s) {
		String apellido = this.component.getText().toString();
		if (apellido.trim().isEmpty()) {
			this.component.setError("El apellido no puede estar vacío");
		} else if (apellido.trim().length() < 3) {
			this.component.setError("El apellido debe contener como mínimo 3 letras");
		} else if (apellido.trim().length() > 60) {
			this.component.setError("El apellido no puede contener más de 60 caracteres");
		} else {
			this.component.setError(null);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
}