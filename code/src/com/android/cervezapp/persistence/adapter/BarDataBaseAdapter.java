package com.android.cervezapp.persistence.adapter;

import java.util.ArrayList;
import java.util.List;

import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.persistence.helper.BarDataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class BarDataBaseAdapter extends AbstractDataBaseAdapter {

	private static final long serialVersionUID = 1L;

	private static BarDataBaseAdapter instance;

	public static BarDataBaseAdapter getInstance(Context context) {
		if (instance == null) {
			instance = new BarDataBaseAdapter(context);
		}
		return instance;
	}

	private BarDataBaseAdapter(Context context) {
		super(context);
	}

	@Override
	protected SQLiteOpenHelper getSQLiteHelper(Context context) {
		return new BarDataBaseHelper(context);
	}

	public long saveBar(Bar bar) {
		ContentValues valores = new ContentValues();
		valores.put(BarDataBaseHelper.CAMPO_NOMBRE, bar.getNombre());
		valores.put(BarDataBaseHelper.CAMPO_DIRECCION, bar.getDireccion());
		valores.put(BarDataBaseHelper.CAMPO_EMAIL, bar.getEmail());
		valores.put(BarDataBaseHelper.CAMPO_TELEFONO, bar.getTelefono());
		valores.put(BarDataBaseHelper.CAMPO_FUMADOR, bar.getFumador());
		valores.put(BarDataBaseHelper.CAMPO_FOTO, bar.getFoto());
		valores.put(BarDataBaseHelper.CAMPO_LATITUD, bar.getLatitud());
		valores.put(BarDataBaseHelper.CAMPO_LONGITUD, bar.getLongitud());

		this.open();
		this.begin();
		long valor = this.insert(BarDataBaseHelper.TABLE_NAME, valores);
		this.commit();

		return valor;
	}

	public void updateBar(Bar bar) {
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

		this.open();
		this.begin();
		this.update(BarDataBaseHelper.TABLE_NAME, valores, BarDataBaseHelper.CAMPO_ID + " = ?", argumentos);
		this.commit();
	}

	public void deleteBar(Bar bar) {
		String[] argumentos = new String[] { String.valueOf(bar.getId()) };

		this.open();
		this.begin();
		this.delete(BarDataBaseHelper.TABLE_NAME, BarDataBaseHelper.CAMPO_ID + " = ?", argumentos);
		this.commit();
	}

	public List<Bar> getAllBares() {
		String[] campos = { BarDataBaseHelper.CAMPO_NOMBRE, BarDataBaseHelper.CAMPO_DIRECCION, BarDataBaseHelper.CAMPO_EMAIL, BarDataBaseHelper.CAMPO_TELEFONO,
				BarDataBaseHelper.CAMPO_FUMADOR, BarDataBaseHelper.CAMPO_FOTO, BarDataBaseHelper.CAMPO_LATITUD, BarDataBaseHelper.CAMPO_LONGITUD };

		this.open();
		this.begin();
		Cursor resultado = this.query(BarDataBaseHelper.TABLE_NAME, campos, null, null);
		this.commit();

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

	public Bar getById(Long id) {
		String[] campos = { BarDataBaseHelper.CAMPO_NOMBRE, BarDataBaseHelper.CAMPO_DIRECCION, BarDataBaseHelper.CAMPO_EMAIL, BarDataBaseHelper.CAMPO_TELEFONO,
				BarDataBaseHelper.CAMPO_FUMADOR, BarDataBaseHelper.CAMPO_FOTO, BarDataBaseHelper.CAMPO_LATITUD, BarDataBaseHelper.CAMPO_LONGITUD };

		String[] argumentos = { String.valueOf(id) };

		this.open();
		this.begin();
		Cursor resultado = this.query(BarDataBaseHelper.TABLE_NAME, campos, BarDataBaseHelper.CAMPO_ID + " = ?", argumentos);
		this.commit();

		Bar bar = null;
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