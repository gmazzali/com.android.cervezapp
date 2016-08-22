package com.android.cervezapp.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.cervezapp.R;
import com.android.cervezapp.business.service.UsuarioService;
import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Comentario;
import com.android.cervezapp.domain.model.Usuario;

public class ComentarioAdapter extends ArrayAdapter<Comentario> {

	private UsuarioService usuarioService;

	public ComentarioAdapter(Context context) {
		super(context, R.layout.comentario_adapter, new ArrayList<Comentario>());
		this.usuarioService = UsuarioService.getInstance(this.getContext());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.comentario_adapter, (ViewGroup) null);
		}
		ImageView fotoUsuarioListView = (ImageView) convertView.findViewById(R.id.fotoUsuarioListView);
		TextView comentarioUserNameTextView = (TextView) convertView.findViewById(R.id.comentarioUserNameTextView);
		TextView comentarioPreviewTextView = (TextView) convertView.findViewById(R.id.comentarioPreviewTextView);
		RatingBar comentarioPuntajeRatingBar = (RatingBar) convertView.findViewById(R.id.comentarioPuntajeRatingBar);

		Comentario comentario = this.getItem(position);
		Usuario usuario = this.usuarioService.getById(comentario.getUserId());
		fotoUsuarioListView.setImageBitmap(BitmapUtility.getImage(usuario.getFoto()));
		comentarioUserNameTextView.setText(usuario.getUserName());
		comentarioPreviewTextView.setText(comentario.getPreview());
		comentarioPuntajeRatingBar.setRating(comentario.getPuntaje());

		convertView.setBackgroundResource(R.color.default_color);
		return convertView;
	}
}