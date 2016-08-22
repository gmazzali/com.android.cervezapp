package com.android.cervezapp.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cervezapp.R;
import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Bar;

public class BarAdapter extends ArrayAdapter<Bar> {

	public BarAdapter(Context context) {
		super(context, R.layout.bar_adapter, new ArrayList<Bar>());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.bar_adapter, (ViewGroup) null);
		}
		ImageView barFotoImageView = (ImageView) convertView.findViewById(R.id.barAdministracionFotoImageView);
		TextView barNombreTextView = (TextView) convertView.findViewById(R.id.barAdministracionNombreTextView);
		TextView barDireccionTextView = (TextView) convertView.findViewById(R.id.barAdministracionAddressTextView);

		Bar bar = this.getItem(position);
		barFotoImageView.setImageBitmap(BitmapUtility.getImage(bar.getFoto()));
		barNombreTextView.setText(bar.getNombre());
		barDireccionTextView.setText(bar.getDireccion());

		convertView.setBackgroundResource(R.color.default_color);
		return convertView;
	}
}