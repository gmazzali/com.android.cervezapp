package com.android.cervezapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.cervezapp.model.util.RequestCodeEnum;
import com.android.cervezapp.model.util.ResponseCodeEnum;
import com.android.cervezup.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
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

	public void ejecutarCambioDeFotoDePerfil(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		this.startActivityForResult(intent, RequestCodeEnum.SACAR_FOTO_PERFIL.getRequest());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		RequestCodeEnum request = RequestCodeEnum.get(requestCode);
		ResponseCodeEnum response = ResponseCodeEnum.get(resultCode);

		if (request != null && response != null) {
			switch (request) {
				
				case SACAR_FOTO_PERFIL:
					switch (response) {
						
						case SACAR_FOTO_PERFIL_EXITOSO:
							break;
							
						case SACAR_FOTO_PERFIL_FALLIDO:
							break;
					}
					break;
			}
		}
	}
}