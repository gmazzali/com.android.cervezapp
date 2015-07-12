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
import com.android.cervezapp.business.service.UsuarioService;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.RequestCodeEnum;
import com.android.cervezapp.domain.util.ResponseCodeEnum;
import com.android.cervezapp.view.adapter.UsuarioAdapter;

/**
 * Ventana de selección de usuario de inicio.
 * 
 * @author Billy
 */
public class SeleccionUsuarioActivity extends Activity {

	private UsuarioService usuarioService = UsuarioService.getInstance();

	private ListView usuariosListView;

	private UsuarioAdapter usuarioAdapter;

	private int posicionSeleccionada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_seleccion_usuario);

		this.usuarioAdapter = new UsuarioAdapter(this);

		this.usuariosListView = (ListView) this.findViewById(R.id.usuarioListView);
		this.usuariosListView.setAdapter(this.usuarioAdapter);
		this.usuariosListView.setOnItemClickListener(new OnItemClickListener() {

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

	public void agregarUsuario(View view) {
		Intent intent = new Intent(this, EdicionUsuarioActivity.class);
		this.startActivityForResult(intent, RequestCodeEnum.AGREGAR_USUARIO.getRequest());
	}

	public void modificarUsuario(View view) {
		if (this.posicionSeleccionada >= 0) {
			Usuario usuario = (Usuario) this.usuariosListView.getItemAtPosition(this.posicionSeleccionada);
			Intent intent = new Intent(this, EdicionUsuarioActivity.class);
			intent.putExtra(Usuario.class.getName(), usuario);
			this.startActivityForResult(intent, RequestCodeEnum.MODIFICAR_USUARIO.getRequest());
		} else {
			Toast.makeText(this, "Seleccione un usuario para modificar", Toast.LENGTH_SHORT).show();
		}
	}

	public void ingresarSistema(View view) {
		if (this.posicionSeleccionada >= 0) {
			Usuario usuario = (Usuario) this.usuariosListView.getItemAtPosition(this.posicionSeleccionada);
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra(Usuario.class.getName(), usuario);
			this.startActivity(intent);
		} else {
			Toast.makeText(this, "Seleccione un usuario para ingresar al sistema", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Usuario usuario = null;

		RequestCodeEnum request = RequestCodeEnum.get(requestCode);
		ResponseCodeEnum response = ResponseCodeEnum.get(request, resultCode);

		if (request != null && response != null) {
			switch (response) {
				case ACEPTAR_AGREGAR_NUEVO_USUARIO:
					usuario = (Usuario) data.getExtras().getSerializable(Usuario.class.getName());
					this.usuarioAdapter.add(usuario);
					this.usuarioService.saveUsuario(usuario);
					break;

				case CANCELAR_AGREDAR_NUEVO_USUARIO:
					Toast.makeText(this, "Se cancelo el alta del usuario", Toast.LENGTH_SHORT).show();
					break;

				case ACEPTAR_MODIFICAR_USUARIO:
					usuario = (Usuario) data.getExtras().getSerializable(Usuario.class.getName());
					this.usuarioAdapter.remove(usuario);
					this.usuarioAdapter.add(usuario);
					this.usuarioService.updateUsuario(usuario);
					break;

				case CANCELAR_MODIFICAR_USUARIO:
					Toast.makeText(this, "Se cancelo la modificación del usuario", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
			}
		} else {
			Toast.makeText(this, "Problemas desconocido", Toast.LENGTH_SHORT).show();
		}
	}
}