package com.android.cervezapp.view.screen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.cervezapp.R;
import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.domain.util.RequestCodeEnum;
import com.android.cervezapp.domain.util.ResponseCodeEnum;
import com.android.cervezapp.view.util.ImageHolder;
import com.android.cervezapp.view.watcher.EmailWatcher;
import com.android.cervezapp.view.watcher.NombreBarWatcher;
import com.android.cervezapp.view.watcher.TelefonoWatcher;

public class BarEdicionActivity extends Activity {

	private Bar bar;

	private ImageView fotoImageView;

	private EditText barNameEditText;

	private EditText direccionEditText;

	private EditText emailEditText;

	private EditText telefonoEditText;

	private CheckBox fumadorCheckBox;

	private boolean barNuevo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bar_edicion_activity);

		this.bar = (Bar) this.getIntent().getSerializableExtra(Bar.class.getName());
		this.barNuevo = this.bar == null;

		this.fotoImageView = (ImageView) this.findViewById(R.id.barFotoEdicionImageView);
		this.barNameEditText = (EditText) this.findViewById(R.id.barNameEditText);
		this.direccionEditText = (EditText) this.findViewById(R.id.barAddressEditText);
		this.telefonoEditText = (EditText) this.findViewById(R.id.barTelephoneEditText);
		this.emailEditText = (EditText) this.findViewById(R.id.barEmailEditText);
		this.fumadorCheckBox = (CheckBox) this.findViewById(R.id.barFumadorCheckBox);

		this.barNameEditText.addTextChangedListener(new NombreBarWatcher(this.barNameEditText));
		this.telefonoEditText.addTextChangedListener(new TelefonoWatcher(this.telefonoEditText));
		this.emailEditText.addTextChangedListener(new EmailWatcher(this.emailEditText));

		this.setBar();
	}

	private void setBar() {
		if (!this.barNuevo) {
			if (ImageHolder.getFoto() == null) {
				ImageHolder.setFoto(BitmapUtility.getImage(this.bar.getFoto()));
			}
			this.barNameEditText.setText(this.bar.getNombre());
			this.direccionEditText.setText(this.bar.getDireccion());
			this.telefonoEditText.setText(this.bar.getTelefono());
			this.emailEditText.setText(this.bar.getEmail());
			this.fumadorCheckBox.setChecked(this.bar.getFumador());
		}
		if (ImageHolder.getFoto() != null) {
			this.fotoImageView.setImageBitmap(ImageHolder.getFoto());
		}
	}

	private void getBar() throws Exception {
		if (this.fotoImageView.getDrawable() != null) {
			this.bar.setFoto(BitmapUtility.getBytes(((BitmapDrawable) this.fotoImageView.getDrawable()).getBitmap()));
		} else {
			throw new RuntimeException("El bar no tiene una foto válida");
		}
		if (!this.barNameEditText.getText().toString().trim().isEmpty() && this.barNameEditText.getError() == null) {
			this.bar.setNombre(this.barNameEditText.getText().toString());
		} else {
			throw new RuntimeException("El bar no tiene un nombre válido");
		}
		if (!this.direccionEditText.getText().toString().trim().isEmpty() && this.direccionEditText.getError() == null) {
			this.bar.setDireccion(this.direccionEditText.getText().toString());
		} else {
			throw new RuntimeException("El usuario no tiene un nombre válido");
		}
		if (!this.telefonoEditText.getText().toString().trim().isEmpty() && this.telefonoEditText.getError() == null) {
			this.bar.setTelefono(this.telefonoEditText.getText().toString());
		} else {
			throw new RuntimeException("El bar no tiene un teléfono válido");
		}
		if (!this.emailEditText.getText().toString().trim().isEmpty() && this.emailEditText.getError() == null) {
			this.bar.setEmail(this.emailEditText.getText().toString());
		} else {
			throw new RuntimeException("El bar no tiene un e-mail válido");
		}
		this.bar.setFumador(this.fumadorCheckBox.isChecked());
	}

	public void guardarBar(View v) {
		try {
			if (this.barNuevo) {
				this.bar = new Bar();
			}
			this.getBar();

			Intent intento = new Intent();
			intento.putExtra(Bar.class.getName(), this.bar);
			if (this.barNuevo) {
				this.setResult(ResponseCodeEnum.ACEPTAR_AGREGAR_NUEVO_BAR.getResponse(), intento);
			} else {
				this.setResult(ResponseCodeEnum.ACEPTAR_MODIFICAR_BAR.getResponse(), intento);
			}
			this.finish();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
		}

	}

	public void cancelarBar(View v) {
		if (this.barNuevo) {
			this.setResult(ResponseCodeEnum.CANCELAR_AGREDAR_NUEVO_BAR.getResponse());
		} else {
			this.setResult(ResponseCodeEnum.CANCELAR_MODIFICAR_BAR.getResponse());
		}
		this.finish();
	}

	public void ejecutarCambioDeFotoDeBar(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		this.startActivityForResult(intent, RequestCodeEnum.SACAR_FOTO_BAR.getRequest());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		RequestCodeEnum request = RequestCodeEnum.get(requestCode);
		ResponseCodeEnum response = ResponseCodeEnum.get(request, resultCode);

		if (request != null && response != null) {
			switch (request) {
			case SACAR_FOTO_BAR:
				switch (response) {

				case SACAR_FOTO_BAR_EXITOSO:
					Bitmap foto = (Bitmap) data.getExtras().get("data");
					ImageHolder.setFoto(foto);
					this.fotoImageView.setImageBitmap(ImageHolder.getFoto());
					break;

				case SACAR_FOTO_BAR_FALLIDO:
					Toast.makeText(this, "Se cancelo la foto", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
				break;

			default:
				break;
			}
		} else {
			Toast.makeText(this, "Problemas desconocido", Toast.LENGTH_SHORT).show();
		}
	}
}