package com.android.cervezapp.model.util;

/**
 * @author Billy
 */
public enum ResponseCodeEnum {

	SACAR_FOTO_PERFIL_EXITOSO(-1),

	SACAR_FOTO_PERFIL_FALLIDO(0);

	public static ResponseCodeEnum get(RequestCodeEnum requestCode, int code) {
		if (requestCode != null) {
			for (ResponseCodeEnum response : getByRequest(requestCode)) {
				if (response.getResponse() == code) {
					return response;
				}
			}
		}

		return null;
	}

	public static ResponseCodeEnum[] getByRequest(RequestCodeEnum requestCode) {
		if (requestCode != null) {
			switch (requestCode) {
				case SACAR_FOTO_PERFIL:
					return new ResponseCodeEnum[] { SACAR_FOTO_PERFIL_EXITOSO, SACAR_FOTO_PERFIL_FALLIDO };
			}
		}
		return new ResponseCodeEnum[] {};
	}

	private int response;

	private ResponseCodeEnum(int response) {
		this.response = response;
	}

	public int getResponse() {
		return response;
	}
}