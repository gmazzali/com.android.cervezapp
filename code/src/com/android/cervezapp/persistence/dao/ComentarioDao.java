package com.android.cervezapp.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.domain.model.Comentario;

/**
 * @author John
 */
@SuppressLint("UseSparseArrays")
public class ComentarioDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private static ComentarioDao instance;

	private Map<Long, Comentario> mapa = new HashMap<Long, Comentario>();

	// TODO Terminar el dao de comentarios
	public static ComentarioDao getInstance(Context context) {
		if (instance == null) {
			instance = new ComentarioDao();
			// instance.barDataBaseAdapter = BarDataBaseAdapter.getInstance(context);
		}
		return instance;
	}

	private ComentarioDao() {
	}

	public List<Comentario> getAllComentarios() {
		return new ArrayList<Comentario>(this.mapa.values());
	}

	public Comentario getById(Long id) {
		return this.mapa.get(id);
	}

	// Se usa para cargar los comentarios a cada bar!
	public List<Comentario> getByBar(Bar bar) {
		List<Comentario> comentarios = new ArrayList<Comentario>();
		for (Comentario comentario : this.mapa.values()) {
			if (bar.getId().equals(comentario.getBarId())) {
				comentarios.add(comentario);
			}
		}
		return comentarios;
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