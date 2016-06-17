package com.android.cervezapp.persistence.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.android.cervezapp.persistence.helper.UsuarioDataBaseHelper;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.SexoEnum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * @author Martin
 */
public class UsuarioDataBaseAdapter {
	private Context context;
	private UsuarioDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
	private static UsuarioDataBaseAdapter instance;
	
	
	public UsuarioDataBaseAdapter(Context context) {
		this.context = context;
	}
	
	public static UsuarioDataBaseAdapter getInstance(Context context) {
		if ( instance == null ) {
			instance = new UsuarioDataBaseAdapter(context);
		}
		return instance;
	}
	
	public UsuarioDataBaseAdapter abrir() throws SQLiteException {
		dbHelper = new UsuarioDataBaseHelper(context);
		db = dbHelper.getWritableDatabase();
		
		return this;
	}

	public void cerrar() {
		if (db != null) {
			db.close();
		}
	}

	public long agregar(Usuario usuario) {
		ContentValues valores = new ContentValues();
		// DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

		valores.put(UsuarioDataBaseHelper.CAMPO_USER, usuario.getUserName());
		valores.put(UsuarioDataBaseHelper.CAMPO_APELLIDO, usuario.getApellido());
		valores.put(UsuarioDataBaseHelper.CAMPO_NOMBRE, usuario.getNombre());
		valores.put(UsuarioDataBaseHelper.CAMPO_SEXO, usuario.getSexo()
				.toString());
		valores.put(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO,
				fecha.format(usuario.getFechaNacimiento()));
		valores.put(UsuarioDataBaseHelper.CAMPO_EMAIL, usuario.getEmail());
		valores.put(UsuarioDataBaseHelper.CAMPO_TELEFONO, usuario.getTelefono());
		valores.put(UsuarioDataBaseHelper.CAMPO_FUMADOR, usuario.getFumador());

		/**
		 * FALTA EL MÉTODO PARA INGRESAR LA FOTO..¿QUÉ FOTO?..JEJEJE
		 */

		return db.insert(UsuarioDataBaseHelper.TABLE_NAME, null, valores);
	}

	public void modificar(Usuario usuario) {
		String[] argumentos = new String[] { String.valueOf(usuario.getId()) };
		ContentValues valores = new ContentValues();
		// DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

		valores.put(UsuarioDataBaseHelper.CAMPO_USER, usuario.getUserName());
		valores.put(UsuarioDataBaseHelper.CAMPO_APELLIDO, usuario.getApellido());
		valores.put(UsuarioDataBaseHelper.CAMPO_NOMBRE, usuario.getNombre());
		valores.put(UsuarioDataBaseHelper.CAMPO_SEXO, usuario.getSexo()
				.toString());
		valores.put(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO,
				fecha.format(usuario.getFechaNacimiento()));
		valores.put(UsuarioDataBaseHelper.CAMPO_EMAIL, usuario.getEmail());
		valores.put(UsuarioDataBaseHelper.CAMPO_TELEFONO, usuario.getTelefono());
		valores.put(UsuarioDataBaseHelper.CAMPO_FUMADOR, usuario.getFumador());

		db.update(UsuarioDataBaseHelper.TABLE_NAME, valores,
				UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos);
	}

	public void eliminar(Usuario usuario) {
		String[] argumentos = new String[] { String.valueOf(usuario.getId()) };

		db.delete(UsuarioDataBaseHelper.TABLE_NAME,
				UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos);
	}

	public Cursor obtenerTodos() {
		String[] campos = { UsuarioDataBaseHelper.CAMPO_ID,
				UsuarioDataBaseHelper.CAMPO_USER,
				UsuarioDataBaseHelper.CAMPO_APELLIDO,
				UsuarioDataBaseHelper.CAMPO_NOMBRE,
				UsuarioDataBaseHelper.CAMPO_SEXO,
				UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO,
				UsuarioDataBaseHelper.CAMPO_EMAIL,
				UsuarioDataBaseHelper.CAMPO_TELEFONO,
				UsuarioDataBaseHelper.CAMPO_FUMADOR,
				UsuarioDataBaseHelper.CAMPO_FOTO };

		Cursor resultado = db.query(UsuarioDataBaseHelper.TABLE_NAME, campos,
				null, null, null, null, null);

		if (resultado != null) {
			resultado.moveToFirst();
		}

		return resultado;
	}

	public Usuario buscar(int id) throws ParseException {
		Usuario usuario = null;

		String[] campos = { UsuarioDataBaseHelper.CAMPO_ID,
				UsuarioDataBaseHelper.CAMPO_USER,
				UsuarioDataBaseHelper.CAMPO_APELLIDO,
				UsuarioDataBaseHelper.CAMPO_NOMBRE,
				UsuarioDataBaseHelper.CAMPO_SEXO,
				UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO,
				UsuarioDataBaseHelper.CAMPO_EMAIL,
				UsuarioDataBaseHelper.CAMPO_TELEFONO,
				UsuarioDataBaseHelper.CAMPO_FUMADOR,
				UsuarioDataBaseHelper.CAMPO_FOTO };

		String[] argumentos = { String.valueOf(id) };

		Cursor resultado = db.query(UsuarioDataBaseHelper.TABLE_NAME, campos,
				UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos, null,
				null, null);

		if (resultado != null) {
			resultado.moveToFirst();

			usuario = new Usuario();
			usuario.setId((long) resultado.getInt(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_ID)));
			usuario.setApellido(resultado.getString(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_APELLIDO)));
			usuario.setNombre(resultado.getString(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_NOMBRE)));
			usuario.setSexo(SexoEnum.getSexoEnum(resultado.getString(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_SEXO))));
			usuario.setFechaNacimiento(fecha.parse(resultado.getString(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO))));
			usuario.setEmail(resultado.getString(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_EMAIL)));
			usuario.setTelefono(resultado.getString(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_TELEFONO)));
			usuario.setFumador(resultado.getInt(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FUMADOR)));
			usuario.setFoto(resultado.getBlob(resultado
					.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FOTO)));
		}

		return usuario;
	}

	public void limpiar() {
		db.delete(UsuarioDataBaseHelper.TABLE_NAME, null, null);
	}

	public void beginTransaction() {
		if (db != null) {
			db.beginTransaction();
		}
	}

	public void flush() {
		if (db != null) {
			db.setTransactionSuccessful();
		}
	}

	public void commit() {
		if (db != null) {
			db.endTransaction();
		}
	}
}
