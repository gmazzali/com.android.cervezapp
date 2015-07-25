package com.android.cervezapp.persistence.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonaDataBaseHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "db_cervezapp";
	public static final String TABLE_NAME = "persona";
	public static final int DB_VERSION = 1;
	
	public static final String CAMPO_ID = "_id";
	public static final String CAMPO_USER = "user";
	public static final String CAMPO_APELLIDO = "apellido";
	public static final String CAMPO_NOMBRE = "nombre";
	public static final String CAMPO_SEXO = "sexo";
	public static final String CAMPO_FECHA_NACIMIENTO = "fec_nac";
	public static final String CAMPO_EMAIL = "email";
	public static final String CAMPO_TELEFONO = "telefono";
	public static final String CAMPO_FUMADOR = "fumador";
	public static final String CAMPO_FOTO = "foto";
	
	private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
			CAMPO_ID + " integer not null primary key autoincrement," +
			CAMPO_USER + " text not null," +
			CAMPO_APELLIDO + " text not null," +
			CAMPO_NOMBRE + " text not null," +
			CAMPO_SEXO + " varchar not null," +
			CAMPO_FECHA_NACIMIENTO + " date not null," +
			CAMPO_EMAIL + " text," +
			CAMPO_TELEFONO + " text, " +
			CAMPO_FUMADOR + " boolean, " +
			CAMPO_FOTO + "blob)";
	
	public PersonaDataBaseHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL(DROP_TABLE);
		db.execSQL(CREATE_TABLE);
	}
	
}
