package com.android.cervezapp.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.android.cervezapp.business.service.UsuarioService;
import com.android.cervezapp.domain.util.SexoEnum;

public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String contenido;
	private String preview;
	private Float puntaje;
	private Long userId;
	private Long barId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Comentario other = (Comentario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public Float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Float puntaje) {
		this.puntaje = puntaje;
	}
	
	public String getUserName(){
		return UsuarioService.getInstance().getById(userId).getUserName();
	}
	
	public byte[] getFoto() {
		return UsuarioService.getInstance().getById(userId).getFoto();
	}
}