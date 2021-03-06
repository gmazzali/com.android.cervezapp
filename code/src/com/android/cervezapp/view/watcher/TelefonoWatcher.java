package com.android.cervezapp.view.watcher;

import android.widget.EditText;

/**
 * @author Billy
 */
public class TelefonoWatcher extends PatternFieldWatcher {

	private static final String REGEX_PATTERN = "^(\\+[0-9]{1,3}){0,1}([0-9]){10}$";

	public TelefonoWatcher(EditText component) {
		super(component, REGEX_PATTERN, "El tel�fono no tiene un formato v�lido");
	}
}