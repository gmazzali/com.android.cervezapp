package com.android.cervezapp.view.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.cervezapp.R;
import com.android.cervezapp.business.service.BarService;
import com.android.cervezapp.domain.model.Bar;
import com.android.cervezapp.domain.util.RequestCodeEnum;
import com.android.cervezapp.domain.util.ResponseCodeEnum;
import com.android.cervezapp.view.adapter.BarAdapter;

public class BarAdministracionActivity extends Activity {

	private BarService barService = BarService.getInstance(this);

	private ListView barListView;

	private BarAdapter barAdapter;

	private int posicionSeleccionada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.bar_administracion_activity);

		this.barAdapter = new BarAdapter(this);
		this.barAdapter.addAll(this.barService.getAllBares());

		this.barListView = (ListView) this.findViewById(R.id.baresListView);
		this.barListView.setAdapter(this.barAdapter);
		this.barListView.setOnItemClickListener(new OnItemClickListener() {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.bar_administracion_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.barAdminsitracionMenuAgregarBarItem) {
			this.agregarBar(item.getActionView());
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void agregarBar(View view) {
		Intent intent = new Intent(this, BarEdicionActivity.class);
		this.startActivityForResult(intent, RequestCodeEnum.AGREGAR_BAR.getRequest());
	}

	public void modificarBar(View view) {
		if (this.posicionSeleccionada >= 0) {
			Bar bar = (Bar) this.barListView.getItemAtPosition(this.posicionSeleccionada);
			Intent intent = new Intent(this, BarEdicionActivity.class);
			intent.putExtra(Bar.class.getName(), bar);
			this.startActivityForResult(intent, RequestCodeEnum.MODIFICAR_BAR.getRequest());
		} else {
			Toast.makeText(this, "Seleccione un bar para modificar", Toast.LENGTH_SHORT).show();
		}
	}

	public void borrarBar(View view) {
		if (this.posicionSeleccionada >= 0) {
			// TODO Hacer el borrado del bar!
		} else {
			Toast.makeText(this, "Seleccione un bar para borrar del sistema", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bar bar = null;

		RequestCodeEnum request = RequestCodeEnum.get(requestCode);
		ResponseCodeEnum response = ResponseCodeEnum.get(request, resultCode);

		if (request != null && response != null) {
			switch (response) {
			case ACEPTAR_AGREGAR_NUEVO_BAR:
				bar = (Bar) data.getExtras().getSerializable(Bar.class.getName());
				this.barAdapter.add(bar);
				this.barService.saveBar(bar);
				break;

			case CANCELAR_AGREDAR_NUEVO_BAR:
				Toast.makeText(this, "Se cancelo el alta del bar", Toast.LENGTH_SHORT).show();
				break;

			case ACEPTAR_MODIFICAR_BAR:
				bar = (Bar) data.getExtras().getSerializable(Bar.class.getName());
				this.barAdapter.remove(bar);
				this.barAdapter.add(bar);
				this.barService.updateBar(bar);
				break;

			case CANCELAR_MODIFICAR_BAR:
				Toast.makeText(this, "Se cancelo la modificación del bar", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		} else {
			Toast.makeText(this, "Problemas desconocido", Toast.LENGTH_SHORT).show();
		}
	}
}