package com.android.cervezapp.persistence.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.persistence.adapter.UsuarioDataBaseAdapter;
import com.android.cervezapp.persistence.util.IdGenerator;

import android.content.Context;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private static UsuarioDao instance;

	private UsuarioDataBaseAdapter usuarioDataBaseAdapter;

	public static UsuarioDao getInstance(Context context) {
		if (instance == null) {
			instance = new UsuarioDao();
			instance.usuarioDataBaseAdapter = UsuarioDataBaseAdapter.getInstance(context);
		}
		return instance;
	}

	private UsuarioDao() {
	}

	public List<Usuario> getAllUsuarios() throws ParseException {
		return this.usuarioDataBaseAdapter.getAllUsuarios();
	}

	public Usuario getById(Long id) throws ParseException {
		return this.usuarioDataBaseAdapter.getById(id);
	}

	public void saveUsuario(Usuario usuario) {
		usuario.setId(IdGenerator.getNextId(Usuario.class));
		this.usuarioDataBaseAdapter.saveUsuario(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		this.usuarioDataBaseAdapter.updateUsuario(usuario);
	}

	public void deleteUsuario(Usuario usuario) {
		this.usuarioDataBaseAdapter.deleteUsuario(usuario);
	}
}