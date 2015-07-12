package com.android.cervezapp.view.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.cervezapp.domain.model.Usuario;
import com.android.cervezapp.domain.util.RequestCodeEnum;
import com.android.cervezapp.view.screen.EdicionUsuarioActivity;

/**
 * @author Billy
 */
public class EditarUsuarioListener implements OnItemClickListener {

	private Activity activity;

	public EditarUsuarioListener(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Usuario usuario = (Usuario) parent.getItemAtPosition(position);
		Intent intent = new Intent(this.activity, EdicionUsuarioActivity.class);
		intent.putExtra(Usuario.class.getName(), usuario);
		this.activity.startActivityForResult(intent, RequestCodeEnum.MODIFICAR_USUARIO.getRequest());
	}
}