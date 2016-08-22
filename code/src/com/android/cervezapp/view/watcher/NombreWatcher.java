package com.android.cervezapp.view.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NombreWatcher implements TextWatcher {

	private EditText component = null;

	public NombreWatcher(EditText component) {
		super();
		this.component = component;
	}

	@Override
	public void afterTextChanged(Editable s) {
		String nombre = s.toString();
		if (nombre.trim().isEmpty()) {
			this.component.setError("El nombre no puede estar vacío");
		} else if (nombre.trim().length() < 3) {
			this.component.setError("El nombre debe contener como mínimo 3 letras");
		} else if (nombre.trim().length() > 60) {
			this.component.setError("El nombre no puede contener más de 60 caracteres");
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