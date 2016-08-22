package com.android.cervezapp.view.screen;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.cervezapp.R;
import com.android.cervezapp.business.util.BitmapUtility;
import com.android.cervezapp.business.util.Constants;
import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.RequestCodeEnum;
import com.android.cervezapp.domain.util.ResponseCodeEnum;
import com.android.cervezapp.domain.util.SexoEnum;
import com.android.cervezapp.view.adapter.SexoAdapter;
import com.android.cervezapp.view.listener.CambiarFechaListener;
import com.android.cervezapp.view.util.ImageHolder;
import com.android.cervezapp.view.watcher.ApellidoWatcher;
import com.android.cervezapp.view.watcher.EmailWatcher;
import com.android.cervezapp.view.watcher.FechaWatcher;
import com.android.cervezapp.view.watcher.NombreWatcher;
import com.android.cervezapp.view.watcher.TelefonoWatcher;
import com.android.cervezapp.view.watcher.UserWatcher;

public class UsuarioEdicionActivity extends Activity {

	private Usuario usuario;

	private ImageView fotoPerfilImageView;

	private EditText userNameEditText;

	private EditText nombreEditText;

	private EditText apellidoEditText;

	private SexoAdapter sexoAdapter;

	private Spinner sexoSpinner;

	private EditText fechaNacimientoEditText;

	private Button fechaNacimientoButton;

	private EditText telefonoEditText;

	private EditText emailEditText;

	private CheckBox fumadorCheckBox;

