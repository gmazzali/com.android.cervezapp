package com.android.cervezapp.persistence.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Billy
 */
public class IdGenerator implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Map<Class<?>, Long> idMap = new HashMap<Class<?>, Long>();

	public synchronized static Long getNextId(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("La clase no puede ser nula");
		}
		Long id = 0L;
		if (IdGenerator.idMap.containsKey(clazz)) {
			id = IdGenerator.idMap.get(clazz);
		}
		id++;
		IdGenerator.idMap.put(clazz, id);
		
		return id;
	}
}