package com.android.cervezapp.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.cervezapp.domain.model.Comentario;
import com.android.cervezapp.domain.model.Usuario;

/**
 * @author John
 */
public class ComentarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ComentarioDao instance = new ComentarioDao();

	private Map<Long, Comentario> mapa = new HashMap<Long, Comentario>();

	public static ComentarioDao getInstance() {
		return ComentarioDao.instance;
	}

	private ComentarioDao() {
	}

	public List<Comentario> getAllComentarios() {
		return new ArrayList<Comentario>(this.mapa.values());
	}

	public Comentario getById(Long id) {
		return this.mapa.get(id);
	}

	public void saveComentario(Comentario comentario) {
		this.mapa.put(comentario.getId(), comentario);
	}

	public void updateComentario(Comentario comentario) {
		this.mapa.put(comentario.getId(), comentario);
	}

	public void deleteComentario(Comentario comentario) {
		this.mapa.remove(comentario.getId());
	}
}