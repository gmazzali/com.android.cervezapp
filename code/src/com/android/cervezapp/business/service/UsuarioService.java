package com.android.cervezapp.business.service;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;

import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.persistence.dao.UsuarioDao;
import com.android.cervezapp.persistence.util.IdGenerator;

/**
 * @author Billy
 */
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static UsuarioService instance = new UsuarioService();

	public static UsuarioService getInstance() {
		return UsuarioService.instance;
	}

	private UsuarioDao usuarioDao = UsuarioDao.getInstance();

	private UsuarioService() {
	}

	/**
	 * Se llama al inicio de la aplicación.
	 */
	public Usuario setUpUsuario(Long idUsuario, Bitmap fotoDefault) {
		Usuario usuario = this.usuarioDao.getById(idUsuario);
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
	}

	public List<Usuario> getAllUsuarios() {
		return this.usuarioDao.getAllUsuarios();
	}

	public Usuario getById(Long id) {
		return this.usuarioDao.getById(id);
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