package com.android.cervezapp.view.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * @author Martin	
 */
public class UserWatcher implements TextWatcher {

	private TextView component = null;

	public UserWatcher(TextView component) {
		super();
		this.component = component;
	}

	@Override
	public void afterTextChanged(Editable s) {
		String user = this.component.getText().toString();
		if (user.trim().isEmpty()) {
			this.component.setError("El usuario no puede estar vac�o");
		} else if (user.trim().length() < 6) {
			this.component.setError("El usuario debe contener como m�nimo 6 d�gitos");
		} else if (user.trim().length() > 60) {
			this.component.setError("El usuario no puede contener m�s de 60 caracteres");
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