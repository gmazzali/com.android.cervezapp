package com.android.cervezapp.persistence.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.android.cervezapp.domain.model.Usuario;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private static UsuarioDao instance = new UsuarioDao();

	private Map<Long, Usuario> mapa = new HashMap<Long, Usuario>();

	public static UsuarioDao getInstance() {
		return UsuarioDao.instance;
	}

	private UsuarioDao() {
	}

	public Usuario getById(Long id) {
		return this.mapa.get(id);
	}

	public void saveUsuario(Usuario usuario) {
		this.mapa.put(usuario.getId(), usuario);
	}

	public void updateUsuario(Usuario usuario) {
		this.mapa.put(usuario.getId(), usuario);
	}

	public void deleteUsuario(Usuario usuario) {
	}
}