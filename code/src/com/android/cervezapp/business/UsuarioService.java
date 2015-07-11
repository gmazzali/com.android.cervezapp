package com.android.cervezapp.business;

import java.io.Serializable;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.android.cervezapp.R;
import com.android.cervezapp.domain.model.Usuario;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static UsuarioService service = new UsuarioService();

	public static UsuarioService getInstance() {
		return UsuarioService.service;
	}

	private Usuario usuario;

	private UsuarioService() {
	}

	public Usuario setUpDefaultUsuario(Resources resources) {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setFoto(BitmapFactory.decodeResource(resources, R.drawable.leprechaun_drunk_icon));
		}
		return usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	

	public Usuario getById(Long id) {
		return null;
	}

	public void saveUsuario(Usuario usuario) {
	}

	public void updateUsuario(Usuario usuario) {
	}

	public void deleteUsuario(Usuario usuario) {
	}
}