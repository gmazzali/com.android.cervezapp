package com.android.cervezapp.view.watcher;

import android.widget.TextView;

/**
 * @author Billy
 */
public class EmailWatcher extends PatternFieldWatcher {

	private static final String REGEX_PATTERN = "^[A-Za-z]+(_|\\.|-|[A-Za-z0-9])*@[A-Za-z]+(_|\\.|-|[A-Za-z0-9])*(\\.[A-Za-z]{2,3}){1,2}$";

	public EmailWatcher(TextView component) {
		super(component, REGEX_PATTERN, "La dirección de correo no tiene un formato válido");
	}
}