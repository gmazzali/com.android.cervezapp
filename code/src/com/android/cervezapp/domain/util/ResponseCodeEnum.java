package com.android.cervezapp.domain.util;

public enum ResponseCodeEnum {

	// USUARIO

	SACAR_FOTO_PERFIL_EXITOSO(-1),

	SACAR_FOTO_PERFIL_FALLIDO(0),

	ACEPTAR_AGREGAR_NUEVO_USUARIO(1000),

	CANCELAR_AGREDAR_NUEVO_USUARIO(0),

	ACEPTAR_MODIFICAR_USUARIO(1002),

	CANCELAR_MODIFICAR_USUARIO(0),

	// BAR

	SACAR_FOTO_BAR_EXITOSO(-1),

	SACAR_FOTO_BAR_FALLIDO(0),

	ACEPTAR_AGREGAR_NUEVO_BAR(2000),

	CANCELAR_AGREDAR_NUEVO_BAR(0),

	ACEPTAR_MODIFICAR_BAR(2002),

	CANCELAR_MODIFICAR_BAR(0),

	// COMENTARIO

	ACEPTAR_AGREGAR_COMENTARIO(1003),

	CANCELAR_AGREGAR_COMENTARIO(0);

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

			case AGREGAR_USUARIO:
				return new ResponseCodeEnum[] { ACEPTAR_AGREGAR_NUEVO_USUARIO, CANCELAR_AGREDAR_NUEVO_USUARIO };

			case MODIFICAR_USUARIO:
				return new ResponseCodeEnum[] { ACEPTAR_MODIFICAR_USUARIO, CANCELAR_MODIFICAR_USUARIO };

			case SACAR_FOTO_BAR:
				return new ResponseCodeEnum[] { SACAR_FOTO_BAR_EXITOSO, SACAR_FOTO_BAR_FALLIDO };

			case ADMINISTRAR_BARES:
				return new ResponseCodeEnum[] {};

			case AGREGAR_BAR:
				return new ResponseCodeEnum[] { ACEPTAR_AGREGAR_NUEVO_BAR, CANCELAR_AGREDAR_NUEVO_BAR };

			case MODIFICAR_BAR:
				return new ResponseCodeEnum[] { ACEPTAR_MODIFICAR_BAR, CANCELAR_MODIFICAR_BAR };

			case AGREGAR_COMENTARIO:
				return new ResponseCodeEnum[] { ACEPTAR_AGREGAR_COMENTARIO, CANCELAR_AGREGAR_COMENTARIO };
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