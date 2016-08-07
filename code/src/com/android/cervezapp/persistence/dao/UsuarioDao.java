package com.android.cervezapp.persistence.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import android.content.Context;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.persistence.adapter.UsuarioDataBaseAdapter;

public class UsuarioDao implements Serializable {

	private static final long serialVersionUID = 1L;
	//private Context context;
	private static UsuarioDao instance;
	private UsuarioDataBaseAdapter usuarioDataBaseAdapter;
	//private Map<Long, Usuario> mapa = new HashMap<Long, Usuario>();


	private UsuarioDao() {
	}
	
//	private UsuarioDao(Context context) {
//		this.context = context;
//	}
	
	public static UsuarioDao getInstance() {
		return UsuarioDao.instance;
	}

	public static UsuarioDao getInstance(Context context) {
		if ( instance == null ) {
			instance = new UsuarioDao();
			instance.usuarioDataBaseAdapter = UsuarioDataBaseAdapter.getInstance(context);
		}
		return null;
	}
	
	public List<Usuario> getAllUsuarios() throws ParseException {
		//return new ArrayList<Usuario>(this.mapa.values());
		return this.usuarioDataBaseAdapter.obtenerTodos();
	}

	public Usuario getById(Long id) throws ParseException {
		//return this.mapa.get(id);
		return this.usuarioDataBaseAdapter.buscar(id);
	}

	public void saveUsuario(Usuario usuario) {
		//this.mapa.put(usuario.getId(), usuario);
		this.usuarioDataBaseAdapter.agregar(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		//this.mapa.put(usuario.getId(), usuario);
		this.usuarioDataBaseAdapter.modificar(usuario);
	}

	public void deleteUsuario(Usuario usuario) {
		//this.mapa.remove(usuario.getId());
		this.usuarioDataBaseAdapter.eliminar(usuario);
	}
	
}