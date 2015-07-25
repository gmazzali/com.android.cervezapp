package com.android.cervezapp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.cervezapp.domain.util.SexoEnum;

/**
 * @author Billy
 */
public class SexoAdapter extends ArrayAdapter<SexoEnum> {

	public SexoAdapter(Context context) {
		super(context, android.R.layout.simple_spinner_dropdown_item, SexoEnum.values());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SexoEnum sexo = this.getItem(position);

		TextView sexoLabel = new TextView(this.getContext());
		sexoLabel.setTextColor(Color.BLACK);
		sexoLabel.setText(sexo.getSexo());

		return sexoLabel;
	}
}