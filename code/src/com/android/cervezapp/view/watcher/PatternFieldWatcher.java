package com.android.cervezapp.view.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * @author Billy
 */
public abstract class PatternFieldWatcher implements TextWatcher {

	private TextView component = null;

	private String pattern = null;

	private String failMessage = null;

	public PatternFieldWatcher(TextView component, String pattern, String failMessage) {
		super();
		this.component = component;
		this.pattern = pattern;
		this.failMessage = failMessage;
	}

	@Override
	public void afterTextChanged(Editable s) {
		String fecha = this.component.getText().toString();
		if (!fecha.equals("") && !fecha.matches(this.pattern)) {
			this.component.setError(this.failMessage);
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