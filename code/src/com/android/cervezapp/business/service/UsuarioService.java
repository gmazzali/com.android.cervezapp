package com.android.cervezapp.business.service;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.persistence.adapter.UsuarioDataBaseAdapter;
import com.android.cervezapp.persistence.dao.UsuarioDao;
import com.android.cervezapp.persistence.util.IdGenerator;

/**
 * @author Billy
 */
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	private Context context;
	private static UsuarioService instance;
	private UsuarioDao usuarioDao = UsuarioDao.getInstance(this.context);

	private UsuarioService() {
	}

	private UsuarioService(Context context) {
		this.context = context;
	}
	
	public static UsuarioService getInstance() {
		return UsuarioService.instance;
	}
	
	public static UsuarioService getInstance(Context context) {
		if ( instance == null ) {
			instance = new UsuarioService(context);
		}
		return null;
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