package com.android.cervezapp.domain.util;

import java.io.Serializable;

/**
 * @author Billy
 */
public enum SexoEnum implements Serializable {

	MASCULINO("Masculino"),

	FEMENINO("Femenino"),

	TRANS("Trans"),

	OTROS("Otros");

	private String sexo;

	private SexoEnum(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return this.getSexo();
	}

	public String getSexo() {
		return sexo;
	}
}