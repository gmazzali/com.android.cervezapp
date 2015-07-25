package com.android.cervezapp.persistence.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GustoDataBaseHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "db_cervezapp";
	public static final String TABLE_NAME = "cerveza";
	public static final int DB_VERSION = 1;
	
	public static final String CAMPO_ID = "_id";
	public static final String CAMPO_PERSONA_FK = "persona_fk";
	public static final String CAMPO_MARCA = "marca";
	public static final String CAMPO_TIPO = "tipo";
	
	private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
			CAMPO_ID + " integer not null primary key autoincrement," +
			CAMPO_MARCA + " texto not null, " +
			CAMPO_TIPO + " texto not null, " + 
			"FOREIGN KEY (" + CAMPO_PERSONA_FK + ") REFERENCES persona (_id));";
	
	public GustoDataBaseHelper(Context context)
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