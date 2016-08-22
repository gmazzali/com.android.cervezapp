package com.android.cervezapp.business.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.persistence.dao.UsuarioDao;
import com.android.cervezapp.persistence.util.IdGenerator;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	// private Context context;
	private static UsuarioService instance;
	private UsuarioDao usuarioDao;

	public static UsuarioService getInstance(Context context) {
		if (instance == null) {
			instance = new UsuarioService();
			instance.usuarioDao = UsuarioDao.getInstance(context);
		}
		return null;
	}

	public Usuario setUpUsuario(Long idUsuario, Bitmap fotoDefault) {
		Usuario usuario;
		try {
			usuario = this.usuarioDao.getById(idUsuario);
			if (usuario == null) {
				usuario = new Usuario();
				usuario.setId(idUsuario);
				usuario.setNombre(null);
				usuario.setApellido(null);
				usuario.setEmail(null);
				usuario.setFoto(BitmapUtility.getBytes(fotoDefault));
				this.usuarioDao.saveUsuario(usuario);
			}
			return usuario;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Usuario> getAllUsuarios() {
		try {
			return this.usuarioDao.getAllUsuarios();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ArrayList<Usuario>();
	}

	public Usuario getById(Long id) {
		try {
			return this.usuarioDao.getById(id);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveUsuario(Usuario usuario) {
		usuario.setId(IdGenerator.getNextId(Usuario.class));
		this.usuarioDao.saveUsuario(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		this.usuarioDao.updateUsuario(usuario);
	}

	public void deleteUsuario(Usuario usuario) {
		this.usuarioDao.deleteUsuario(usuario);
	}
}