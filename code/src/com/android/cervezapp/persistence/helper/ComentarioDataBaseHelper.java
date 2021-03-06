package com.android.cervezapp.persistence.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ComentarioDataBaseHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "db_cervezapp";
	public static final String TABLE_NAME = "comentarios";
	public static final int DB_VERSION = 1;

	public static final String CAMPO_ID = "_id";
	public static final String CAMPO_BAR_FK = "bar_fk";
	public static final String CAMPO_USUARIO_FK = "persona_fk";
	public static final String CAMPO_CALIFICACION = "calificacion";
	public static final String CAMPO_NOTAS = "notas";

	private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + CAMPO_ID + " integer not null primary key autoincrement," + CAMPO_CALIFICACION + " integer, "
			+ CAMPO_NOTAS + " texto, " + "FOREIGN KEY (" + CAMPO_BAR_FK + ") REFERENCES " + BarDataBaseHelper.TABLE_NAME + " (" + BarDataBaseHelper.CAMPO_ID + ")), "
			+ "FOREIGN KEY (" + CAMPO_USUARIO_FK + ") REFERENCES " + UsuarioDataBaseHelper.TABLE_NAME + " (" + UsuarioDataBaseHelper.CAMPO_ID + "));";

	public ComentarioDataBaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			db.execSQL(DROP_TABLE);
			db.execSQL(CREATE_TABLE);
		}
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion < oldVersion) {
			db.execSQL(DROP_TABLE);
			db.execSQL(CREATE_TABLE);
		}
	}
}