	private boolean usuarioNuevo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.usuario_edicion_activity);

		this.usuario = (Usuario) this.getIntent().getSerializableExtra(Usuario.class.getName());
		this.usuarioNuevo = this.usuario == null;

		this.fotoPerfilImageView = (ImageView) this.findViewById(R.id.userFotoEdicionImageView);
		this.userNameEditText = (EditText) this.findViewById(R.id.userUserNameEditText);
		this.nombreEditText = (EditText) this.findViewById(R.id.userNameEditText);
		this.apellidoEditText = (EditText) this.findViewById(R.id.userLastnameEditText);
		this.fechaNacimientoEditText = (EditText) this.findViewById(R.id.userBirthdayEditText);
		this.telefonoEditText = (EditText) this.findViewById(R.id.userTelephoneEditText);
		this.emailEditText = (EditText) this.findViewById(R.id.userEmailEditText);
		this.fechaNacimientoButton = (Button) this.findViewById(R.id.userBirthdayButton);
		this.sexoSpinner = (Spinner) this.findViewById(R.id.userSexoSpinner);
		this.fumadorCheckBox = (CheckBox) this.findViewById(R.id.userFumadorCheckBox);

		this.sexoAdapter = new SexoAdapter(this);
		this.sexoSpinner.setAdapter(this.sexoAdapter);

		this.userNameEditText.addTextChangedListener(new UserWatcher(this.userNameEditText));
		this.nombreEditText.addTextChangedListener(new NombreWatcher(this.nombreEditText));
		this.apellidoEditText.addTextChangedListener(new ApellidoWatcher(this.apellidoEditText));
		this.fechaNacimientoEditText.addTextChangedListener(new FechaWatcher(this.fechaNacimientoEditText));
		this.telefonoEditText.addTextChangedListener(new TelefonoWatcher(this.telefonoEditText));
		this.emailEditText.addTextChangedListener(new EmailWatcher(this.emailEditText));

		this.fechaNacimientoButton.setOnClickListener(new CambiarFechaListener(this, this.fechaNacimientoEditText));

		this.setUsuario();
	}

	private void setUsuario() {
		if (!this.usuarioNuevo) {
			if (ImageHolder.getFoto() == null) {
				ImageHolder.setFoto(BitmapUtility.getImage(this.usuario.getFoto()));
			}
			this.userNameEditText.setText(this.usuario.getUserName());
			this.nombreEditText.setText(this.usuario.getNombre());
			this.apellidoEditText.setText(this.usuario.getApellido());
			this.sexoSpinner.setSelection(this.sexoAdapter.getPosition(this.usuario.getSexo()));
			if (this.usuario.getFechaNacimiento() != null) {
				this.fechaNacimientoEditText.setText(new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).format(this.usuario.getFechaNacimiento()));
			}
			this.telefonoEditText.setText(this.usuario.getTelefono());
			this.emailEditText.setText(this.usuario.getEmail());
			this.fumadorCheckBox.setChecked(this.usuario.isFumador());
		}
		if (ImageHolder.getFoto() != null) {
			this.fotoPerfilImageView.setImageBitmap(ImageHolder.getFoto());
		}
	}

	private void getUsuario() throws Exception {
		if (this.fotoPerfilImageView.getDrawable() != null) {
			this.usuario.setFoto(BitmapUtility.getBytes(((BitmapDrawable) this.fotoPerfilImageView.getDrawable()).getBitmap()));
		} else {
			throw new RuntimeException("El usuario no tiene una foto v�lida");
		}
		if (!this.userNameEditText.getText().toString().trim().isEmpty() && this.userNameEditText.getError() == null) {
			this.usuario.setUserName(this.userNameEditText.getText().toString());
		} else {
			throw new RuntimeException("El usuario no tiene un nombre de usuario v�lido");
		}
		if (!this.nombreEditText.getText().toString().trim().isEmpty() && this.nombreEditText.getError() == null) {
			this.usuario.setNombre(this.nombreEditText.getText().toString());
		} else {
			throw new RuntimeException("El usuario no tiene un nombre v�lido");
		}
		if (!this.apellidoEditText.getText().toString().trim().isEmpty() && this.apellidoEditText.getError() == null) {
			this.usuario.setApellido(this.apellidoEditText.getText().toString());
		} else {
			throw new RuntimeException("El usuario no tiene un apellido v�lido");
		}
		if (this.sexoSpinner.getSelectedItem() != null) {
			this.usuario.setSexo((SexoEnum) this.sexoSpinner.getSelectedItem());
		} else {
			throw new RuntimeException("El usuario no tiene un sexo seleccionado");
		}
		if (!this.fechaNacimientoEditText.getText().toString().trim().isEmpty() && this.fechaNacimientoEditText.getError() == null) {
			this.usuario.setFechaNacimiento(new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).parse(this.fechaNacimientoEditText.getText().toString()));
		} else {
			throw new RuntimeException("El usuario no tiene una fecha de nacimiento v�lida");
		}
		if (!this.telefonoEditText.getText().toString().trim().isEmpty() && this.telefonoEditText.getError() == null) {
			this.usuario.setTelefono(this.telefonoEditText.getText().toString());
		} else {
			throw new RuntimeException("El usuario no tiene un tel�fono v�lido");
		}
		if (!this.emailEditText.getText().toString().trim().isEmpty() && this.emailEditText.getError() == null) {
			this.usuario.setEmail(this.emailEditText.getText().toString());
		} else {
			throw new RuntimeException("El usuario no tiene un e-mail v�lido");
		}
		this.usuario.setFumador(this.fumadorCheckBox.isChecked());
	}

	public void guardarUsuario(View v) {
		try {
			if (this.usuarioNuevo) {
				this.usuario = new Usuario();
			}
			this.getUsuario();

			Intent intento = new Intent();
			intento.putExtra(Usuario.class.getName(), this.usuario);
			if (this.usuarioNuevo) {
				this.setResult(ResponseCodeEnum.ACEPTAR_AGREGAR_NUEVO_USUARIO.getResponse(), intento);
			} else {
				this.setResult(ResponseCodeEnum.ACEPTAR_MODIFICAR_USUARIO.getResponse(), intento);
			}
			this.finish();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
		}

	}

	public void cancelarUsuario(View v) {
		if (this.usuarioNuevo) {
			this.setResult(ResponseCodeEnum.CANCELAR_AGREDAR_NUEVO_USUARIO.getResponse());
		} else {
			this.setResult(ResponseCodeEnum.CANCELAR_MODIFICAR_USUARIO.getResponse());
		}
		this.finish();
	}

	public void ejecutarCambioDeFotoDePerfil(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		this.startActivityForResult(intent, RequestCodeEnum.SACAR_FOTO_PERFIL.getRequest());
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
					Bitmap foto = (Bitmap) data.getExtras().get("data");
					ImageHolder.setFoto(foto);
					this.fotoPerfilImageView.setImageBitmap(ImageHolder.getFoto());
					break;

				case SACAR_FOTO_PERFIL_FALLIDO:
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