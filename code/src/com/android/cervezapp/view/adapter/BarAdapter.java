package com.android.cervezapp.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.cervezapp.R;
import com.android.cervezapp.domain.model.Bar;

// TODO terminar el adapter
public class BarAdapter extends ArrayAdapter<Bar> {

	public BarAdapter(Context context) {
		super(context, R.layout.usuario_adapter, new ArrayList<Bar>());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// if (convertView == null) {
		// convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.usuario_adapter, (ViewGroup) null);
		// }
		// ImageView fotoPerfilListView = (ImageView) convertView.findViewById(R.id.fotoPerfilListaImageView);
		// TextView usuarioUserNameTextView = (TextView) convertView.findViewById(R.id.usuarioUserNameTextView);
		// TextView usuarioEmailTextView = (TextView) convertView.findViewById(R.id.usuarioEmailTextView);
		//
		// Bar bar = this.getItem(position);
		// fotoPerfilListView.setImageBitmap(BitmapUtility.getImage(usuario.getFoto()));
		// usuarioUserNameTextView.setText(usuario.getUserName());
		// usuarioEmailTextView.setText(usuario.getEmail());
		//
		// convertView.setBackgroundResource(R.color.default_color);
		return convertView;
	}
}