package com.android.cervezapp.view.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.cervezapp.R;
import com.android.cervezapp.business.service.ComentarioService;
import com.android.cervezapp.business.service.UsuarioService;
import com.android.cervezapp.domain.model.Comentario;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.RequestCodeEnum;
import com.android.cervezapp.domain.util.ResponseCodeEnum;
import com.android.cervezapp.view.adapter.ComentarioAdapter;
import com.android.cervezapp.view.adapter.UsuarioAdapter;

/**
 * Ventana de listado de comentarios.
 * 
 * @author John
 */
public class VisualizarComentariosActivity extends Activity {

	private ComentarioService comentarioService = ComentarioService.getInstance();

	private ListView comentariosListView;

	private ComentarioAdapter comentarioAdapter;

	private int posicionSeleccionada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_visualizar_comentarios);
		Toast.makeText(this, "Nai putazo", Toast.LENGTH_SHORT).show();
		this.comentarioAdapter = new ComentarioAdapter(this);
		this.comentarioAdapter.addAll(this.comentarioService.getAllComentarios());

		this.comentariosListView = (ListView) this.findViewById(R.id.comentarioListView);
		this.comentariosListView.setAdapter(this.comentarioAdapter);
		this.comentariosListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (posicionSeleccionada != position) {
					posicionSeleccionada = position;
					view.setBackgroundResource(R.color.pressed_color);
				} else {
					posicionSeleccionada = -1;
					view.setBackgroundResource(R.color.default_color);
				}
			}
		});
	}

//	public void agregarUsuario(View view) {
//		Intent intent = new Intent(this, EdicionUsuarioActivity.class);
//		this.startActivityForResult(intent, RequestCodeEnum.AGREGAR_USUARIO.getRequest());
//	}
//
//	public void modificarUsuario(View view) {
//		if (this.posicionSeleccionada >= 0) {
//			Usuario usuario = (Usuario) this.usuariosListView.getItemAtPosition(this.posicionSeleccionada);
//			Intent intent = new Intent(this, EdicionUsuarioActivity.class);
//			intent.putExtra(Usuario.class.getName(), usuario);
//			this.startActivityForResult(intent, RequestCodeEnum.MODIFICAR_USUARIO.getRequest());
//		} else {
//			Toast.makeText(this, "Seleccione un usuario para modificar", Toast.LENGTH_SHORT).show();
//		}
//	}

	public void agregarComentario(View view) {
		if (this.posicionSeleccionada >= 0) {
			Comentario comentario = (Comentario) this.comentariosListView.getItemAtPosition(this.posicionSeleccionada);
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra(Comentario.class.getName(), comentario);
			this.startActivity(intent);
		} else {
			Toast.makeText(this, "Seleccione un usuario para ingresar al sistema", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Comentario comentario = null;

		RequestCodeEnum request = RequestCodeEnum.get(requestCode);
		ResponseCodeEnum response = ResponseCodeEnum.get(request, resultCode);

		if (request != null && response != null) {
			switch (response) {
				case ACEPTAR_AGREGAR_COMENTARIO:
					comentario = (Comentario) data.getExtras().getSerializable(Comentario.class.getName());
					this.comentarioAdapter.add(comentario);
					this.comentarioService.saveComentario(comentario);
					break;

				case CANCELAR_AGREGAR_COMENTARIO:
					Toast.makeText(this, "Se cancelo la creacion del comentario", Toast.LENGTH_SHORT).show();
					break;

//				case ACEPTAR_MODIFICAR_USUARIO:
//					usuario = (Usuario) data.getExtras().getSerializable(Usuario.class.getName());
//					this.usuarioAdapter.remove(usuario);
//					this.usuarioAdapter.add(usuario);
//					this.usuarioService.updateUsuario(usuario);
//					break;
//
//				case CANCELAR_MODIFICAR_USUARIO:
//					Toast.makeText(this, "Se cancelo la modificación del usuario", Toast.LENGTH_SHORT).show();
//					break;

				default:
					break;
			}
		} else {
			Toast.makeText(this, "Problemas desconocido", Toast.LENGTH_SHORT).show();
		}
	}
}