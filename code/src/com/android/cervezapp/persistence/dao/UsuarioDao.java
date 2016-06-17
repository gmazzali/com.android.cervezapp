package com.android.cervezapp.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.cervezapp.business.service.UsuarioService;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.persistence.adapter.UsuarioDataBaseAdapter;

/**
 * @author Billy
 */
public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private Context context;
	private static UsuarioDao instance;
	private UsuarioDataBaseAdapter usuarioDataBaseAdapter = UsuarioDataBaseAdapter.getInstance(context);
	//private Map<Long, Usuario> mapa = new HashMap<Long, Usuario>();


	private UsuarioDao() {
	}
	
	private UsuarioDao(Context context) {
		this.context = context;
	}
	
	public static UsuarioDao getInstance() {
		return UsuarioDao.instance;
	}

	public static UsuarioDao getInstance(Context context) {
		if ( instance == null ) {
			instance = new UsuarioDao(context);
		}
		return null;
	}
	
	public List<Usuario> getAllUsuarios() {
		return new ArrayList<Usuario>(this.mapa.values());
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
		this.mapa.remove(usuario.getId());
	}
	
}