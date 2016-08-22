package com.android.cervezapp.persistence.dao;

import java.io.Serializable;
import java.util.List;

import android.content.Context;

import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.persistence.adapter.BarDataBaseAdapter;

public class BarDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private static BarDao instance;

	private BarDataBaseAdapter barDataBaseAdapter;

	public static BarDao getInstance(Context context) {
		if (instance == null) {
			instance = new BarDao();
			instance.barDataBaseAdapter = BarDataBaseAdapter.getInstance(context);
		}
		return instance;
	}

	private BarDao() {
	}

	public List<Bar> getAllBares() {
		return this.barDataBaseAdapter.getAllBares();
	}

	public Bar getById(Long id) {
		return this.barDataBaseAdapter.getById(id);
	}

	public long saveBar(Bar bar) {
		return this.barDataBaseAdapter.saveBar(bar);
	}

	public void updateBar(Bar bar) {
		this.barDataBaseAdapter.updateBar(bar);
	}

	public void deleteBar(Bar bar) {
		this.barDataBaseAdapter.deleteBar(bar);
	}
}