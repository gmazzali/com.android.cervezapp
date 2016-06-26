package com.android.cervezapp.view.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.cervezapp.R;
import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.RequestCodeEnum;

public class MainActivity extends Activity {

	private ImageView fotoPerfilImageView;

	private Usuario usuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.usuario = (Usuario) this.getIntent().getExtras().getSerializable(Usuario.class.getName());

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

	public void listarComentarios(View view) {
		Toast.makeText(this, "Nai trolazo", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, VisualizarComentariosActivity.class);
		this.startActivityForResult(intent, RequestCodeEnum.AGREGAR_COMENTARIO.getRequest());
	}
}