package com.android.cervezapp.business.service;

import java.io.Serializable;
import java.util.List;

import android.graphics.Bitmap;

import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Comentario;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.persistence.dao.ComentarioDao;
import com.android.cervezapp.persistence.dao.UsuarioDao;
import com.android.cervezapp.persistence.util.IdGenerator;

/**
 * @author John
 */
public class ComentarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ComentarioService instance = new ComentarioService();

	public static ComentarioService getInstance() {
		return ComentarioService.instance;
	}

	private ComentarioDao comentarioDao = ComentarioDao.getInstance();

	private ComentarioService() {
	}

	/**
	 * Se llama al inicio de la aplicación.
	 */
//	public Usuario setUpUsuario(Long idUsuario, Bitmap fotoDefault) {
//		Usuario usuario = this.usuarioDao.getById(idUsuario);
//		if (usuario == null) {
//			usuario = new Usuario();
//			usuario.setId(idUsuario);
//			usuario.setNombre(null);
//			usuario.setApellido(null);
//			usuario.setEmail(null);
//			usuario.setFoto(BitmapUtility.getBytes(fotoDefault));
//			this.usuarioDao.saveUsuario(usuario);
//		}
//		return usuario;
//	}

	public List<Comentario> getAllComentarios() {
		return this.comentarioDao.getAllComentarios();
	}

	public Comentario getById(Long id) {
		return this.comentarioDao.getById(id);
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