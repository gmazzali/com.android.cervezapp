package com.android.cervezapp.view.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author Martin
 */
public class UserWatcher implements TextWatcher {

	private EditText component = null;

	public UserWatcher(EditText component) {
		super();
		this.component = component;
	}

	@Override
	public void afterTextChanged(Editable s) {
		String user = s.toString();
		if (user.trim().isEmpty()) {
			this.component.setError("El usuario no puede estar vacío");
		} else if (user.trim().length() < 6) {
			this.component.setError("El usuario debe contener como mínimo 6 caracteres");
		} else if (user.trim().length() > 60) {
			this.component.setError("El usuario no puede contener más de 60 caracteres");
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