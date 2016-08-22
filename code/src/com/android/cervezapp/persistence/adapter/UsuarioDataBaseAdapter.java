package com.android.cervezapp.persistence.adapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.SexoEnum;
import com.android.cervezapp.persistence.helper.UsuarioDataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDataBaseAdapter extends AbstractDataBaseAdapter {

	private static final long serialVersionUID = 1L;

	private static UsuarioDataBaseAdapter instance;

	public static UsuarioDataBaseAdapter getInstance(Context context) {
		if (instance == null) {
			instance = new UsuarioDataBaseAdapter(context);
		}
		return instance;
	}

	private UsuarioDataBaseAdapter(Context context) {
		super(context);
	}

	@Override
	protected SQLiteOpenHelper getSQLiteHelper(Context context) {
		return new UsuarioDataBaseHelper(context);
	}

	public long saveUsuario(Usuario usuario) {
		ContentValues valores = new ContentValues();
		valores.put(UsuarioDataBaseHelper.CAMPO_USER, usuario.getUserName());
		valores.put(UsuarioDataBaseHelper.CAMPO_APELLIDO, usuario.getApellido());
		valores.put(UsuarioDataBaseHelper.CAMPO_NOMBRE, usuario.getNombre());
		valores.put(UsuarioDataBaseHelper.CAMPO_SEXO, usuario.getSexo().toString());
		valores.put(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO, DATE_FORMAT.format(usuario.getFechaNacimiento()));
		valores.put(UsuarioDataBaseHelper.CAMPO_EMAIL, usuario.getEmail());
		valores.put(UsuarioDataBaseHelper.CAMPO_TELEFONO, usuario.getTelefono());
		valores.put(UsuarioDataBaseHelper.CAMPO_FUMADOR, usuario.getFumador());
		valores.put(UsuarioDataBaseHelper.CAMPO_FOTO, usuario.getFoto());

		this.open();
		this.begin();
		long valor = this.insert(UsuarioDataBaseHelper.TABLE_NAME, valores);
		this.commit();

		return valor;
	}

	public void updateUsuario(Usuario usuario) {
		String[] argumentos = new String[] { String.valueOf(usuario.getId()) };

		ContentValues valores = new ContentValues();
		valores.put(UsuarioDataBaseHelper.CAMPO_USER, usuario.getUserName());
		valores.put(UsuarioDataBaseHelper.CAMPO_APELLIDO, usuario.getApellido());
		valores.put(UsuarioDataBaseHelper.CAMPO_NOMBRE, usuario.getNombre());
		valores.put(UsuarioDataBaseHelper.CAMPO_SEXO, usuario.getSexo().toString());
		valores.put(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO, DATE_FORMAT.format(usuario.getFechaNacimiento()));
		valores.put(UsuarioDataBaseHelper.CAMPO_EMAIL, usuario.getEmail());
		valores.put(UsuarioDataBaseHelper.CAMPO_TELEFONO, usuario.getTelefono());
		valores.put(UsuarioDataBaseHelper.CAMPO_FUMADOR, usuario.getFumador());
		valores.put(UsuarioDataBaseHelper.CAMPO_FOTO, usuario.getFoto());

		this.open();
		this.begin();
		this.update(UsuarioDataBaseHelper.TABLE_NAME, valores, UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos);
		this.commit();
	}

	public void deleteUsuario(Usuario usuario) {
		String[] argumentos = new String[] { String.valueOf(usuario.getId()) };

		this.open();
		this.begin();
		this.delete(UsuarioDataBaseHelper.TABLE_NAME, UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos);
		this.commit();
	}

	public List<Usuario> getAllUsuarios() throws ParseException {
		String[] campos = { UsuarioDataBaseHelper.CAMPO_ID, UsuarioDataBaseHelper.CAMPO_USER, UsuarioDataBaseHelper.CAMPO_APELLIDO, UsuarioDataBaseHelper.CAMPO_NOMBRE,
				UsuarioDataBaseHelper.CAMPO_SEXO, UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO, UsuarioDataBaseHelper.CAMPO_EMAIL, UsuarioDataBaseHelper.CAMPO_TELEFONO,
				UsuarioDataBaseHelper.CAMPO_FUMADOR, UsuarioDataBaseHelper.CAMPO_FOTO };

		this.open();
		this.begin();
		Cursor resultado = this.query(UsuarioDataBaseHelper.TABLE_NAME, campos, null, null);
		this.commit();

		List<Usuario> usuarios = new ArrayList<Usuario>();
		if (resultado != null) {
			resultado.moveToFirst();
			while (resultado.moveToNext()) {
				Usuario usuario = new Usuario();
				usuario.setId((long) resultado.getInt(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_ID)));
				usuario.setUserName(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_USER)));
				usuario.setNombre(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_NOMBRE)));
				usuario.setApellido(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_APELLIDO)));
				usuario.setSexo(SexoEnum.getSexoEnum(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_SEXO))));
				usuario.setFechaNacimiento(DATE_FORMAT.parse(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO))));
				usuario.setTelefono(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_TELEFONO)));
				usuario.setFumador(resultado.getInt(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FUMADOR)));
				usuario.setFoto(resultado.getBlob(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FOTO)));
				usuarios.add(usuario);
			}
		}
		return usuarios;
	}

	public Usuario getById(Long id) throws ParseException {
		String[] campos = { UsuarioDataBaseHelper.CAMPO_ID, UsuarioDataBaseHelper.CAMPO_USER, UsuarioDataBaseHelper.CAMPO_APELLIDO, UsuarioDataBaseHelper.CAMPO_NOMBRE,
				UsuarioDataBaseHelper.CAMPO_SEXO, UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO, UsuarioDataBaseHelper.CAMPO_EMAIL, UsuarioDataBaseHelper.CAMPO_TELEFONO,
				UsuarioDataBaseHelper.CAMPO_FUMADOR, UsuarioDataBaseHelper.CAMPO_FOTO };

		String[] argumentos = { String.valueOf(id) };

		this.open();
		this.begin();
		Cursor resultado = this.query(UsuarioDataBaseHelper.TABLE_NAME, campos, UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos);
		this.commit();

		Usuario usuario = null;
		if (resultado != null) {
			resultado.moveToFirst();
			usuario = new Usuario();
			usuario.setId((long) resultado.getInt(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_ID)));
			usuario.setApellido(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_APELLIDO)));
			usuario.setNombre(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_NOMBRE)));
			usuario.setSexo(SexoEnum.getSexoEnum(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_SEXO))));
			usuario.setFechaNacimiento(DATE_FORMAT.parse(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO))));
			usuario.setEmail(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_EMAIL)));
			usuario.setTelefono(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_TELEFONO)));
			usuario.setFumador(resultado.getInt(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FUMADOR)));
			usuario.setFoto(resultado.getBlob(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FOTO)));
		}
		return usuario;
	}
}