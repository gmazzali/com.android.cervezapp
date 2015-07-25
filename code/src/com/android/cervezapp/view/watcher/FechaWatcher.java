package com.android.cervezapp.view.watcher;

import android.widget.EditText;

/**
 * @author Billy
 */
public class FechaWatcher extends PatternFieldWatcher {

	private static final String REGEX_PATTERN = "^[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}$";

	public FechaWatcher(EditText component) {
		super(component, REGEX_PATTERN, "La fecha parece no tiene un formato válido");
	}
}