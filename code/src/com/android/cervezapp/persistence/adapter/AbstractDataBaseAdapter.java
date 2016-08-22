package com.android.cervezapp.persistence.adapter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class AbstractDataBaseAdapter implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	private Context context;

	private SQLiteDatabase db;

	protected AbstractDataBaseAdapter(Context context) {
		this.context = context;
	}

	protected abstract SQLiteOpenHelper getSQLiteHelper(Context context);

	public AbstractDataBaseAdapter open() throws SQLiteException {
		if (this.db == null) {
			SQLiteOpenHelper dbHelper = getSQLiteHelper(this.context);
			this.db = dbHelper.getWritableDatabase();
		}
		return this;
	}

	public void begin() {
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
			this.db.setTransactionSuccessful();
			this.db.endTransaction();
		}
	}

	public void close() {
		if (this.db != null) {
			this.db.close();
		}
	}

	protected long insert(String table, ContentValues valores) {
		return this.db.insert(table, null, valores);
	}

	protected void update(String table, ContentValues valores, String whereClause, String[] argumentos) {
		this.db.update(table, valores, whereClause, argumentos);
	}

	protected void delete(String table, String whereClause, String[] argumentos) {
		this.db.delete(table, whereClause, argumentos);
	}

	protected Cursor query(String table, String[] columns, String selection, String[] selectionArgs) {
		return this.db.query(table, columns, selection, selectionArgs, null, null, null);
	}
}