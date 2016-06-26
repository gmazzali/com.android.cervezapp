package com.android.cervezapp.persistence.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.persistence.helper.BarDataBaseHelper;

public class BarDataBaseAdapter {

	private static BarDataBaseAdapter instance;

	private Context context;

	private BarDataBaseHelper dbHelper;

	private SQLiteDatabase db;

	public static BarDataBaseAdapter getInstance(Context context) {
		if (instance == null) {
			instance = new BarDataBaseAdapter(context);
		}
		return instance;
	}

	public BarDataBaseAdapter(Context context) {
		this.context = context;
	}

	public BarDataBaseAdapter abrir() throws SQLiteException {
		this.dbHelper = new BarDataBaseHelper(this.context);
		this.db = this.dbHelper.getWritableDatabase();
		return this;
	}

	public void cerrar() {
		if (this.db != null) {
			this.db.close();
		}
	}

	public void limpiar() {
		this.db.delete(BarDataBaseHelper.TABLE_NAME, null, null);
	}

	public void beginTransaction() {
		if (this.db != null) {
			this.db.beginTransaction();
		}
	}

	public void flush() {
		if (this.db != null) {
			this.db.setTransactionSuccessful();
		}
	}

	public void commit() {
		if (this.db != null) {
			this.db.endTransaction();
		}
	}

	public long agregar(Bar bar) {
		ContentValues valores = new ContentValues();
		valores.put(BarDataBaseHelper.CAMPO_NOMBRE, bar.getNombre());
		valores.put(BarDataBaseHelper.CAMPO_DIRECCION, bar.getDireccion());
		valores.put(BarDataBaseHelper.CAMPO_EMAIL, bar.getEmail());
		valores.put(BarDataBaseHelper.CAMPO_TELEFONO, bar.getTelefono());
		valores.put(BarDataBaseHelper.CAMPO_FUMADOR, bar.getFumador());
		valores.put(BarDataBaseHelper.CAMPO_FOTO, bar.getFoto());
		valores.put(BarDataBaseHelper.CAMPO_LATITUD, bar.getLatitud());
		valores.put(BarDataBaseHelper.CAMPO_LONGITUD, bar.getLongitud());
		return this.db.insert(BarDataBaseHelper.TABLE_NAME, null, valores);
	}

	public void modificar(Bar bar) {
		String[] argumentos = new String[] { String.valueOf(bar.getId()) };
		ContentValues valores = new ContentValues();
		valores.put(BarDataBaseHelper.CAMPO_NOMBRE, bar.getNombre());
		valores.put(BarDataBaseHelper.CAMPO_DIRECCION, bar.getDireccion());
		valores.put(BarDataBaseHelper.CAMPO_EMAIL, bar.getEmail());
		valores.put(BarDataBaseHelper.CAMPO_TELEFONO, bar.getTelefono());
		valores.put(BarDataBaseHelper.CAMPO_FUMADOR, bar.getFumador().toString());
		valores.put(BarDataBaseHelper.CAMPO_FOTO, bar.getFoto());
		valores.put(BarDataBaseHelper.CAMPO_LATITUD, bar.getLatitud());
		valores.put(BarDataBaseHelper.CAMPO_LONGITUD, bar.getLongitud());
		this.db.update(BarDataBaseHelper.TABLE_NAME, valores, BarDataBaseHelper.CAMPO_ID + " = ?", argumentos);
	}

	public void eliminar(Bar bar) {
		String[] argumentos = new String[] { String.valueOf(bar.getId()) };
		this.db.delete(BarDataBaseHelper.TABLE_NAME, BarDataBaseHelper.CAMPO_ID + " = ?", argumentos);
	}

	public List<Bar> obtenerTodos() {
		String[] campos = { BarDataBaseHelper.CAMPO_NOMBRE, BarDataBaseHelper.CAMPO_DIRECCION, BarDataBaseHelper.CAMPO_EMAIL, BarDataBaseHelper.CAMPO_TELEFONO,
				BarDataBaseHelper.CAMPO_FUMADOR, BarDataBaseHelper.CAMPO_FOTO, BarDataBaseHelper.CAMPO_LATITUD, BarDataBaseHelper.CAMPO_LONGITUD };
		Cursor resultado = this.db.query(BarDataBaseHelper.TABLE_NAME, campos, null, null, null, null, null);
		List<Bar> bares = new ArrayList<Bar>();
		if (resultado != null) {
			resultado.moveToFirst();
			while (resultado.moveToNext()) {
				Bar bar = new Bar();
				bar.setId((long) resultado.getInt(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_ID)));
				bar.setNombre(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_NOMBRE)));
				bar.setDireccion(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_DIRECCION)));
				bar.setEmail(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_EMAIL)));
				bar.setTelefono(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_TELEFONO)));
				bar.setFumador(Boolean.valueOf(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_FUMADOR))));
				bar.setFoto(resultado.getBlob(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_FOTO)));
				bar.setLatitud(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_LATITUD)));
				bar.setLongitud(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_LONGITUD)));
				bares.add(bar);
			}
		}
		return bares;
	}

	public Bar buscar(Long id) {
		Bar bar = null;
		String[] campos = { BarDataBaseHelper.CAMPO_NOMBRE, BarDataBaseHelper.CAMPO_DIRECCION, BarDataBaseHelper.CAMPO_EMAIL, BarDataBaseHelper.CAMPO_TELEFONO,
				BarDataBaseHelper.CAMPO_FUMADOR, BarDataBaseHelper.CAMPO_FOTO, BarDataBaseHelper.CAMPO_LATITUD, BarDataBaseHelper.CAMPO_LONGITUD };
		String[] argumentos = { String.valueOf(id) };

		Cursor resultado = this.db.query(BarDataBaseHelper.TABLE_NAME, campos, BarDataBaseHelper.CAMPO_ID + " = ?", argumentos, null, null, null);

		if (resultado != null) {
			resultado.moveToFirst();
			bar = new Bar();
			bar.setId((long) resultado.getInt(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_ID)));
			bar.setNombre(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_NOMBRE)));
			bar.setDireccion(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_DIRECCION)));
			bar.setEmail(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_EMAIL)));
			bar.setTelefono(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_TELEFONO)));
			bar.setFumador(Boolean.valueOf(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_FUMADOR))));
			bar.setFoto(resultado.getBlob(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_FOTO)));
			bar.setLatitud(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_LATITUD)));
			bar.setLongitud(resultado.getString(resultado.getColumnIndex(BarDataBaseHelper.CAMPO_LONGITUD)));
		}
		return bar;
	}
}