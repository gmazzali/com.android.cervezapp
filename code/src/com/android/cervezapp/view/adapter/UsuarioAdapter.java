package com.android.cervezapp.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.cervezapp.R;
import com.android.cervezapp.domain.model.Usuario;

/**
 * @author Billy
 */
public class UsuarioAdapter extends ArrayAdapter<Usuario> {

	public UsuarioAdapter(Context context) {
		super(context, R.layout.usuario_item_list, new ArrayList<Usuario>());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.usuario_item_list, (ViewGroup) null);
		}
		TextView contactNameTextView = (TextView) convertView.findViewById(R.id.contactNameTextView);
		TextView contactTelTextView = (TextView) convertView.findViewById(R.id.contactTelTextView);

		Usuario usuario = this.getItem(position);
		contactNameTextView.setText(usuario.getNombre() + " " + usuario.getApellido());
		contactTelTextView.setText(usuario.getUserName());

		return convertView;
	}
}