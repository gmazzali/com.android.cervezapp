package com.android.cervezapp.business.service;

import java.io.Serializable;
import java.util.List;

import android.content.Context;

import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.domain.model.Comentario;
import com.android.cervezapp.persistence.dao.ComentarioDao;
import com.android.cervezapp.persistence.util.IdGenerator;

/**
 * @author John
 */
public class ComentarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ComentarioService instance;

	private ComentarioDao comentarioDao;

	public static ComentarioService getInstance(Context context) {
		if (instance == null) {
			instance = new ComentarioService();
			instance.comentarioDao = ComentarioDao.getInstance(context);
		}
		return instance;
	}

	private ComentarioService() {
	}

	/**
	 * Se llama al inicio de la aplicación.
	 */
	// public Usuario setUpUsuario(Long idUsuario, Bitmap fotoDefault) {
	// Usuario usuario = this.usuarioDao.getById(idUsuario);
	// if (usuario == null) {
	// usuario = new Usuario();
	// usuario.setId(idUsuario);
	// usuario.setNombre(null);
	// usuario.setApellido(null);
	// usuario.setEmail(null);
	// usuario.setFoto(BitmapUtility.getBytes(fotoDefault));
	// this.usuarioDao.saveUsuario(usuario);
	// }
	// return usuario;
	// }

	public List<Comentario> getAllComentarios() {
		return this.comentarioDao.getAllComentarios();
	}

	public Comentario getById(Long id) {
		return this.comentarioDao.getById(id);
	}

	// Se usa para cargar los comentarios a cada bar!
	public List<Comentario> getByBar(Bar bar) {
		return this.comentarioDao.getByBar(bar);
	}

	public void saveComentario(Comentario comentario) {
		comentario.setId(IdGenerator.getNextId(Comentario.class));
		this.comentarioDao.saveComentario(comentario);
	}

	public void updateComentario(Comentario comentario) {
		this.comentarioDao.updateComentario(comentario);
	}

	public void deleteComentario(Comentario comentario) {
		this.comentarioDao.deleteComentario(comentario);
	}
}