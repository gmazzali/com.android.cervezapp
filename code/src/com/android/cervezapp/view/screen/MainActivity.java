package com.android.cervezapp.view.screen;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.cervezapp.R;
import com.android.cervezapp.business.service.UsuarioService;
import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.business.util.Constants;
import com.android.cervezapp.domain.model.Usuario;

public class MainActivity extends Activity {

	private ImageView fotoPerfilImageView;

	private Usuario usuario;

	private UsuarioService usuarioService = UsuarioService.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.usuario = this.usuarioService.setUpUsuario(Constants.ID_USUARIO_DEFAULT,
				BitmapFactory.decodeResource(this.getResources(), R.drawable.leprechaun_drunk_icon));

		this.fotoPerfilImageView = (ImageView) this.findViewById(R.id.fotoPerfilPrincipalImageView);
		this.fotoPerfilImageView.setImageBitmap(BitmapUtility.getImage(this.usuario.getFoto()));
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
}