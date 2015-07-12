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
import com.android.cervezapp.domain.model.Usuario;

/**
 * @author Billy
 */
public class UsuarioAdapter extends ArrayAdapter<Usuario> {

	public UsuarioAdapter(Context context) {
		super(context, R.layout.usuario_adapter, new ArrayList<Usuario>());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.usuario_adapter, (ViewGroup) null);
		}
		ImageView fotoPerfilListView = (ImageView) convertView.findViewById(R.id.fotoPerfilListaImageView);
		TextView usuarioUserNameTextView = (TextView) convertView.findViewById(R.id.usuarioUserNameTextView);
		TextView usuarioEmailTextView = (TextView) convertView.findViewById(R.id.usuarioEmailTextView);

		Usuario usuario = this.getItem(position);
		fotoPerfilListView.setImageBitmap(BitmapUtility.getImage(usuario.getFoto()));
		usuarioUserNameTextView.setText(usuario.getUserName());
		usuarioEmailTextView.setText(usuario.getEmail());

		convertView.setBackgroundResource(R.color.default_color);
		return convertView;
	}
}