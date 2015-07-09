package com.android.cervezup.model.util;

/**
 * @author Billy
 */
public enum RequestCodeEnum {

	SACAR_FOTO_PERFIL(100);

	public static RequestCodeEnum get(int code) {
		for (RequestCodeEnum request : values()) {
			if (request.getRequest() == code) {
				return request;
			}
		}
		return null;
	}

	private int request;

	private RequestCodeEnum(int request) {
		this.request = request;
	}

	public int getRequest() {
		return request;
	}
}