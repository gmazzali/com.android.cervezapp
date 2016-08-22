package com.android.cervezapp.persistence.util;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Billy
 */
public class IdGenerator implements Serializable {

	private static final long serialVersionUID = 1L;

	public synchronized static Long getNextId(Class<?> clazz) {
		return new Date().getTime();
	}
}