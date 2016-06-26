package com.android.cervezapp.business.service;

import java.io.Serializable;
import java.util.List;

import android.content.Context;

import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.persistence.dao.BarDao;
import com.android.cervezapp.persistence.util.IdGenerator;

public class BarService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static BarService instance;

	private BarDao barDao;

	public static BarService getInstance(Context context) {
		if (instance == null) {
			instance = new BarService();
			instance.barDao = BarDao.getInstance(context);
		}
		return instance;
	}

	public List<Bar> getAllBares() {
		return this.barDao.getAllBares();
	}

	public Bar getById(Long id) {
		return this.barDao.getById(id);
	}

	public void saveBar(Bar bar) {
		bar.setId(IdGenerator.getNextId(Bar.class));
		this.barDao.saveBar(bar);
	}

	public void updateBar(Bar bar) {
		this.barDao.updateBar(bar);
	}

	public void deleteBar(Bar bar) {
		this.barDao.deleteBar(bar);
	}
}