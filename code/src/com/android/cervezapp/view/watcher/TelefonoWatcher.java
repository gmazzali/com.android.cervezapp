package com.android.cervezapp.view.watcher;

import android.widget.TextView;

/**
 * @author Billy
 */
public class TelefonoWatcher extends PatternFieldWatcher {

	private static final String REGEX_PATTERN = "^(\\+[0-9]{1,3}){0,1}([0-9]){10}$";

	public TelefonoWatcher(TextView component) {
		super(component, REGEX_PATTERN, "El teléfono no tiene un formato válido");
	}
}