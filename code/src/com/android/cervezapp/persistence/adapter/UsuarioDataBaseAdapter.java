package com.android.cervezapp.persistence.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.android.cervezapp.persistence.helper.UsuarioDataBaseHelper;
import com.android.cervezapp.domain.model.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class UsuarioDataBaseAdapter
{
	private Context context;
	private UsuarioDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	
	public UsuarioDataBaseAdapter(Context context)
	{
		this.context = context;
	}
	
	public UsuarioDataBaseAdapter abrir() throws SQLiteException
	{
		dbHelper = new UsuarioDataBaseHelper(context);
		db = dbHelper.getWritableDatabase();
		
		return this;
	}
	
	public void cerrar()
	{
		if ( db != null )
		{
			db.close();
		}
	}
	
	public long agregar(Usuario usuario)
	{
		ContentValues valores = new ContentValues();
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		
		valores.put(UsuarioDataBaseHelper.CAMPO_USER, usuario.getUserName());
		valores.put(UsuarioDataBaseHelper.CAMPO_APELLIDO, usuario.getApellido());
		valores.put(UsuarioDataBaseHelper.CAMPO_NOMBRE, usuario.getNombre());
		valores.put(UsuarioDataBaseHelper.CAMPO_SEXO, usuario.getSexo().toString());
		valores.put(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO, fecha.format(usuario.getFechaNacimiento()));
		valores.put(UsuarioDataBaseHelper.CAMPO_EMAIL, usuario.getEmail());
		valores.put(UsuarioDataBaseHelper.CAMPO_TELEFONO, usuario.getTelefono());
		if(usuario.isFumador())
			valores.put(UsuarioDataBaseHelper.CAMPO_FUMADOR, 1);
		else
			valores.put(UsuarioDataBaseHelper.CAMPO_FUMADOR, 0);
		
		/**
		 * FALTA EL M�TODO PARA INGRESAR LA FOTO..�QU� FOTO?..JEJEJE
		 */
		
		return db.insert(UsuarioDataBaseHelper.TABLE_NAME, null, valores);
	}
	
	public void modificar(Usuario usuario)
	{
		String[] argumentos = new String[]{String.valueOf(usuario.getId())};
		ContentValues valores = new ContentValues();
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		
		valores.put(UsuarioDataBaseHelper.CAMPO_USER, usuario.getUserName());
		valores.put(UsuarioDataBaseHelper.CAMPO_APELLIDO, usuario.getApellido());
		valores.put(UsuarioDataBaseHelper.CAMPO_NOMBRE, usuario.getNombre());
		valores.put(UsuarioDataBaseHelper.CAMPO_SEXO, usuario.getSexo().toString());
		valores.put(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO, fecha.format(usuario.getFechaNacimiento()));
		valores.put(UsuarioDataBaseHelper.CAMPO_EMAIL, usuario.getEmail());
		valores.put(UsuarioDataBaseHelper.CAMPO_TELEFONO, usuario.getTelefono());
		
		db.update(UsuarioDataBaseHelper.TABLE_NAME, valores, UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos);
	}
	
	public void eliminar(Usuario usuario)
	{
		String[] argumentos = new String[]{String.valueOf(usuario.getId())};
		
		db.delete(UsuarioDataBaseHelper.TABLE_NAME, UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos);
	}
	
	public Cursor obtenerTodos()
	{
		String[] campos = {UsuarioDataBaseHelper.CAMPO_ID,
			UsuarioDataBaseHelper.CAMPO_APELLIDO,
			UsuarioDataBaseHelper.CAMPO_NOMBRE,
			UsuarioDataBaseHelper.CAMPO_SEXO,
			UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO,
			UsuarioDataBaseHelper.CAMPO_EMAIL,
			UsuarioDataBaseHelper.CAMPO_TELEFONO
		};
		
		Cursor resultado = db.query(UsuarioDataBaseHelper.TABLE_NAME, campos, null, null, null, null, null);
		
		if (resultado != null)
		{
			resultado.moveToFirst();
		}
		
		return resultado;
	}
	
	public Usuario buscar(int id)
	{
		Usuario usuario = null;
		
		String[] campos = {UsuarioDataBaseHelper.CAMPO_ID,
				UsuarioDataBaseHelper.CAMPO_APELLIDO,
				UsuarioDataBaseHelper.CAMPO_NOMBRE,
				UsuarioDataBaseHelper.CAMPO_SEXO,
				UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO,
				UsuarioDataBaseHelper.CAMPO_EMAIL,
				UsuarioDataBaseHelper.CAMPO_TELEFONO
		};
		
		String[] argumentos = {String.valueOf(id)};
		
		Cursor resultado = db.query(UsuarioDataBaseHelper.TABLE_NAME, campos, UsuarioDataBaseHelper.CAMPO_ID + " = ?", argumentos, null, null, null);
		
		if (resultado != null)
		{
			resultado.moveToFirst();
			
			usuario = new Usuario();
			usuario.setId(resultado.getInt(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_ID)));
			usuario.setApellido(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_APELLIDO)));
			usuario.setNombre(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_NOMBRE)));
			usuario.setSexo(resultado.getInt(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_SEXO)));
			usuario.setFechaNacimiento(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_FECHA_NACIMIENTO)));
			usuario.setEmail(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_EMAIL)));
			usuario.setTelefono(resultado.getString(resultado.getColumnIndex(UsuarioDataBaseHelper.CAMPO_TELEFONO)));
		}
		
		return usuario;
	}

	public void limpiar()
	{
		db.delete(UsuarioDataBaseHelper.TABLE_NAME, null, null);
	}
	
	public void beginTransaction()
	{
		if ( db != null )
		{
			db.beginTransaction();
		}
	}
	
	public void flush()
	{
		if ( db != null )
		{
			db.setTransactionSuccessful();
		}
	}
	
	public void commit()
	{
		if ( db != null )
		{
			db.endTransaction();
		}
	}
}