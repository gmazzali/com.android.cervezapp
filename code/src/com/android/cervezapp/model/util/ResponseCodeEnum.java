package com.android.cervezapp.model.util;

/**
 * @author Billy
 */
public enum ResponseCodeEnum {

	SACAR_FOTO_PERFIL_EXITOSO(-1), 
	
	SACAR_FOTO_PERFIL_FALLIDO(0);

	public static ResponseCodeEnum get(int code) {
		for (ResponseCodeEnum response : values()) {
			if (response.getResponse() == code) {
				return response;
			}
		}
		return null;
	}

	private int response;

	private ResponseCodeEnum(int response) {
		this.response = response;
	}

	public int getResponse() {
		return response;
	}
}