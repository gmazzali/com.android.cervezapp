package com.android.cervezapp.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.cervezapp.R;
import com.android.cervezapp.business.service.UsuarioService;
import com.android.cervezapp.business.util.Constants;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.RequestCodeEnum;
import com.android.cervezapp.domain.util.ResponseCodeEnum;

public class MainActivity extends Activity {

	private ImageView fotoPerfilImageView;

	private Usuario usuario;

	private UsuarioService usuarioService = UsuarioService.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.usuario = this.usuarioService.setUpUsuario(Constants.ID_USUARIO_DEFAULT, BitmapFactory.decodeResource(this.getResources(), R.drawable.leprechaun_drunk_icon));

		this.fotoPerfilImageView = (ImageView) this.findViewById(R.id.fotoPerfilImageView);
		this.fotoPerfilImageView.setImageBitmap(usuario.getFoto());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		RequestCodeEnum request = RequestCodeEnum.get(requestCode);
		ResponseCodeEnum response = ResponseCodeEnum.get(request, resultCode);

		if (request != null && response != null) {
			switch (request) {

				case SACAR_FOTO_PERFIL:
					switch (response) {

						case SACAR_FOTO_PERFIL_EXITOSO:
							this.updateFotoPerfil((Bitmap) data.getExtras().get("data"));
							break;

						case SACAR_FOTO_PERFIL_FALLIDO:
							break;
					}
					break;
			}
		}
	}

	private void updateFotoPerfil(Bitmap foto) {
		this.usuario.setFoto(foto);
		this.usuarioService.updateUsuario(this.usuario);
		this.fotoPerfilImageView.setImageBitmap(foto);
	}

	public void ejecutarCambioDeFotoDePerfil(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		this.startActivityForResult(intent, RequestCodeEnum.SACAR_FOTO_PERFIL.getRequest());
	}
}